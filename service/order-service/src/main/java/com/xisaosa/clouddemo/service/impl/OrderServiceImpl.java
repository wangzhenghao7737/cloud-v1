package com.xisaosa.clouddemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.entity.OrderCommon;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.codeEnum.OrderCodeEnum;
import com.xisaosa.clouddemo.domain.Order;
import com.xisaosa.clouddemo.service.OrderDtoMapper;
import com.xisaosa.clouddemo.service.OrderService;
import com.xisaosa.clouddemo.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
* @author D14
* @description 针对表【order】的数据库操作Service实现
* @createDate 2025-12-19 15:42:00
*/
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDtoMapper orderDtoMapper;
    @Override
    public OrderCommon getOrderCommonById(Long id) {
        Order order = orderMapper.selectById(id);
        if(ObjectUtils.isEmpty( order)){
            throw new BusinessException(OrderCodeEnum.ORDER_NOT_FOUND);
        }
        return orderDtoMapper.toCommon(order);
    }

    @Transactional
    @Override
    public boolean createOrderCreateDto(OrderCreateDto orderCreateDto) {
        Order order = orderDtoMapper.toOrder(orderCreateDto);
        final int result = orderMapper.insert(order);
        if(result != 1){
            throw new BusinessException(OrderCodeEnum.ORDER_CREATE_ERROR);
        }
        return true;
    }
}




