package com.pomela.cache.redis.sdr.clients;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.pomela.cache.redis.sdr.utils.RedisTemplateHolder.redisTemplate;

/**
 * Created by hetao on 15-7-22.
 */
public class RedisListClient {

    /**
     * 压栈
     * @param key
     * @param value
     * @return
     */
    public static Long push(String key, Serializable value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出栈
     * @param key
     * @return
     */
    public static Serializable pop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public static Serializable popBlock(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForList().leftPop(key, timeout, timeUnit);
    }

    /**
     * 入队
     * @param key
     * @param value
     * @return
     */
    public static Long in(String key, Serializable value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 出队
     * @param key
     * @return
     */
    public static Serializable out(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 栈/队列长
     * @param key
     * @return
     */
    public static Long length(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 范围检索
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<Serializable> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除
     * @param key
     * @param i
     * @param value
     */
    public static void remove(String key, long i, Serializable value) {
        redisTemplate.opsForList().remove(key, i, value);
    }

    /**
     * 检索
     * @param key
     * @param index
     * @return
     */
    public static Serializable index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 置值
     * @param key
     * @param index
     * @param value
     */
    public static void set(String key, long index, Serializable value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 裁剪
     * @param key
     * @param start
     * @param end
     */
    public static void trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }
}
