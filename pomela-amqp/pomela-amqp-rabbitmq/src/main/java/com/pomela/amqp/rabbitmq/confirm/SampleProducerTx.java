package com.pomela.amqp.rabbitmq.confirm;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import com.pomela.amqp.rabbitmq.Configuration;
import com.rabbitmq.client.*;

/**
 * Created by hetao on 15-2-4.
 *
 * tx机制：
 * 1. 消息由produce执行publish后，由produce发送commit给broke后，broke才把这条消息路由给队列
 */
public class SampleProducerTx implements Runnable {
    private final static int MSG_COUNT = 10;
    private static final String EXCHANGE_NAME = "exchange.topic.task";

    private static Connection conn;
    private static Channel channel;

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
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true); //direct topic fanout
            /** 生产者不是必须声明队列 **/
            String queueName = channel.queueDeclare("queue.work.task", true, false, false, null).getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "#.tasks");
            channel.txSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        try {
            for (int i = 0; i < MSG_COUNT; ++i) {
                String msg = "m_" + i;
                System.out.println("Produce: " + msg);
                channel.basicPublish(EXCHANGE_NAME, "rk.tasks", MessageProperties.PERSISTENT_BASIC, msg.getBytes());
                channel.txCommit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("total elapse: " + (System.currentTimeMillis() - begin));
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