package com.xiaosa.clouddemo.exception;

import com.xiaosa.clouddemo.constant.CommonConstant;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.result.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<String> handleBusinessException(BusinessException e) {
        log.warn("Product Service 业务异常", e);
        return ApiResponse.fail(e.getCode(), e.getMessage(), e.getDescription());
    }
    /**
     * 参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String customErrorMessage = "请求参数校验失败"; // 默认消息
        // 获取第一个校验失败的字段的自定义错误信息
        if (!e.getBindingResult().getFieldErrors().isEmpty()) {
            FieldError firstError = e.getBindingResult().getFieldErrors().get(0);
            // getDefaultMessage() 会返回你在注解中定义的 message 属性值（如果有的话）
            if (firstError.getDefaultMessage() != null && !firstError.getDefaultMessage().isEmpty()) {
                customErrorMessage = firstError.getDefaultMessage();
            }
        }
        return ApiResponse.fail(SystemCodeEnum.SYSTEM_REQUEST_PARAMETER_ERROR.getCode(),
                customErrorMessage,
                e.getMessage());
    }

    /**
     * 参数不可读
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ApiResponse<String> handleHttpMessageNotReadableException(Exception e) {
        return ApiResponse.fail(SystemCodeEnum.SYSTEM_REQUEST_PARAMETER_ERROR.getCode(),
                SystemCodeEnum.SYSTEM_REQUEST_PARAMETER_ERROR.getMessage(),
                e.getMessage());
    }
    /**
     * 资源不存在
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoHandlerFoundException.class})
    public ResponseEntity<ApiResponse<String>> handleNoHandlerFoundException(Exception e) {
        return ResponseEntity
                .status(SystemCodeEnum.RESOURCE_NOT_FOUND.getCode())
                .body(ApiResponse.fail(SystemCodeEnum.RESOURCE_NOT_FOUND));
    }
    // todo security filter-chain的权限不足异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        log.error("product系统异常", e);
        return ResponseEntity
                .status(CommonConstant.SYSTEM_ERROR_CODE)
                .body(ApiResponse.fail(SystemCodeEnum.SYSTEM_BUSY));
    }
}
