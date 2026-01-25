package com.xiaosa.clouddemo.security;

import com.xiaosa.clouddemo.entity.domain.*;
import com.xiaosa.clouddemo.entity.dto.UserSecurityDto;
import com.xiaosa.clouddemo.mapper.*;
import com.xiaosa.clouddemo.service.UserSecurityDtoMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author D14
 * @description 从数据库获取用户心信息，放入SpringSecurity上下文
 * @createDate 2025-12-02 23:37:53
 */
@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserSecurityDtoMapper userSecurityDtoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUserId(Long.parseLong(username));
        // 框架会根据语言环境，将错误提示转换为 用户名或密码错误
        if (user == null) throw new UsernameNotFoundException("用户不存在");
        return buildLoginUserDetails(user);
    }
    public User loadUserByPhone(String phone){
        User user = userMapper.selectByUserPhone(phone);
        if (user == null) throw new UsernameNotFoundException("用户不存在");
        return user;
    }
    // 私有方法，提取公共逻辑
    public LoginUserDetails buildLoginUserDetails(User user) {
        List<String> roleNameList = new ArrayList<>();
        List<String> permissionNameList = new ArrayList<>();

        List<UserRole> userRoles = userRoleMapper.selectByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(userRoles)) {
            List<Long> roleIdList = userRoles.stream()
                    .map(UserRole::getRoleId)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(roleIdList)) {
                List<Role> roles = roleMapper.selectRoleNameByRoleIds(roleIdList);
                if (!CollectionUtils.isEmpty(roles)) {
                    roleNameList.addAll(roles.stream().map(Role::getRoleName).toList());
                }
                List<RolePermission> rolePermissions = rolePermissionMapper.selectPermissionsIdByRoleIds(roleIdList);
                if (!CollectionUtils.isEmpty(rolePermissions)) {
                    List<Long> permissionsIdList = rolePermissions.stream()
                            .map(RolePermission::getPermissionId)
                            .collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(permissionsIdList)) {
                        List<Permission> permissions = permissionMapper.selectPermissionsNameByPermissionIds(permissionsIdList);
                        if (!CollectionUtils.isEmpty(permissions)) {
                            permissionNameList.addAll(permissions.stream().map(Permission::getPermissionName).toList());
                        }
                    }
                }
            }
        }
        return new LoginUserDetails(userSecurityDtoMapper.toSecurityDto(user), roleNameList, permissionNameList, user.getUserId().toString());
    }
}
