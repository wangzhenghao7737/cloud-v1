package com.xisaosa.clouddemo.controller;

import com.xiaosa.clouddemo.dto.OrderCreateDto;
import com.xiaosa.clouddemo.entity.OrderCommon;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xisaosa.clouddemo.service.OrderService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/{id}")
    public ApiResponse<OrderCommon> getOrderById(@PathVariable("id") Long id) {
        OrderCommon order = orderService.getOrderCommonById(id);
        return ApiResponse.success(order);
    }
    @PostMapping("/create")
    public ApiResponse<Boolean> createOrder(@Valid @RequestBody OrderCreateDto order) {
        Boolean result = orderService.createOrderCreateDto(order);
        return ApiResponse.success(result);
    }
}
