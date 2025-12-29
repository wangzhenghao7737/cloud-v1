package com.xiaosa.clouddemo.dto.purchase;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PurChaseDto {
    @Positive(message = "用户id不能小于0")
    @NotNull(message = "用户id不能为空")
    private Long userId;
    @Positive(message = "商品id不能小于0")
    @NotNull(message = "商品id不能为空")
    private Long productId;
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1L, message = "商品数量不能小于1")
    private Integer count;

    private boolean result;

    public PurChaseDto() {
        this.result = false;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PurChaseDto{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", count=" + count +
                ", result=" + result +
                '}';
    }
}
