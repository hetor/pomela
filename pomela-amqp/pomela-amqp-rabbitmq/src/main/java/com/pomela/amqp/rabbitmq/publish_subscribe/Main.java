package com.pomela.amqp.rabbitmq.publish_subscribe;

import java.util.Random;

/**
 * Created by hetao on 15-2-9.
 */
public class Main {

    public static void main(String[] args) {
        //publish some messages
        for (int i = 0; i < 1; i++) {
            final String message = "This is message numero " + new Random().nextInt();
            SamplePublisher publisher = new SamplePublisher(message);
            new Thread(publisher).start();
        }

        //subscribe
        Thread subscriberThread = new Thread(new SampleSubscriber());
        subscriberThread.start();
    }
}
