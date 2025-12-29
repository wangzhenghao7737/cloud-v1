package com.xiaosa.clouddemo.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneDto {
    @NotBlank(message = "手机号为空")
    private String phone;
    @NotBlank(message = "密码为空")
    private String password;
}
