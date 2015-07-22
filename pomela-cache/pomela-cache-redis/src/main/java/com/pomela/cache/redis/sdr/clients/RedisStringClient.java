package com.pomela.cache.redis.sdr.clients;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.pomela.cache.redis.sdr.utils.RedisTemplateHolder.redisTemplate;

/**
 * take care of the methods that do not set expire time
 * Created by hetao on 15-7-21.
 */
public class RedisStringClient {

    public final static int DEFAULT_EXPIRE_TIME = 30; //1*60

    public static void updateOrAdd(final String key, final Serializable value) {
        updateOrAdd(key, value, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public static void updateOrAdd(final String key, final Serializable value, final long expireTime, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    public static void updateOrAddMulti(Map<String, Serializable> entities) {
        redisTemplate.opsForValue().multiSet(entities);
    }

    public static Boolean addIfAbsent(final String key, final Serializable value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static Boolean addIfAbsentMulti(Map<String, Serializable> entities) {
        return redisTemplate.opsForValue().multiSetIfAbsent(entities);
    }

    public static void replaceOrAdd(final String key, final Serializable value, final long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    public static Boolean setBitOrAdd(final String key, long offset, final boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    public static Integer appendOrAdd(final String key, final String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    public static Double incrementOrAdd(final String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public static Long incrementOrAdd(final String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public static String read(final String key, final long start, final long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    public static Serializable read(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Serializable readAndSet(final String key, final Serializable value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public static Boolean readBit(final String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    public static List<Serializable> readMulti(Collection<Serializable> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
