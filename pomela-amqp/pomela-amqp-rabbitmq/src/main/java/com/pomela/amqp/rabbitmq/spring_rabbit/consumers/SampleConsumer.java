package com.pomela.amqp.rabbitmq.spring_rabbit.consumers;

import com.pomela.amqp.rabbitmq.spring_rabbit.entities.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

/**
 * Created by hetor on 16/3/30.
 */
@Component("sampleConsumer")
public class SampleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SampleConsumer.class);

    public void handleMessage(Msg msg) {
        logger.info(msg.getName());
//        try {
//            while (true) {
//                Thread.sleep(3000L);
//                logger.info("next round");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        throw new RuntimeException("test exception");
    }
}
