package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.pomela.concurrent.threads.daemons.DaemonFromFactory;
import org.pomela.concurrent.threads.daemons.DaemonThreadFactory;

public class DaemonFromFactoryTest {

    public static void main(String[] args) throws InterruptedException {
        //带线程工厂参数
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0; i<10; i++) {
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
