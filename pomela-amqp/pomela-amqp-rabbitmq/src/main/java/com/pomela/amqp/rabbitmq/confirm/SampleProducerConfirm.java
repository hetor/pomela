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
 * confirm机制：
 * 1. 无法路由的消息，在basic.return之后立即返回confirm
 * 2. 瞬时的消息，在入队列之后立即返回confirm
 * 3. 持久化的消息，在消息持久化到硬盘或者被消费者ack之后，返回confirm
 */
public class SampleProducerConfirm implements Runnable {
    private final static int MSG_COUNT = 1;
    private static final String EXCHANGE_NAME = "exchange.topic.task";

    private static volatile SortedSet<Long> unconfirmedSet = Collections.synchronizedSortedSet(new TreeSet());
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
            String queueName = channel.queueDeclare("ha.queue.work.task", true, false, false, null).getQueue();
            System.out.println("queueName: " + queueName);
            channel.queueBind(queueName, EXCHANGE_NAME, "#.tasks");
            channel.addConfirmListener(new ConfirmListener() {
                public void handleAck(long seqNo, boolean multiple) {
                    if (multiple) {
                        SortedSet<Long> sortedSet = unconfirmedSet.headSet(seqNo + 1);
                        System.out.println("handleAck, seqNo:" + sortedSet + " multiple:" + multiple);
                        sortedSet.clear();
                    } else {
                        System.out.println("handleAck, seqNo:" + seqNo + " multiple:" + multiple);
                        unconfirmedSet.remove(seqNo);
                    }
                }
                public void handleNack(long seqNo, boolean multiple) {
                    // handle the lost messages somehow
                    System.out.println("handle the lost messages somehow " + seqNo + " " + multiple);
                }
            });
            channel.confirmSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        try {
            //MessageProperties.PERSISTENT_TEXT_PLAIN mark our messages as persistent
            for (long i = 0; i < MSG_COUNT; ++i) {
                String msg = "m_" + i;
                System.out.println("Produce: " + msg);
                unconfirmedSet.add(channel.getNextPublishSeqNo());
                channel.basicPublish(EXCHANGE_NAME, "rk.tasks", MessageProperties.PERSISTENT_BASIC, msg.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (unconfirmedSet.size() > 0) {
            System.out.println("still not confirmed: " + unconfirmedSet.size());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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