package org.pomela.concurrent.synchronized_Lock;

/**
 * Created by hetor on 15/4/6.
 * 同一个互斥锁可以被同一个任务多次获得
 */
public class MultiLock {

    public synchronized void f1(int count) {
        if(count-- > 0) {
            System.out.println("f1() call f2() with count " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if(count-- > 0) {
            System.out.println("f2() call f1() with count " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        MultiLock multiLock = new MultiLock();
        new Thread() {
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }.start();
    }
}
