package com.zhichao.clothes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhichao.clothes.model.entity.UserBodyMeasurementInfo;
import com.zhichao.clothes.vo.measurement.MeasurementVo;

public interface MeasurementService extends IService<UserBodyMeasurementInfo> {
    MeasurementVo getMeasurementById(int id);
}
