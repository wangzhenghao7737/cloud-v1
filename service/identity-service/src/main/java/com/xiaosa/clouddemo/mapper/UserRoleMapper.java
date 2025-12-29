package com.xiaosa.clouddemo.mapper;

import com.xiaosa.clouddemo.entity.domain.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author D14
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2025-12-25 11:13:33
* @Entity generator.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRole> selectByUserId(Long userId);
}




