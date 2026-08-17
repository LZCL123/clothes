package com.zhichao.clothes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhichao.clothes.mapper.MeasurementMapper;
import com.zhichao.clothes.model.entity.UserBodyMeasurementInfo;
import com.zhichao.clothes.service.MeasurementService;
import com.zhichao.clothes.vo.measurement.MeasurementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementServiceImpl extends ServiceImpl<MeasurementMapper, UserBodyMeasurementInfo> implements MeasurementService {

    @Autowired
    private MeasurementMapper mapper;

    @Override
    public MeasurementVo getMeasurementById(int id) {
        return mapper.getMeasurementById(id);
    }
}
