package com.pomela.amqp.rabbitmq.work_queues;

import java.io.IOException;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by hetao on 15-2-4.
 */
public class SampleConsumer implements Runnable {
    private static final String EXCHANGE_NAME = "exchange.topic.task";
    private static final String ROUTING_KEY = "#.tasks";
    private static final boolean AUTO_ACK = false;

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
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

            //每次只给每个消费者的消息不超过一条
            channel.basicQos(1);

            String queueName = channel.queueDeclare("queue.work.task", true, false, false, null).getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            //QueueingConsumer作为异步的回调对象，当服务器异步的把消息传递到消费者时缓存在QueueingConsumer对象里面
            channel.basicConsume(queueName, AUTO_ACK, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery;
                try {
                    delivery = consumer.nextDelivery(); //阻塞直到下一条消息到达
                    System.out.println("received message: " + new String(delivery.getBody()) + " in thread: " + Thread.currentThread().getName());
                    Thread.sleep(10000);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException ie) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
