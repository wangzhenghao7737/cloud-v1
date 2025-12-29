package com.xiaosa.clouddemo.codeEnum;

import com.xiaosa.clouddemo.constant.CodeEnum;

public enum UserCodeEnum implements CodeEnum {
    USER_NOT_EXIST(10001,"用户不存在","用户不存在"),
    USER_FROZEN(10002,"用户已冻结","用户已冻结"),
    USER_MONEY_NOT_ENOUGH(10003,"用户余额不足","用户余额不足"),
    USER_IS_DELETED(10004,"用户已删除","用户已删除"),
    USER_UPDATE_ERROR(10005,"用户更新失败","用户更新失败"),
    USER_REQUEST_PARAM_ERROR(10006,"用户请求参数错误","用户请求参数错误"),;
    private final int code;
    private final String message;
    private final String description;
    UserCodeEnum(int code, String message, String description) {
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
