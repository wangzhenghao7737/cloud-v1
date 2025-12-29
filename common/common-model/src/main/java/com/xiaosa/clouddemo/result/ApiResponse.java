package com.xiaosa.clouddemo.result;

import com.xiaosa.clouddemo.constant.CodeEnum;
import com.xiaosa.clouddemo.constant.CommonConstant;

import java.io.Serial;
import java.io.Serializable;

public class ApiResponse <T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private String description;
    private T data;
    private final Long timestamp;
    public ApiResponse() {
        timestamp = System.currentTimeMillis();
    }
    public static <T> ApiResponse<T> success() {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CommonConstant.SUCCESS_CODE);
        apiResponse.setMessage(CommonConstant.SUCCESS_MESSAGE);
        apiResponse.setData(null);
        return apiResponse;
    }
    public static <T> ApiResponse<T> success(T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CommonConstant.SUCCESS_CODE);
        apiResponse.setMessage(CommonConstant.SUCCESS_MESSAGE);
        apiResponse.setData(data);
        return apiResponse;
    }
    public static <T> ApiResponse<T> fail(CodeEnum codeEnum) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(codeEnum.getCode());
        apiResponse.setMessage(codeEnum.getMessage());
        apiResponse.setDescription(codeEnum.getDescription());
        return apiResponse;
    }
    public static <T> ApiResponse<T> fail(int code, String message, String description) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setDescription(description);
        apiResponse.setData( null);
        return apiResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
