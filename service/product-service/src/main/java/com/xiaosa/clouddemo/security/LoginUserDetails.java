package com.xiaosa.clouddemo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginUserDetails implements UserDetails {
    private UserSecurityDto user;
    private List<String> roleNames;
    private List<String> permissionNames;
    // 用户 id
    private String loginIdentifier;
    public LoginUserDetails() {
    }

    public LoginUserDetails(UserSecurityDto user
            , List<String> roleNames
            , List<String> permissionNames
            , String loginIdentifier) {
        this.user = user;
        this.roleNames = roleNames;
        this.permissionNames = permissionNames;
        this.loginIdentifier = loginIdentifier;
    }

    /**
     * 权限列表
     * 用户的权限信息
     * 1.角色 ROLE_ eg.ROLE_ADMIN
     * 2.权限
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        /*
         * 当前用户的权限信息
         * 1. 角色表权限   ROLE_  admin  ROLE_admin
         * 2. 权限表权限  del add query edit
         */
        if(!CollectionUtils.isEmpty(roleNames)){
            for (String roleName : roleNames) {
                roleName = "ROLE_"+roleName;
                grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
            }
        }
        if(!CollectionUtils.isEmpty(permissionNames)){
            for (String permissionName : permissionNames) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permissionName));
            }
        }
        return grantedAuthorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * user_id
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return loginIdentifier;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return user!=null
                && user.getUserStatus()!=null
                && user.getUserStatus()== IdentityConstant.USER_EXIST;
    }
}
