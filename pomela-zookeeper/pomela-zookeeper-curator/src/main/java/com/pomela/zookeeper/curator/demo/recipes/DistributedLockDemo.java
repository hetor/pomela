package com.pomela.zookeeper.curator.demo.recipes;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/22.
 */
public class DistributedLockDemo {
	public static void main(String[] args) throws Exception {
		//NOTE: A InterProcessMutex instance is reusable. i.e. don't create a new instance every time. Re-use a single instance.
		InterProcessMutex lock = new InterProcessMutex(TopsCuratorFramework.getInstance().getCuratorFramework(),
				"/my/path/lock");

		lock.makeRevocable(new RevocationListener<InterProcessMutex>() {
			@Override
			public void revocationRequested(InterProcessMutex forLock) {
				try {
					RPIDLogger.info("request&do revoke lock");
					forLock.release();
					RPIDLogger.info("revoke lock done");
				} catch (Exception e) {
					RPIDLogger.error(e);
				}
			}
		});

		Thread t0 = new Thread(new Task(0, lock));
		Thread t1 = new Thread(new Task(1, lock));
		Thread t2 = new Thread(new Task(2, lock));
		Thread t3 = new Thread(new Task(3, lock));
		Thread t4 = new Thread(new Task(4, lock));
		Thread t5 = new Thread(new Task(5, lock));
		Thread t6 = new Thread(new Task(6, lock));

		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
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
			long maxWait = 10;
			try {
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
