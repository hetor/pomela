package com.pomela.amqp.rabbitmq.routing;

import java.util.Random;

/**
 * Created by hetao on 15-2-10.
 */
public class Main {

    public static void main(String[] args) {
        //produce message
        new Thread(new SampleRoutingPublisher("This is warn message numero " + new Random().nextInt(), "warn")).start();
        new Thread(new SampleRoutingPublisher("This is info message numero " + new Random().nextInt(), "info")).start();
        new Thread(new SampleRoutingPublisher("This is error message numero " + new Random().nextInt(), "error")).start();

        //consume messsage
        new Thread(new SampleRoutingSubscriber()).start();
        new Thread(new SampleRoutingSubscriber2()).start();
    }
}
