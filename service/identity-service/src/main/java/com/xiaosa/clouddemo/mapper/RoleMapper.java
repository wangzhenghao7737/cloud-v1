package com.xiaosa.clouddemo.mapper;

import com.xiaosa.clouddemo.entity.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author D14
* @description 针对表【role】的数据库操作Mapper
* @createDate 2025-12-25 11:13:33
* @Entity generator.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleNameByRoleIds(@Param("roleIds") List<Long> roleIds);
}




