package com.pomela.amqp.rabbitmq.rpc;

/**
 * Created by hetao on 15-2-10.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        RPCClient fibonacciRpc = new RPCClient();

        System.out.println(" [x] Requesting fib(30)");
        String response = fibonacciRpc.call("30");
        System.out.println(" [.] Got '" + response + "'");

        fibonacciRpc.close();


//        new Thread(new RPCServer()).start();
    }
}
