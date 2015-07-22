package com.pomela.cache.redis.sdr.clients;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.pomela.cache.redis.sdr.utils.RedisTemplateHolder.redisTemplate;

/**
 * Created by hetao on 15-7-22.
 */
public class RedisHashClient {

    public static void updateOrAdd(String key, Serializable field, Serializable value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public static void updateOrAddMulti(String key, Map<Serializable, Serializable> entries) {
        redisTemplate.opsForHash().putAll(key, entries);
    }

    /**
     * if field exists, do nothing
     * if key does not exist, create a new hash table, then add
     * @param key
     * @param field
     * @param value
     */
    public static Boolean addIfAbsent(String key, Serializable field, Serializable value) {
        return redisTemplate.opsForHash().putIfAbsent(key, field, value);
    }

    public static Double incrementOrAdd(String key, Serializable field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    public static Long incrementOrAdd(String key, Serializable field, long delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }


    public static Object get(String key, Serializable field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public static List<Object> getMulti(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    public static Map<Object, Object> entries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public static List<Object> values(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    public static Boolean hasField(String key, Serializable field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    public static Set<Object> fields(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    public static long size(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    public static void delete(String key, Serializable... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public static void scan(String key) {
        //TODO
        //redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().build());
    }

}
