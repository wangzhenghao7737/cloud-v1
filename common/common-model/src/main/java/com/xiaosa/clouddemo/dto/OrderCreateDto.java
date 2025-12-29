package com.xiaosa.clouddemo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class OrderCreateDto {
    private Long id;

    @Positive
    private Long userId;

    @Positive
    private Long productId;

    @DecimalMin("0.0")
    private BigDecimal totalPrice;

    @Positive
    private Integer quantity;

    public Long getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     */
    public Long getProductId() {
        return productId;
    }

    /**
     *
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     *
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
