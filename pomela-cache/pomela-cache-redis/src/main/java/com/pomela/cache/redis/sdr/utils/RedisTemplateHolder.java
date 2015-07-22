package com.pomela.cache.redis.sdr.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;

/**
 * Created by hetao on 15-7-21.
 */
public class RedisTemplateHolder {
    public final static RedisTemplate<Serializable, Serializable> redisTemplate = RedisRemplateHolder.redisTemplate;
    public final static StringRedisTemplate stringRedisTemplate = RedisRemplateHolder.stringRedisTemplate;

    private static class RedisRemplateHolder {
        private static final RedisTemplate<Serializable, Serializable> redisTemplate =
                (RedisTemplate<Serializable, Serializable>) SpringApplicationContextHolder.ctx.getBean("redisTemplate");
        private static final StringRedisTemplate stringRedisTemplate =
                (StringRedisTemplate) SpringApplicationContextHolder.ctx.getBean("stringRedisTemplate");
    }
}
