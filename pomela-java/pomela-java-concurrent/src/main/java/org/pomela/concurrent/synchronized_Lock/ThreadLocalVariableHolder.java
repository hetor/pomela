package org.pomela.concurrent.synchronized_Lock;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 防止任务在共享资源上产生冲突的第二种方式是根除对变量的共享<br>
 * ThreadLocal对象通常当做静态域存储<br>
 * 当运行这个程序时，每个单独的线程都被分配了自己的存储，因为他们每个都需要跟踪自己的计数值。
 * @author hetao
 */
public class ThreadLocalVariableHolder {

    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>() {
        private Random rand = new Random(47);
        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };
    
    public static void increment() {
        value.set(value.get() + 1);
    }
    
    public static int get() {
        return value.get();
    }
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3); // Run for a while
        exec.shutdownNow(); // All Accessors will quit
    }
}

class Accessor implements Runnable {
    private final int id;
    
    public Accessor(int idn) {
        id = idn;
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    
    @Override
    public String toString() {
        return "#" + id + ": " + 
                ThreadLocalVariableHolder.get();
    }
}
