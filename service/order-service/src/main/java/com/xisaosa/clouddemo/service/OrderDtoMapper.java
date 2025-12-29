package com.xisaosa.clouddemo.service;

import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.entity.OrderCommon;
import com.xisaosa.clouddemo.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {
    OrderCreateDto toDto(Order order);
    OrderCommon toCommon(Order  order);

    Order toOrder(OrderCreateDto orderCreateDto);
}
