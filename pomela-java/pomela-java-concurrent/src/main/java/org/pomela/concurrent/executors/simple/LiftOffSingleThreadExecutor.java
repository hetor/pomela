package org.pomela.concurrent.executors.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.pomela.concurrent.runnables.LiftOff;

/**
 * 是线程数量为1的FixedThreadPool，适合单个长期任务或多个短期任务，多个任务会排队<br>
 * @author hetao
 */
public class LiftOffSingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
