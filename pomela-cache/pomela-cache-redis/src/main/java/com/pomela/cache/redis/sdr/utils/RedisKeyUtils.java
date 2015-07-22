package com.pomela.cache.redis.sdr.utils;

/**
 * Created by hetao on 15-7-21.
 */
public class RedisKeyUtils {
    public static String getUserKey(String uid) {
        return "uid:" + uid;
    }
}
