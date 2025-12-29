package com.xiaosa.clouddemo.exception;

import com.xiaosa.clouddemo.constant.CodeEnum;

public class BusinessException extends RuntimeException{
    private final int code;
    private final String description;

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.description = codeEnum.getDescription();
    }
    public BusinessException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum.getMessage(),cause);
        this.code = codeEnum.getCode();
        this.description = codeEnum.getDescription();
    }
    public BusinessException(int code, String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
    public BusinessException(int code, String message, String description, Throwable cause) {
        super(message,cause);
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
