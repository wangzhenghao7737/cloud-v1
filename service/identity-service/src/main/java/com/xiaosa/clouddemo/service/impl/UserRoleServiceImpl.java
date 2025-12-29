package com.xiaosa.clouddemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosa.clouddemo.entity.domain.UserRole;
import com.xiaosa.clouddemo.service.UserRoleService;
import com.xiaosa.clouddemo.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author D14
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2025-12-25 11:13:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




