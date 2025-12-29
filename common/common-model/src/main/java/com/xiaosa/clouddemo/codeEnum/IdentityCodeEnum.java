package com.xiaosa.clouddemo.codeEnum;

import com.xiaosa.clouddemo.constant.CodeEnum;

public enum IdentityCodeEnum implements CodeEnum {

    AUTHORIZATION_NOT_PERMIT(1001,"not permit","permit"),
    LOGIN_UN_AUTHENTICATION(1002,"not authenticate","authenticate"),
    LOGIN_PARAMETER_ERROR(1003,"parameter error","parameter_error"),
    LOGIN_PASSWORD_EMPTY(1004,"password empty","password_empty"),
    LOGIN_SMS_CODE_EMPTY(1005,"sms code empty","sms_code_empty"),
    LOGIN_TOKEN_MISSING(1006,"token missing","token_missing"),
    LOGIN_TOKEN_EXPIRED(1007,"token expired","token_expired"),
    JSON_PROCESSING_ERROR(1008,"json processing error","json_processing_error");
    private final int code;
    private final String message;
    private final String description;

    IdentityCodeEnum(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
