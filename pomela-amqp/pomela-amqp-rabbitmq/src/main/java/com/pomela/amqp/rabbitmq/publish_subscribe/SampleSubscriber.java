package com.pomela.amqp.rabbitmq.publish_subscribe;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by hetao on 15-2-9.
 */
public class SampleSubscriber implements Runnable {
    private final String EXCHANGE_NAME = "exchange.fanout.message";
    private final String EXCHANGE_TYPE = "fanout";
    private final String ROUTING_KEY = "";
    private final boolean AUTO_ACK = true;

    @Override
    public void run() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(Configuration.HOSTNAME);
        cf.setPort(Configuration.PORT);
        cf.setPassword(Configuration.PASSWORD);
        cf.setUsername(Configuration.USERNAME);
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);

        try {
            Connection conn = cf.newConnection();

            Channel channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);

            System.out.println(" [*] Waiting for messages.");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, AUTO_ACK, consumer);

            while(true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [x] Received '" + message + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
