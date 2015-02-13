package com.pomela.amqp.rabbitmq.routing;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by hetao on 15-2-9.
 */
public class SampleRoutingPublisher implements Runnable {

    private final String message;
    private final String severity;
    private static final String EXCHANGE_NAME = "exchange.direct.log";
    private final String EXCHANGE_TYPE = "direct";

    public SampleRoutingPublisher(String message, String severity) {
        this.message = message;
        this.severity = severity;
    }

    @Override
    public void run() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);
        cf.setPort(Configuration.PORT);
        cf.setUsername(Configuration.USERNAME);
        cf.setPassword(Configuration.PASSWORD);
        cf.setHost(Configuration.HOSTNAME);

        Connection conn;

        try {
            conn = cf.newConnection();

            Channel channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());

            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
