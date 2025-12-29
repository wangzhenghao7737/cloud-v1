package com.xiaosa.clouddemo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCommon {
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private Long productId;

    /**
     *
     */
    private BigDecimal totalPrice;

    /**
     *
     */
    private Integer quantity;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer logicDelete;

    /**
     *
     */
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

    /**
     *
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     */
    public Integer getLogicDelete() {
        return logicDelete;
    }

    /**
     *
     */
    public void setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
    }


    @Override
    public String toString() {
        return "OrderCommon{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", logicDelete=" + logicDelete +
                '}';
    }
}
