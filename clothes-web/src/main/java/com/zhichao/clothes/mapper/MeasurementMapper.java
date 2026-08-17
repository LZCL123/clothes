package com.zhichao.clothes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhichao.clothes.model.entity.UserBodyMeasurementInfo;
import com.zhichao.clothes.vo.measurement.MeasurementVo;

public interface MeasurementMapper extends BaseMapper<UserBodyMeasurementInfo> {
    MeasurementVo getMeasurementById(int id);
}
