package com.xisaosa.clouddemo.exception;

import com.xiaosa.clouddemo.constant.CommonConstant;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.result.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<String> handleBusinessException(BusinessException e) {
        return ApiResponse.fail(e.getCode(), e.getMessage(), e.getDescription());
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
