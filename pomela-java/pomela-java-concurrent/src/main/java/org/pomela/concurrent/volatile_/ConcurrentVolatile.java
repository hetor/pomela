package org.pomela.concurrent.volatile_;

import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 15/6/11.
 */
public class ConcurrentVolatile {

    private static volatile long count = 0;



    private static class Task implements Runnable {

        @Override
        public void run() {
            for(int i=0; i<10000; i++) { //10000时出现并发问题
                count++; //不是原子操作，包含好几条CPU指令，可能存在并发问题
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Thread thread0 = new Thread(new Task());
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        Thread thread3 = new Thread(new Task());
        Thread thread4 = new Thread(new Task());
        Thread thread5 = new Thread(new Task());

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println(count);
    }
}
