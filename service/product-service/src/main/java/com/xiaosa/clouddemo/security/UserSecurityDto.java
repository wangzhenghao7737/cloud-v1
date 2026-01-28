package com.xiaosa.clouddemo.security;

import lombok.Data;

@Data
public class UserSecurityDto {
    private Long userId;
    private String phone;
    private String password;
    private Integer userStatus;
}
