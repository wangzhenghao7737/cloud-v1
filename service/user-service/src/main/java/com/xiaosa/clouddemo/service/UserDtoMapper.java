package com.xiaosa.clouddemo.service;

import com.xiaosa.clouddemo.domain.User;
import com.xiaosa.clouddemo.dto.user.UserDto;
import com.xiaosa.clouddemo.dto.user.admin.UserTemplateDto;
import com.xiaosa.clouddemo.entity.UserCommon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDto toDto(User user);
    UserCommon toCommon(User  user);
    UserTemplateDto toTemplateDto(User user);
}
