package org.pomela.concurrent.runnables;

/**
 * 线程调度器倾向于让优先权最高的线程先执行<br>
 * 优先级较低的线程执行的频率较低<br>
 * 通常，应该以默认的优先级运行，试图操纵线程优先级通常是一种错误
 * @author hetao
 */
public class SimplePriority implements Runnable{
    private int countDown = 5;
    @SuppressWarnings("unused")
    private volatile double d;//No optimization
    private int priority;
    
    public SimplePriority(int priority) {
        this.priority = priority;
    }
    
    @Override
    public String toString() {
        //打印线程的名称、优先级、所属的线程组
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while(true) {
            //An expensive, interruptable operation
            for(int i=1; i<100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i%1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if(--countDown == 0) return;
        }
    }
}
