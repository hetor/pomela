package org.pomela.concurrent.callables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 从任务中产生返回值<br>
 * 
 * @author hetao
 */
class TaskWithResult implements Callable<String> {
    private int id;
    
    public TaskWithResult(int id) {
        this.id = id;
    }
    
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        //submit方法返回Future<T>对象，T是Callable<T>从call()方法返回的值的类型
        List<Future<String>> results = new ArrayList<>();
        for(int i=0; i<10; i++) {
            //Callable必须使用ExecutorService.submit()方法调用
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for(Future<String> fs : results) {
            //可以不用isDone()进行检查就直接调用get(),在这种情况下，get()将阻塞，直至结果准备就绪
            if(fs.isDone()) {
                try {
                    System.out.println(fs.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    exec.shutdown();
                }
            }
        }
    }
}
