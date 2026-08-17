package com.zhichao.clothes.vo.measurement;

import com.zhichao.clothes.model.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "APP端详细身体数据")
public class MeasurementVo {

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "身高")
    private Double height;

    @Schema(description = "体重")
    private Double weight;

    @Schema(description = "胸围")
    private Double bust;

    @Schema(description = "腰围")
    private Double waist;

    @Schema(description = "臀围")
    private Double hip;

    @Schema(description = "腿长")
    private Double legLength;

    @Schema(description = "肩宽")
    private Double shoulderWidth;
}
