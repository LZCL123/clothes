package com.zhichao.clothes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhichao.clothes.mapper.UserInfoMapper;
import com.zhichao.clothes.model.entity.UserInfo;
import com.zhichao.clothes.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
