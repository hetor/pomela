package org.pomela.concurrent.join;

/**
 * 如果某个线程在另一个线程t上调用t.join(),此线程将被挂起，直到目标线程t结束(t.isAlive()为false)才恢复
 * 也可以在调用join()时带上一个超时参数，这样如果目标线程在这段时间到期时还没有结束的话，join()方法总能返回
 * 线程t被中断，比如在调用t.interrupt()方法，则线程t结束，线程t被中断后t.Alive()方法依然返回true
 * java.util.concurrent类库包含诸如CyclicBarrier的工具，它们可能比join()更适合
 * 如果Sleeper被中断或者正常结束，Joiner将和Sleeper一同结束
 * @author hetao
 */
class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }
    
    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(getName() + " was interrupted. isAlive: " + isAlive() +
                    " isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    
    public void run() {
        try {
            sleeper.join(1200); //超时参数
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }
        System.out.println(getName() + " join completed");
    }
}

public class Joining {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Sleeper 
            sleepy = new Sleeper("Sleepy", 800), //complete before 超时参数
            sg = new Sleeper("Sg", 1000), //被中断
            grumpy = new Sleeper("Grumpy", 1500); //超过超时参数
        
        Joiner
            dopey = new Joiner("Dopey", sleepy),
            dsg = new Joiner("Dsg", sg),
            doc = new Joiner("Doc", grumpy);
        
        sg.interrupt();
    }
}