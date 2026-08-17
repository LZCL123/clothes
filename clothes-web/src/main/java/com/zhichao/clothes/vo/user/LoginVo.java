package com.zhichao.clothes.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "APP端登录")
public class LoginVo {

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "短信验证码")
    private String code;
}
