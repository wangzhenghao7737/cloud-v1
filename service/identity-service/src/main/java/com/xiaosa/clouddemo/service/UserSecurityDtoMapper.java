package com.xiaosa.clouddemo.service;

import com.xiaosa.clouddemo.entity.domain.User;
import com.xiaosa.clouddemo.entity.dto.UserSecurityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSecurityDtoMapper {
    UserSecurityDto toSecurityDto(User user);
}
