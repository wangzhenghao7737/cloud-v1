package com.xisaosa.clouddemo.service;

import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.entity.OrderCommon;
import com.xisaosa.clouddemo.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author D14
* @description 针对表【order】的数据库操作Service
* @createDate 2025-12-19 15:42:00
*/
public interface OrderService extends IService<Order> {
    OrderCommon getOrderCommonById(Long id);
    boolean createOrderCreateDto(OrderCreateDto order);
}
