package com.zhichao.clothes.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "身体数据表")
@TableName(value = "user_body_measurement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBodyMeasurementInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableField(value = "user_id")
    private int userId;

    @Schema(description = "身高")
    @TableField(value = "height")
    private Double height;

    @Schema(description = "体重")
    @TableField(value = "weight")
    private Double weight;

    @Schema(description = "胸围")
    @TableField(value = "bust")
    private Double bust;

    @Schema(description = "腰围")
    @TableField(value = "waist")
    private Double waist;

    @Schema(description = "臀围")
    @TableField(value = "hip")
    private Double hip;

    @Schema(description = "腿长")
    @TableField(value = "leg_length")
    private Double legLength;

    @Schema(description = "肩宽")
    @TableField(value = "shoulder_width")
    private Double shoulderWidth;

}
