package com.xiaosa.clouddemo.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {
    @NotBlank(message = "账号为空")
    private String account;
    @NotBlank(message = "密码为空")
    private String password;
}
