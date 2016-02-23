package com.pomela.zookeeper.curator.demo.recipes;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.recipes.locks.Revoker;

/**
 * Created by hetor on 16/2/22.
 */
public class SharedReentrantLockDemo {
	public static void main(String[] args) throws Exception {
		CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
		String path = "/my/path/lock";
		InterProcessMutex lock = new InterProcessMutex(client, path);

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

		Thread t0 = new Thread(new Task(0, client, path));
		Thread t1 = new Thread(new Task(1, client, path));
		Thread t2 = new Thread(new Task(2, client, path));

		t0.start();
		t1.start();
		t2.start();
	}

	private static class Task implements Runnable {
		private final Integer i;
		private final CuratorFramework client;
		private final String path;

		Task(Integer i, CuratorFramework client, String path) {
			this.i = i;
			this.client = client;
			this.path = path;
		}

		@Override
		public void run() {
			try {
				Revoker.attemptRevoke(client, path);
				// do some work inside of the critical section here
				RPIDLogger.info("{}: do some work inside of the critical section here", i);
				Thread.sleep(2000L);
				RPIDLogger.info("{}: done", i);
			} catch (Exception e) {
				RPIDLogger.error(e);
			}
		}
	}

}
