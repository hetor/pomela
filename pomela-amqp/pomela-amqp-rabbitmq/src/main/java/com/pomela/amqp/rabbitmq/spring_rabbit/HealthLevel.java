package com.pomela.amqp.rabbitmq.spring_rabbit;

/**
 * Created by hetor on 16/3/31.
 */
/**
 * 应用程序运行时的健康等级
 * 排序： DEAD < ALIVE < SICK < HEALTHY
 */
public enum HealthLevel {

    /* 表示应用处于不可用状态 */
    DEAD,

    /* 表示应用处于已启动后的工作状态 */
    ALIVE,

    /* 表示应用处于故障状态，包含且不限于以下情况：
     *  1、程序出现BUG，导致服务运行异常
     *  2、外部资源故障，如db、zookeeper、mq server等无法连接
     *  3、正常运行所依赖的其它服务不可用
     */
    SICK,

    /* 表示应用处于正常的工作状态 */
    HEALTHY
}

