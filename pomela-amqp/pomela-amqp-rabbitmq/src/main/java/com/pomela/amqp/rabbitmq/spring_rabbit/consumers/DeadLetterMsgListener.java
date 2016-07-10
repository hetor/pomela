package com.pomela.amqp.rabbitmq.spring_rabbit.consumers;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by hetor on 16/3/30.
 */
@Component("dlMsgListener")
public class DeadLetterMsgListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DeadLetterMsgListener.class);

    public void onMessage(Message message) {
        System.out.println(new Date());
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        List xDeath = (ArrayList)headers.get("x-death");
        Map xDeathOne = (HashMap)xDeath.get(0);
        System.out.println(xDeathOne.get("reason"));
        System.out.println(xDeathOne.get("count"));
        System.out.println(xDeathOne.get("exchange"));
        System.out.println(xDeathOne.get("time"));
        System.out.println(xDeathOne.get("routing-keys"));
        System.out.println(xDeathOne.get("queue"));
        System.out.println(message.getMessageProperties().getClusterId());
        System.out.println(message.getMessageProperties().getDeliveryMode());
        System.out.println(new String(message.getBody()));
    }
}
