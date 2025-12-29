package com.xiaosa.clouddemo.entity.dto;

import com.xiaosa.clouddemo.entity.dto.group.PhoneGroup;
import com.xiaosa.clouddemo.entity.dto.group.SmsGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SmsDto {
    @NotEmpty(groups = {PhoneGroup.class, SmsGroup.class}, message = "手机号为空")
    private String phone;
    @NotEmpty(groups = {PhoneGroup.class}, message = "验证码为空")
    private String code;
}
