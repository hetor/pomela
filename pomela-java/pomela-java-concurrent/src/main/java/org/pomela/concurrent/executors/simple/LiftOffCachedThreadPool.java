package org.pomela.concurrent.executors.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.pomela.concurrent.runnables.LiftOff;

/**
 * CachedThreadPool在程序执行过程中通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程<br>
 * Executor的首选，只有当这种方式出问题时才需要切换到FixedThreadPool<br>
 * @author hetao
 */
public class LiftOffCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i<5; i++) {
            exec.execute(new LiftOff());
        }
        //1.shutdown()方法调用防止新任务被提交给这个Executor，
        //2.当前线程继续运行shutdown()被调用之前提交的所有任务
        exec.shutdown();
    }
}
