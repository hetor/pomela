package org.pomela.concurrent.join;

import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 15/4/6.
 * 如果某个线程在另一个线程t上调用t.join(),此线程将被挂起，直到目标线程t结束(t.isAlive()返回false)才恢复
 * 也可以在调用join()时带上一个超时参数，这样如果目标线程在这段时间到期时还没有结束的话，join()方法总能返回
 * 线程t被中断，比如在调用t.interrupt()方法，则线程t结束，线程t被中断后t.isAlive()方法依然返回true
 * java.util.concurrent类库包含诸如CyclicBarrier的工具，它们可能比join()更适合
 */
public class Join {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t is start");
                    TimeUnit.SECONDS.sleep(5L);
                    System.out.println("t is end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t is alive: " + t.isAlive());
    }
}
