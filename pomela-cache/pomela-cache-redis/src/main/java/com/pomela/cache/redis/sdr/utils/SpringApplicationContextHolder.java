package com.pomela.cache.redis.sdr.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by hetao on 15-6-5.
 */
public class SpringApplicationContextHolder {
    public static ApplicationContext ctx;

    public static void setCtx(ApplicationContext ctx) {
        SpringApplicationContextHolder.ctx = ctx;
    }
}
