package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显式的偶数生成器，thread safe
 * @author hetao
 */
public class LockEvenGenerator extends IntGenerator {
    
    private int currentEvenValue = 0;//临界共享资源
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEvenValue; //java中++操作不是原子的，Danger point here
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        }finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        IntGenerator eg = new LockEvenGenerator(); //用同一个EvenGenerator
        for(int i=0; i<10; i++) {
            EvenChecker ec = new EvenChecker(eg, i);
            exec.execute(ec);
        }
    }
}
