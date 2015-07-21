package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步的偶数生成器，thread safe
 * @author hetao
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    
    private int currentEvenValue = 0; //临界共享资源

    @Override
    public synchronized int next() {
        ++currentEvenValue; //java中++操作不是原子的，Danger point here
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        IntGenerator eg = new SynchronizedEvenGenerator(); //用同一个EvenGenerator
        for(int i=0; i<10; i++) {
            EvenChecker ec = new EvenChecker(eg, i);
            exec.execute(ec);
        }
    }
}
