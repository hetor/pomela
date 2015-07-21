package org.pomela.concurrent.threads.simple;

import org.pomela.concurrent.runnables.LiftOff;

/**
 * 用runnable对象创建一个子线程启动执行run方法，main函数所在的主线程继续执行
 * @author hetao
 */
public class LiftOffOneSubThread {

    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff!");
    }
}
