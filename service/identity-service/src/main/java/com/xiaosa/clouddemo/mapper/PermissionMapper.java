package com.xiaosa.clouddemo.mapper;

import com.xiaosa.clouddemo.entity.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author D14
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2025-12-25 11:13:33
* @Entity generator.domain.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {
 List<Permission> selectPermissionsNameByPermissionIds(@Param("permissionIds") List<Long> permissionIds);
}




