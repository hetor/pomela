package com.pomela.amqp.rabbitmq.spring_rabbit.producers;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.pomela.amqp.rabbitmq.spring_rabbit.entities.Msg;

/**
 * Created by hetor on 16/3/31.
 */

@Component
public class SampleProducer {

    @Resource(type=AmqpTemplate.class, name = "payTopicTemplate")  //payTopicTemplate
    private AmqpTemplate template;

    public void send(Msg msg) throws IOException {
        try{
            template.convertAndSend("ha.exchange.topic.task", "msg.pay.callback.alipay", msg);
//            template.convertAndSend("ha.exchange.fanout.pay", "", msg);
        }catch(Exception e){
            throw e;
        }
    }
}
