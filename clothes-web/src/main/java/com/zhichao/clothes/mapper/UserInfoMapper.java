package com.zhichao.clothes.mapper;

import com.zhichao.clothes.model.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Lenovo
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-03-06 17:56:11
* @Entity com.zhichao.lease.domain.UserInfo
*/

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo selectOneByUsername(String username);
}




