package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.TimeUnit;

import org.pomela.concurrent.threads.daemons.SimpleDaemon;

public class SimpleDaemonTest {

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<10; i++) {
            Thread daemon = new Thread(new SimpleDaemon());
            daemon.setDaemon(true); //Must call before start()
            daemon.start();
        }
        System.out.println("All daemon started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
