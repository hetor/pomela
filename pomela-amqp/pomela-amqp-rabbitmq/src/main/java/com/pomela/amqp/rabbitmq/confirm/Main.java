package com.pomela.amqp.rabbitmq.confirm;

/**
 * Created by hetao on 15-2-4.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        produceConfirm();
//        produceTx();
//        consume();
    }


    private static void produceConfirm() throws InterruptedException {
        Thread p0 = new Thread(new SampleProducerConfirm());
        p0.start();
        Thread p1 = new Thread(new SampleProducerConfirm());
        p1.start();
        Thread p2 = new Thread(new SampleProducerConfirm());
        p2.start();
        p0.join();
        p1.join();
        p2.join();
        SampleProducerConfirm.clean();
    }

    private static void produceTx() throws InterruptedException {
        Thread p0 = new Thread(new SampleProducerTx());
        p0.start();
        p0.join();
        SampleProducerTx.clean();
    }

    private static void consume() throws InterruptedException {
        Thread c0 = new Thread(new SampleConsumerConfirm());
        c0.start();
//        Thread c1 = new Thread(new SampleConsumerConfirm());
//        c1.start();
//        Thread c2 = new Thread(new SampleConsumerConfirm());
//        c2.start();
        c0.join();
//        c1.join();
//        c2.join();
        SampleConsumerConfirm.clean();
    }
}