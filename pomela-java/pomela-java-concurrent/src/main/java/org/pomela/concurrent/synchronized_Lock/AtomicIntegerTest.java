package org.pomela.concurrent.synchronized_Lock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性变量类：AtomicInteger, AtomicLong, AtomicReference<br>
 * 它们提供了原子性条件更新操作：boolean compareAndSet(expectedValue, updateValue)<br>
 * 通常依赖于锁要更安全一些（synchronized或者Lock）
 * @author hetao
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    
    public int getValue() {
        return i.get();
    }
    
    private void evenIncrement() {
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while(true)
            evenIncrement();
    }
    
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000); //Terminate after 5 seconds
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);
        while(true) {
            int val = ait.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

}
