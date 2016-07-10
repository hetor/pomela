package com.pomela.amqp.rabbitmq.spring_rabbit.consumers;

import com.pomela.amqp.rabbitmq.spring_rabbit.entities.UpgradeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pomela.amqp.rabbitmq.spring_rabbit.entities.Msg;

/**
 * Created by hetor on 16/3/30.
 */
@Component("sampleConsumer2")
public class SampleConsumer2 {

    private static final Logger logger = LoggerFactory.getLogger(SampleConsumer2.class);

    public void handleMessage(UpgradeMsg msg) {
//        logger.info(msg.getName());
        try {
            while (true) {
                Thread.sleep(3000L);
                logger.info("next round");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("test exception");
    }
}
