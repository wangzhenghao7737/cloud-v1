package com.xiaosa.clouddemo.controller;

import com.xiaosa.clouddemo.dto.purchase.PurChaseDto;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.PurchaseService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;
    @PostMapping("/create")
    public ApiResponse<PurChaseDto> createPurchase(@Valid @RequestBody PurChaseDto purChaseDto) {
        System.out.println(purChaseDto.toString());
        final PurChaseDto purchase = purchaseService.createPurchase(purChaseDto);
        return ApiResponse.success(purchase);
    }
}
