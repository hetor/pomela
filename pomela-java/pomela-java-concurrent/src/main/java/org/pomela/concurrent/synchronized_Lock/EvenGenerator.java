package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 偶数生成器，not thread safe
 * @author hetao
 */
public class EvenGenerator extends IntGenerator {
    
    private int currentEvenValue = 0;//临界共享资源

    @Override
    public int next() {
        ++currentEvenValue; //java中++操作不是原子的，Danger point here
        ++currentEvenValue;
        return currentEvenValue;
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        IntGenerator eg = new EvenGenerator(); //用同一个EvenGenerator
        for(int i=0; i<10; i++) {
            EvenChecker ec = new EvenChecker(eg, i);
            exec.execute(ec);
        }
    }
}
