package com.zhichao.clothes.controller.measurement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhichao.clothes.common.exception.LeaseException;
import com.zhichao.clothes.common.result.Result;
import com.zhichao.clothes.common.result.ResultCodeEnum;
import com.zhichao.clothes.model.entity.UserBodyMeasurementInfo;
import com.zhichao.clothes.model.entity.UserInfo;
import com.zhichao.clothes.service.MeasurementService;
import com.zhichao.clothes.service.UserInfoService;
import com.zhichao.clothes.vo.measurement.MeasurementVo;
import com.zhichao.clothes.vo.measurement.UpdateUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "身体数据管理")
@RestController
@RequestMapping("/app/")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "查询用户身体数据")
    @GetMapping("getMeasurement")
    public Result<MeasurementVo> getMeasurement(@RequestParam int id) {
        MeasurementVo measurementVo = measurementService.getMeasurementById(id);
        return Result.ok(measurementVo);
    }

    @Operation(summary = "修改用户信息")
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody UpdateUserInfoVo updateUserInfoVo) {
        System.out.println(updateUserInfoVo);
        int id = updateUserInfoVo.getId();
        UserInfo userInfo = userInfoService.getById(id);
        if (userInfo == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        BeanUtils.copyProperties(updateUserInfoVo, userInfo);
        System.out.println(userInfo);
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getId, id);
        userInfoService.update(userInfo, queryWrapper);
        return Result.ok();
    }


    @Operation(summary = "新增或修改用户身体数据")
    @PostMapping("setMeasurement")
    public Result setMeasurement(@RequestBody UserBodyMeasurementInfo userBodyMeasurementInfo) {
        int userId = userBodyMeasurementInfo.getUserId();
        UserInfo userInfo = userInfoService.getById(userId);
        if(userInfo == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        LambdaQueryWrapper<UserBodyMeasurementInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserBodyMeasurementInfo::getUserId, userInfo.getId());
        UserBodyMeasurementInfo one = measurementService.getOne(queryWrapper);
        Long id = one.getId();
        if(id == null){
            measurementService.save(userBodyMeasurementInfo);
        }
        userBodyMeasurementInfo.setId(id);
        measurementService.saveOrUpdate(userBodyMeasurementInfo);
        return Result.ok();
    }
}
