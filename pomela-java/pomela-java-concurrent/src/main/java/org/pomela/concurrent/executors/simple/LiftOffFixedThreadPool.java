package org.pomela.concurrent.executors.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.pomela.concurrent.runnables.LiftOff;

/**
 * FixedThreadPool一次性预先执行代价高昂的线程分配，限制线程的数量
 * @author hetao
 */
public class LiftOffFixedThreadPool {
    public static void main(String[] args) {
        //Constructor argument is number of threads
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
