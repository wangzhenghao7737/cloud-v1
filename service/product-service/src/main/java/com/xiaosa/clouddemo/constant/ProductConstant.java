package com.xiaosa.clouddemo.constant;

import java.util.concurrent.TimeUnit;

public interface ProductConstant {
    // 商品存在
    int UNDELETED = 0;
    // 商品不存在
    int DELETED = 1;
    // 更新操作成功
    int UPDATE_SUCCESS = 1;
    // 插入操作成功
    int INSERT_SUCCESS = 1;

    /**
     * redis 相关常量
     */

    // 普通商品过期时间
    Long REDIS_SECONDS = 60L * 60L * 8L;
    // ID不存在的商品存在时间
    Long REDIS_NULL_VALUE_TTL_SECONDS = 60L;
    // redis锁持有时间
    Long REDIS_LOCK_TIME = 10L;
    // 时间单位 秒
    TimeUnit REDIS_TIME_UNIT = TimeUnit.SECONDS;
    // 单次重试时间
    int WAIT_TIME = 1000;
    // 重试次数
    int RETRY_TIMES = 3;
    // 空商品对应的redis value
    String PRODUCT_NOT_EXIST = "product_not_exist";
}
