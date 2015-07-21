package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程在不执行finally语句的情况下就会终止其run()方法
 * @author hetao
 */
class ADaemon implements Runnable {
    @Override
    public void run() {
        System.out.println("Starting ADaemon");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("finally block should always run?");
        }
    }
}

public class DaemonFinal{

    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
