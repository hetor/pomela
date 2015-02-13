package com.pomela.amqp.rabbitmq.routing;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by hetao on 15-2-9.
 */
public class SampleRoutingSubscriber implements Runnable {

    private static final String EXCHANGE_NAME = "exchange.direct.log";
    private final String EXCHANGE_TYPE = "direct";

    @Override
    public void run() {

        ConnectionFactory cf = new ConnectionFactory();
        cf.setVirtualHost(Configuration.VIRTUAL_HOST);
        cf.setPort(Configuration.PORT);
        cf.setUsername(Configuration.USERNAME);
        cf.setPassword(Configuration.PASSWORD);
        cf.setHost(Configuration.HOSTNAME);

        try {
            Connection conn = cf.newConnection();
            Channel channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            String queueName = channel.queueDeclare("queue.log.multi.binding", false, false, true, null).getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "info");
            channel.queueBind(queueName, EXCHANGE_NAME, "warn");
            channel.queueBind(queueName, EXCHANGE_NAME, "error");

            System.out.println(" [*] Subscriber Waiting for message.");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume("queue.log.multi.binding", true, consumer);

            while(true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [x] Subscriber Received '" + message + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
