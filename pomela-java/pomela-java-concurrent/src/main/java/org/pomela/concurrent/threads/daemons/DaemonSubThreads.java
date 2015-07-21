package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.TimeUnit;

/**
 * 如果一个线程是后台线程，那么它创建的任何线程将被自动设置成为后台线程
 * @author hetao
 */
public class DaemonSubThreads {

    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
        //Allow the daemon thread to finish their startup processs
        TimeUnit.SECONDS.sleep(2);
    }
}

class Daemon implements Runnable {
    private Thread[] ts = new Thread[10];

    @Override
    public void run() {
        for(int i=0; i<ts.length; i++) {
            ts[i] = new Thread(new DaemonSpawn());
            ts[i].start();
            System.out.println("DaemonSpawn " + i + " started, ");
        }
        for(int i=0; i<ts.length; i++) {
            System.out.println("ts[" + i + "].isDaemon() = " + ts[i].isDaemon() + ", ");
        }
        while(true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {

    @Override
    public void run() {
        while(true) {
            Thread.yield();
        }
    }
}
