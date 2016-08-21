package com.pomela.zookeeper.common.client;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hetor on 16/7/24.
 */
public class ZookeeperConnectSimple implements Watcher {

	private static CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) {

		System.out.println(zookeeper.getState());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Zookeeper session established");
	}

}
