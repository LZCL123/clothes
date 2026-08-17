package com.zhichao.clothes.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户信息表")
@TableName(value = "user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号码（用做登录用户名）")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "密码")
    @TableField(value = "password",select = false)
    private String password;

    @Schema(description = "昵称")
    @TableField(value = "nickname")
    private String nickname;

    @Schema(description = "性别")
    @TableField(value = "sex")
    private int sex;

    @Schema(description = "身份证号")
    @TableField(value = "id_number")
    private String idNumber;

    @Schema(description = "头像")
    @TableField(value = "avatar")
    private String avatar;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;


}
