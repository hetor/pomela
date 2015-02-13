package com.pomela.amqp.rabbitmq.routing;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by hetao on 15-2-10.
 */
public class SampleRoutingSubscriber2 implements Runnable {
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

            channel.queueDeclare("queue.log.single.binding", false, false, true, null);

            channel.queueBind("queue.log.single.binding", EXCHANGE_NAME, "error");

            System.out.println(" [*] Subscriber2 Waiting for message.");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume("queue.log.single.binding", true, consumer);

            while(true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println(" [x] Subscriber2 Received '" + message + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
