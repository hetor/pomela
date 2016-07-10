package com.pomela.amqp.rabbitmq.spring_rabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by hetor on 16/3/31.
 */
@Component
public class MsgCenterHealthProvider implements IHealthProvider {

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource
    private RabbitAdmin rabbitAdmin;

    @Override
    public String getHealthInfo() {
        StringBuilder strBuilder = new StringBuilder("");
        strBuilder.append("RabbitMQ queue(sms) message count:");
        Properties smsProp = rabbitAdmin.getQueueProperties("messaging-queue-sms");
        strBuilder.append(smsProp.get("QUEUE_MESSAGE_COUNT"));
        strBuilder.append("\n");
        strBuilder.append("RabbitMQ queue(fax) message count:");
        Properties faxProp = rabbitAdmin.getQueueProperties("messaging-queue-fax");
        strBuilder.append(faxProp.get("QUEUE_MESSAGE_COUNT"));
        strBuilder.append("\n");
        return strBuilder.toString();
    }

    @Override
    public HealthLevel getHealthLevel() {
        return MsgCenterEnv.CONTEXT_LEVEL;
    }

    public static class MsgCenterEnv {
        public static HealthLevel CONTEXT_LEVEL = HealthLevel.DEAD;
    }

}
