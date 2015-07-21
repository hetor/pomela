package org.pomela.concurrent.uncaughtException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @hetao
 * 1.Thread.UncaughtExceptionHandler.uncaughtException方法会在线程因未捕获的异常而临近死亡时被调用<br>
 * 2.创建ThreadFactory，在新创建的Thread对象上附着一个Thread.UncaughtExceptionHandler<br>
 */

/**
 * Runnable
 */
class ExceptionRunnable2 implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

/**
 * Thread.UncaughtExceptionHandler
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Name: " + t.getName() + " Caught: " + e);
    }
}

/**
 * ThreadFactory
 */
class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

/**
 * 
 * @author hetao
 */
public class CaptureUncaughtException {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionRunnable2());
        
        //1.如果在代码中处处使用相同的异常处理器，那么更简单的方式是在Thread类中设置一个静态域，并将这个处理器设置为默认的未捕获异常处理器
        //2.系统会先检查线程专有的异常处理器，若没有，检查线程组专有的异常处理器，若没有，调用Thread类默认的异常处理器
//        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }
}
