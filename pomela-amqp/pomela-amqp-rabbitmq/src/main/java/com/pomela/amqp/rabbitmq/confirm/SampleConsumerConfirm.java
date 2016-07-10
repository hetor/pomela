package com.pomela.amqp.rabbitmq.confirm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by hetao on 15-2-4.
 */
public class SampleConsumerConfirm implements Runnable {
    private final static int MSG_COUNT = 400000;

    private static final String EXCHANGE_NAME = "exchange.topic.task";
    private static final String ROUTING_KEY = "#.tasks";
    private static final boolean AUTO_ACK = false;

    private static Connection conn;
    private static Channel channel;
    private static String queueName;

    private static Set<String> msgs = new HashSet<>(MSG_COUNT);

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost(Configuration.VIRTUAL_HOST);
        factory.setUsername(Configuration.USERNAME);
        factory.setPassword(Configuration.PASSWORD);
        factory.setHost(Configuration.HOSTNAME);
        factory.setPort(Configuration.PORT);

        try {
            conn = factory.newConnection();
            channel = conn.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
            //每次只给每个消费者的消息不超过一条
            channel.basicQos(1);

            queueName = channel.queueDeclare("manual.queue.work.task", true, false, false, null).getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            QueueingConsumer consumer = new QueueingConsumer(channel);
            //QueueingConsumer作为异步的回调对象，当服务器异步的把消息传递到消费者时缓存在QueueingConsumer对象里面
            channel.basicConsume(queueName, AUTO_ACK, consumer);

            for(int i=0; i < MSG_COUNT; i++) {
                QueueingConsumer.Delivery delivery;
                try {
                    delivery = consumer.nextDelivery(); //阻塞直到下一条消息到达
                    String msg = new String(delivery.getBody());
                    msgs.add(msg);
                    System.out.println("Consume: " + msg);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Consumed total count: " + msgs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clean() {
        try {
            if(null != channel && channel.isOpen()) {
                channel.close();
            }
            if(null != conn && conn.isOpen()) {
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
