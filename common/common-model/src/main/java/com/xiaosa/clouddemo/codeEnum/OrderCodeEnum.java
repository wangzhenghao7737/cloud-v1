package com.xiaosa.clouddemo.codeEnum;

import com.xiaosa.clouddemo.constant.CodeEnum;

public enum OrderCodeEnum implements CodeEnum {
    ORDER_NOT_FOUND(10001,"order not found" ,"订单不存在"),
    ORDER_REQUEST_PARAMETER_ERROR(10002,"order request parameter error" ,"订单请求参数错误"),
    ORDER_CREATE_ERROR(10003,"order create error" ,"订单创建失败");
    private final int code;
    private final String message;
    private final String description;

    OrderCodeEnum(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

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
