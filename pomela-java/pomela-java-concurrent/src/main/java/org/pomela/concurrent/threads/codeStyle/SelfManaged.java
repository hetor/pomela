package org.pomela.concurrent.threads.codeStyle;

/**
 * Created by hetor on 15/4/6.
 * 自管理的Runnable
 * 在构造器中启动线程可能会变得有问题，因为另一个任务可能会在构造器结束之前开始执行
 * ，这意味着该任务能够访问处于不稳定状态的对象
 */
public class SelfManaged implements Runnable {

    public Thread t = new Thread(this);

    private int countDown = 5;

    public SelfManaged() {
        t.start();
    }

    @Override
    public void run() {
        while(countDown-- > 0) {
            System.out.println(t.getName() + "-" + countDown);
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<5; i++) {
            new SelfManaged();
        }
    }
}
