package com.pomela.amqp.rabbitmq.work_queues;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * Created by hetao on 15-2-4.
 */
public class SampleProducer implements Runnable {
    private final String message;

    private static final String EXCHANGE_NAME = "exchange.topic.task";

    public SampleProducer(final String message) {
        this.message = message;
    }

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost(Configuration.VIRTUAL_HOST);
        factory.setUsername(Configuration.USERNAME);
        factory.setPassword(Configuration.PASSWORD);
        factory.setHost(Configuration.HOSTNAME);
        factory.setPort(Configuration.PORT);
        Connection conn;

        try {
            conn = factory.newConnection();
            Channel channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true); //direct topic fanout
            /** 生产者不是必须声明队列 **/
//            String queueName = channel.queueDeclare("queue.work.task", true, false, false, null).getQueue();
//            channel.queueBind(queueName, EXCHANGE_NAME, "#.tasks");
            System.out.println("Producing message: " + message + " in thread: " + Thread.currentThread().getName());

            //MessageProperties.PERSISTENT_TEXT_PLAIN mark our messages as persistent
            channel.basicPublish(EXCHANGE_NAME, "foo.aa", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}