package com.xiaosa.clouddemo.service;


import com.xiaosa.clouddemo.constant.CodeEnum;
import com.xiaosa.clouddemo.codeEnum.SystemCodeEnum;
import com.xiaosa.clouddemo.result.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FeignFallbackHelper {
    private static final Logger log = LoggerFactory.getLogger(FeignFallbackHelper.class);

    private FeignFallbackHelper() {} // 工具类，禁止实例化

    public static <T> ApiResponse<T> fallbackResult(String message, Throwable cause) {
        // 1. 分类异常
        CodeEnum error = classifyError(cause);

        // 2. 记录结构化日志（带堆栈）
        log.warn("Feign fallback message : [{}]\n {}", message, error.getMessage(), cause);

        // 3. 返回统一业务错误
        return ApiResponse.fail(error);
    }

    // ———————— 私有方法：异常分类 ————————
    private static CodeEnum classifyError(Throwable cause) {
        if (cause == null) {
            return SystemCodeEnum.SYSTEM_ERROR;
        }

        // 网络层异常（JDK 原生）
        if (cause instanceof java.net.SocketTimeoutException) {
            return SystemCodeEnum.TIMEOUT;
        }
        if (cause instanceof java.net.ConnectException) {
            return SystemCodeEnum.CONNECTION_REFUSED;
        }
        if (cause instanceof java.net.UnknownHostException) {
            return SystemCodeEnum.HOST_NOT_FOUND;
        }

        // Feign HTTP 异常
        if (cause instanceof feign.FeignException feignEx) {
            return switch (feignEx.status()) {
                case 404 -> SystemCodeEnum.NOT_FOUND;
                case 503 -> SystemCodeEnum.SERVICE_UNAVAILABLE;
                case 500, 501, 502, 504 -> SystemCodeEnum.SERVER_ERROR;
                case 400, 401, 403 -> SystemCodeEnum.CLIENT_ERROR;
                default -> SystemCodeEnum.HTTP_ERROR;
            };
        }
        return SystemCodeEnum.UNKNOWN_ERROR;
    }
}
