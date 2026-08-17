package com.zhichao.clothes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhichao.clothes.common.constant.RedisConstant;
import com.zhichao.clothes.common.exception.LeaseException;
import com.zhichao.clothes.common.result.ResultCodeEnum;
import com.zhichao.clothes.common.utils.CodeUtils;
import com.zhichao.clothes.common.utils.JwtUtil;
import com.zhichao.clothes.mapper.UserInfoMapper;
import com.zhichao.clothes.model.entity.UserInfo;
import com.zhichao.clothes.service.LoginService;


import com.zhichao.clothes.service.SmsService;
import com.zhichao.clothes.vo.user.LoginVo;
import com.zhichao.clothes.vo.user.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public  class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SmsService smsService;

    @Override
    public void getCode(String phone) {
        String code = CodeUtils.getRandomCode(6);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;

        Boolean hasKey = redisTemplate.hasKey(key);

        if(hasKey){
            // 获取key的过期时间
            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            // 判断是否过期
            if(RedisConstant.APP_LOGIN_CODE_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC){
                throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
            }
        }
        // 发送短信
        smsService.sendCode(phone, code);
        // 将验证码存储到redis中,设置过期时间
        redisTemplate.opsForValue().set(key,code,RedisConstant.APP_LOGIN_CODE_TTL_SEC,TimeUnit.SECONDS);
    }

    @Override
    public String login(LoginVo loginVo) {
        if(loginVo.getPhone()==null){
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }

        if(loginVo.getCode()==null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        String code = redisTemplate.opsForValue().get(key);

        if(code == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }

        if(!loginVo.getCode().equals(code)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone,loginVo.getPhone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if(userInfo == null) {
            //注册
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setNickname("用户-"+loginVo.getPhone().substring(7));
        }
        return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
    }

    @Override
    public UserInfoVo getLoginUserInfoById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        String avatarUrl = userInfo.getAvatar();
        String nickname = userInfo.getNickname();
        UserInfoVo userInfoVo = new UserInfoVo(nickname,avatarUrl);
        return userInfoVo;
    }


}
