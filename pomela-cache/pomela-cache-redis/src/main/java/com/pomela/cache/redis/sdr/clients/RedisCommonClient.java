package com.pomela.cache.redis.sdr.clients;

import java.io.Serializable;
import java.util.Collection;

import static com.pomela.cache.redis.sdr.utils.RedisTemplateHolder.redisTemplate;

/**
 * Created by hetao on 15-7-21.
 */
public class RedisCommonClient {

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    public static void deleteMulti(Collection<Serializable> keys) {
        redisTemplate.delete(keys);
    }
}
