package com.pomela.amqp.rabbitmq.work_queues;

import java.util.Random;

/**
 * Created by hetao on 15-2-4.
 */
public class Main {
    public static void main(String[] args) {
        // producing some messages
        for (int i = 0; i < 5; i++) {
            final String message = "This is message numero " + new Random().nextInt();
            SampleProducer producer = new SampleProducer(message);
            new Thread(producer).start();
        }

        new Thread(new SampleConsumer()).start();
    }
}