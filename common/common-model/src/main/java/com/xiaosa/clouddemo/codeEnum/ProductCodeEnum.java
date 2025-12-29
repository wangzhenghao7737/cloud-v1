package com.xiaosa.clouddemo.codeEnum;

import com.xiaosa.clouddemo.constant.CodeEnum;

public enum ProductCodeEnum implements CodeEnum {
    PRODUCT_NOT_FOUND(1001, "产品不存在", "产品不存在"),
    PRODUCT_NOT_ENOUGH(1002, "产品库存不足", "产品库存不足"),
    PRODUCT_SALE_OUT(1003, "产品已售罄", "产品已售罄"),
    PRODUCT_ALREADY_EXIST(1004, "产品已存在", "产品已存在"),
    PRODUCT_NOT_SALE(1005, "产品未上架", "产品未上架"),
    PRODUCT_OFFLINE(1006, "产品已下架", "产品已下架"),
    PRODUCT_REQUEST_PARAMETER_ERROR(1007, "参数错误", "参数错误"),
    PRODUCT_NOT_START_SALE(1008, "产品未开始销售", "产品未开始销售"),
    PRODUCT_UPDATE_ERROR(1009, "产品更新失败", "产品更新失败"),;

    ProductCodeEnum(int code, String message, String description){
        this.code = code;
        this.message = message;
        this.description = description;
    }
    private final int code;
    private final String message;
    private final String description;
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
