package com.xiaosa.clouddemo.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class ReduceStockDto {
    @Positive
    private Long productId;
    @Min(value = 1, message = "商品减库存数量不能小于1")
    private Integer count;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
