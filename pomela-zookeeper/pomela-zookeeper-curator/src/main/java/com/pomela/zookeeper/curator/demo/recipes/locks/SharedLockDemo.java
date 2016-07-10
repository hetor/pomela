package com.pomela.zookeeper.curator.demo.recipes.locks;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * Fully distributed locks that are globally synchronous, meaning at any
 * snapshot in time no two clients think they hold the same lock. Note: unlike
 * InterProcessMutex this lock is not reentrant.
 *
 * @Participating-Classes
 * InterProcessSemaphoreMutex
 *
 * @Error-Handling
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state changes.
 * If a SUSPENDED state is reported you cannot be certain that you still hold the lock unless you subsequently receive a RECONNECTED state.
 * If a LOST state is reported it is certain that you no longer hold the lock.
 *
 * http://curator.apache.org/curator-recipes/shared-lock.html
 */
public class SharedLockDemo {
    public static void main(String[] args) throws Exception {
        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(TopsCuratorFramework.getInstance().getCuratorFramework(),
                "/my/path/lock");

        Thread t0 = new Thread(new Task(0, lock));
        Thread t1 = new Thread(new Task(1, lock));
        Thread t2 = new Thread(new Task(2, lock));

        t0.start();
        t1.start();
        t2.start();
    }

    private static class Task implements Runnable {
        private final Integer i;
        private final InterProcessSemaphoreMutex lock;

        Task(Integer i, InterProcessSemaphoreMutex lock) {
            this.i = i;
            this.lock = lock;
        }

        @Override
        public void run() {
            long maxWait = 3;
            try {
                if (lock.acquire(maxWait, TimeUnit.SECONDS)) {
                    try {
                        // do some work inside of the critical section here
                        RPIDLogger.info("{}: do some work inside of the critical section here", i);
                        Thread.sleep(1000L);
                        if(lock.acquire(maxWait, TimeUnit.SECONDS)) {
                            try {
                                RPIDLogger.info("{} reentrant", i);
                            } finally {
                                lock.release();
                            }
                        } else {
                            throw new IllegalStateException("lock not reentrant");
                        }
                        RPIDLogger.info("{}: done", i);
                    } finally {
                        lock.release();
                    }
                } else {
                    RPIDLogger.info("{}: lock wait timeout", i);
                }
            } catch (Exception e) {
                RPIDLogger.error(e);
            }
        }
    }
}
