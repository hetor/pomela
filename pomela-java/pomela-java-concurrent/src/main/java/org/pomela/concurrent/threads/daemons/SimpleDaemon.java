package org.pomela.concurrent.threads.daemons;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程不是程序中不可或缺的部分，所有非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台线程<br>
 * 只要有任何非后台线程还在运行，程序就不会终止<br>
 * 必须在线程启动start()之前调用setDaemon()方法，才能把它设置为后台线程
 * @author hetao
 */
public class SimpleDaemon implements Runnable {

    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
            e.printStackTrace();
        }
    }
}
