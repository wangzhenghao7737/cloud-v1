package com.xiaosa.clouddemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosa.clouddemo.entity.domain.Permission;
import com.xiaosa.clouddemo.service.PermissionService;
import com.xiaosa.clouddemo.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author D14
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2025-12-25 11:13:33
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {

}




