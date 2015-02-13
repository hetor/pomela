package com.pomela.amqp.rabbitmq.rpc;


import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by hetao on 15-2-10.
 */
public class RPCServer implements Runnable {
    private static final String RPC_QUEUE_NAME = "rpc_queue";

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            System.out.println(" [x] Awaiting RPC requests");

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();

                AMQP.BasicProperties props = delivery.getProperties();
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(props.getCorrelationId())
                        .build();

                String message = new String(delivery.getBody());
                int n = Integer.parseInt(message);
                System.out.println(" [.] fib(" + message + ")");

                String response = "" + fib(n);

                channel.basicPublish("", props.getReplyTo(), replyProps, response.getBytes());

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int fib(int n) throws Exception {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
