package com.zhichao.clothes.service;

import com.zhichao.clothes.vo.user.LoginVo;
import com.zhichao.clothes.vo.user.UserInfoVo;

public interface LoginService {

    String login(LoginVo loginVo);

    void getCode(String phone);

    UserInfoVo getLoginUserInfoById(Long userId);
}
