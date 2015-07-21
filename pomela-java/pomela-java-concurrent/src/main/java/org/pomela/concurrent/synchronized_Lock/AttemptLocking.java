package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock对象尝试获取锁方法
 * @author hetao
 */
public class AttemptLocking {
    private Lock lock = new ReentrantLock();
    
    public void untimed() {
        if(lock.tryLock()) {
            try {
                // manipulate protected state
                System.out.println("tryLock(): true");
            } finally {
                lock.unlock();
            }
        }else {
            // perform alternative actions
            System.out.println("tryLock(): false");
        }
    }
    
    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(captured) {
            try{
                // manipulate protected state
                System.out.println("tryLock(2, TimeUnit.SECONDS): true");
            }finally {
                lock.unlock();
            }
        }else {
            // perform alternative actions
            System.out.println("tryLock(2, TimeUnit.SECONDS): false");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al = new AttemptLocking();
        al.untimed(); // True -- lock is available
        al.timed(); // True -- lock is available
        // Now create a separate task to grab the lock:
        new Thread() {
            {
                setDaemon(true);
            }
            @Override
            public void run() {
                //只获取锁没有释放锁
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        TimeUnit.SECONDS.sleep(1); // Give the 2nd task a chance
        al.untimed(); // False -- lock grabbed by task
        al.timed(); // False -- lock grabbed by task
    }
}
