package com.zhichao.clothes.controller.login;

import com.zhichao.clothes.common.login.LoginUserHolder;
import com.zhichao.clothes.common.result.Result;
import com.zhichao.clothes.service.LoginService;
import com.zhichao.clothes.vo.user.LoginVo;
import com.zhichao.clothes.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "获取验证码")
    @GetMapping("/login/getCode")
    public Result getCode(@RequestParam String phone){
        loginService.getCode(phone);
        return Result.ok();
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token =loginService.login(loginVo);
        return Result.ok(token);
    }

    @Operation(summary = "获取登录用户信息")
    @GetMapping("info")
    public Result<UserInfoVo> info() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        UserInfoVo result = loginService.getLoginUserInfoById(userId);
        return Result.ok(result);
    }
}
