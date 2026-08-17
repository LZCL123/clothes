package com.zhichao.clothes.vo.measurement;


import com.zhichao.clothes.model.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "APP端修改用户信息")
public class UpdateUserInfoVo {

    @Schema(description = "用户id")
    private int id;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "性别")
    private int sex;

}
