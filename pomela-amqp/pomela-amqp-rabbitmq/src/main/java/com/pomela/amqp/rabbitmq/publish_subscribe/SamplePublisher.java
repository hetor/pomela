package com.pomela.amqp.rabbitmq.publish_subscribe;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by hetao on 15-2-6.
 */
public class SamplePublisher implements Runnable {

    private final String message;
    private static final String EXCHANGE_NAME = "exchange.fanout.message";
    private final String EXCHANGE_TYPE = "fanout";
    private final String ROUTING_KEY = "";

    public SamplePublisher(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);
        cf.setHost(Configuration.HOSTNAME);
        cf.setUsername(Configuration.USERNAME);
        cf.setPassword(Configuration.PASSWORD);
        cf.setPort(Configuration.PORT);

        Connection conn;
        Channel channel;
        try {
            conn = cf.newConnection();

            channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
            System.out.println("[x] Sent '" + message + "'");

            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
