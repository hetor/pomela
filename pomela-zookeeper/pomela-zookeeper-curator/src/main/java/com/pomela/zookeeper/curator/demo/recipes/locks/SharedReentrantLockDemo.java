package com.pomela.zookeeper.curator.demo.recipes.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;

/**
 * Created by hetor on 16/2/22.
 *
 * @Description
 * Fully distributed locks that are globally synchronous, meaning at any snapshot in time no two clients think they hold the same lock.
 * A InterProcessMutex instance is reusable. i.e. don't create a new instance every time. Re-use a single instance.
 * InterProcessMutex supports a cooperative revocation mechanism as described on the ZooKeeper recipes wiki.
 *
 * @Participating-Classes
 * InterProcessMutex
 *
 * @Error-Handling
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state changes.
 * If a SUSPENDED state is reported you cannot be certain that you still hold the lock unless you subsequently receive a RECONNECTED state.
 * If a LOST state is reported it is certain that you no longer hold the lock.
 *
 * http://curator.apache.org/curator-recipes/shared-reentrant-lock.html
 */
public class SharedReentrantLockDemo {
	final static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		//NOTE: A InterProcessMutex instance is reusable. i.e. don't create a new instance every time. Re-use a single instance.
		final InterProcessMutex lock = new InterProcessMutex(TopsCuratorFramework.getInstance().getCuratorFramework(),
				"/curator_recipes_lock_path");

		Thread t0 = new Thread(new Task(0, lock));
		Thread t1 = new Thread(new Task(1, lock));
		Thread t2 = new Thread(new Task(2, lock));

		t0.start();
		t1.start();
		t2.start();

		countDownLatch.countDown();
	}

	private static class Task implements Runnable {
		private final Integer i;
		private final InterProcessMutex lock;

		Task(Integer i, InterProcessMutex lock) {
			this.i = i;
			this.lock = lock;
		}

		@Override
		public void run() {
			long maxWait = 3;
			try {
				countDownLatch.await();
				if (lock.acquire(maxWait, TimeUnit.SECONDS)) {
					try {
						// do some work inside of the critical section here
						RPIDLogger.info("{}: do some work inside of the critical section here", i);
						Thread.sleep(2000L);
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
