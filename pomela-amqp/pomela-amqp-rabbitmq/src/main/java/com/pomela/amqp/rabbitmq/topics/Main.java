package com.pomela.amqp.rabbitmq.topics;

import java.util.Random;

/**
 * Created by hetao on 15-2-10.
 */
public class Main {

    public static void main(String[] args) {
        //publish some message
        new Thread(new SampleTopicPublisher("This is topic exchange message numero " + new Random().nextInt())).start();
        new Thread(new SampleTopicPublisher("This is topic exchange message numero " + new Random().nextInt())).start();

        //consume message
        new Thread(new SampleTopicSubscriber()).start();
    }
}
