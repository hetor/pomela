package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger nextEven = new AtomicInteger(0);

    @Override
    public int next() {
        return nextEven.addAndGet(2);
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        IntGenerator eg = new AtomicEvenGenerator(); //用同一个EvenGenerator
        for(int i=0; i<10; i++) {
            EvenChecker ec = new EvenChecker(eg, i);
            exec.execute(ec);
        }
    }
}
