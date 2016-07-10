package com.pomela.amqp.rabbitmq.spring_rabbit;

/**
 * Created by hetor on 16/3/31.
 */
public interface IHealthProvider {

    /**
     * 提供当前应用运行的健康等级
     */
    HealthLevel getHealthLevel();

    /**
     * 提供当前应用运行的详细健康信息
     * 如非正常等级，需要说明原因
     * 返回信息需要有可读性
     */
    String getHealthInfo();

}