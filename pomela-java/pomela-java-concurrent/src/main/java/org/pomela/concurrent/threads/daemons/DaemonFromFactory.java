package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {

    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
            e.printStackTrace();
        }
    }
}
