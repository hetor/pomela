package com.pomela.amqp.rabbitmq.topics;

import com.pomela.amqp.rabbitmq.Configuration;
import com.pomela.amqp.rabbitmq.publish_subscribe.SamplePublisher;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by hetao on 15-2-10.
 */
public class SampleTopicPublisher implements Runnable {

    private static final String EXCHANGE_NAME = "exchange.topic.log";
    private final String message;


    public SampleTopicPublisher(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(Configuration.HOSTNAME);
        cf.setPassword(Configuration.PASSWORD);
        cf.setUsername(Configuration.USERNAME);
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);
        cf.setPort(Configuration.PORT);

        try {
            Connection conn = cf.newConnection();

            Channel channel = conn.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, "quick.white.rabbit", null, message.getBytes());

            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
