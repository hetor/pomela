package org.pomela.concurrent.uncaughtException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.线程的本质特性，使得你不能捕获从线程中逃逸的异常<br>
 * 2.main()方法中的try-catch语句块不能捕获异常<br>
 * 3.需要特殊的步骤来捕获这种错误的异常
 * @author hetao
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }
    
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            //This statement will NOT execute!
            System.out.println("Exception has been handled!");
        }
    }
}
