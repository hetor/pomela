package com.pomela.zookeeper.curator.demo.recipes.locks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.RevocationListener;
import org.apache.curator.framework.recipes.locks.Revoker;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;

import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/22.
 *
 * Error Handling:
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state
 * changes. If a SUSPENDED state is reported you cannot be certain that you
 * still hold the lock unless you subsequently receive a RECONNECTED state. If a
 * LOST state is reported it is certain that you no longer hold the lock.
 */
public class SharedReentrantLockDemoRevocable {
	public static void main(String[] args) throws Exception {
		final CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
		final String path = "/my/path/lock";

		Thread t0 = new Thread() {
			@Override
			public void run() {
				long maxWait = 3;
				try {
					final InterProcessMutex lock = new InterProcessMutex(client, path);
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

					if (lock.acquire(maxWait, TimeUnit.SECONDS)) {
						try {
							// do some work inside of the critical section here
							RPIDLogger.info("{}: do some work inside of the critical section here", 0);
							Thread.sleep(3000L);
							RPIDLogger.info("{}: done", 0);
						} finally {
							lock.release();
						}
					} else {
						RPIDLogger.info("{}: lock wait timeout", 0);
					}
				} catch (Exception e) {
					RPIDLogger.error(e);
				}
			}
		};

		Thread t1 = new Thread(){
			@Override
			public void run() {
				long maxWait = 3;
				try {
					Thread.sleep(1000L);
					Revoker.attemptRevoke(client, path);
					final InterProcessMutex lock = new InterProcessMutex(client, path);
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
					if (lock.acquire(maxWait, TimeUnit.SECONDS)) {
						try {
							// do some work inside of the critical section here
							RPIDLogger.info("{}: do some work inside of the critical section here", 1);
							Thread.sleep(3000L);
							RPIDLogger.info("{}: done", 1);
						} finally {
							lock.release();
						}
					} else {
						RPIDLogger.info("{}: lock wait timeout", 1);
					}
				} catch (Exception e) {
					RPIDLogger.error(e);
				}
			}
		};

		t0.start();
		t1.start();
	}
}
