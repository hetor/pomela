package org.pomela.concurrent.runnables;

public class LiftOff implements Runnable{
    protected int countDown = 10; //default 
    private static int taskCount = 0;
    private final int id = taskCount++;
    
    public LiftOff(){}
    
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    
    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }
    
    @Override
    public void run() {
        while(countDown-- > 0) {
            System.out.print(status());
            //让步-暗示可以让别的线程使用CPU
            //不保证这个暗示将被采纳
            Thread.yield();
            //休眠sleep()方法会抛出InterruptedException异常
            //当前线程进入阻塞状态，线程调度器可以切换到下一个线程
            //Thread.sleep(1000); 
        }
    }
}
