package com.xiaosa.clouddemo.utils;

import java.util.UUID;

/**
 * 用于规范redis的key
 */
public class KeyUtils {
    public static String getProductKey(Long productId) {
        return "product-service:productId:" + productId;
    }
    public static String getLockKey(Long productId) {
        return "product-service:productLock:" + productId;
    }
    public static String getLockValue() {
        return UUID.randomUUID().toString();
    }
}
