package com.pomela.amqp.rabbitmq.topics;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by hetao on 15-2-10.
 */
public class SampleTopicSubscriber implements Runnable {

    private static final String EXCHANGE_NAME = "exchange.topic.log";

    @Override
    public void run() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setPort(Configuration.PORT);
        cf.setHost(Configuration.HOSTNAME);
        cf.setUsername(Configuration.USERNAME);
        cf.setPassword(Configuration.PASSWORD);
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);

        try {
            Connection conn = cf.newConnection();

            Channel channel = conn.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName, EXCHANGE_NAME, "*.#.rabbit");

            System.out.println(" [*] Waiting for message.");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);

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
