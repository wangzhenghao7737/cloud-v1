package com.xiaosa.clouddemo.codeEnum;

import com.xiaosa.clouddemo.constant.CodeEnum;

public enum SystemCodeEnum implements CodeEnum {
    SYSTEM_ERROR(500, "系统错误", "系统错误"),
    SYSTEM_BUSY(503, "系统繁忙", "系统繁忙"),
    UNKNOWN_ERROR(500,"未知错误" ,"未知错误" ),
    TIMEOUT(500, "请求超时", "请求超时" ),
    CONNECTION_REFUSED(500, "连接被拒绝", "连接被拒绝"),
    HOST_NOT_FOUND(500, "主机未找到", "主机未找到"),
    NOT_FOUND(404, "请求资源不存在", "请求资源不存在"),
    SERVICE_UNAVAILABLE(503, "服务不可用", "服务不可用"),
    SERVER_ERROR(505, "服务器错误", "服务器错误"),
    CLIENT_ERROR(500, "客户端错误", "客户端错误"),
    HTTP_ERROR(500, "HTTP错误", "HTTP错误"),
    SYSTEM_REQUEST_PARAMETER_ERROR(2010, "请求参数错误", "系统请求参数错误"),
    RESOURCE_NOT_FOUND(404, "资源未找到", "资源未找到"),;
    private final int code;
    private final String message;
    private final String description;
    SystemCodeEnum(int code, String message, String description) {
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
