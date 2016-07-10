package com.pomela.zookeeper.curator.demo.recipes.barriers;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * An implementation of the Distributed Barrier ZK recipe.
 * Distributed systems use barriers to block processing of a set of nodes until
 * a condition is met at which time all the nodes are allowed to proceed.
 *
 * 本例通过调用DistributedBarrier.setBarrier()方法来完成Barrier设置,
 * 通过调用DistributedBarrier.waitOnBarrier()方法来等待Barrier释放,
 * 然后在主线程中通过调用DistributedBarrier.removeBarrier()方法来释放Barrier,同时出发所有等待该Barrier的5个线程同时进行各自的业务逻辑.
 *
 * 特点: 主线程控制进入Barrier
 *
 * @Participating-Classes
 * DistributedBarrier
 *
 * @Error-Handling
 * DistributedBarrier instances watch for connection loss and will throw an exception from waitOnBarrier().
 *
 * http://curator.apache.org/curator-recipes/barrier.html
 *
 */
public class BarrierDemo {
	static DistributedBarrier barrier;

	public static void main(String[] args) throws Exception {
		final String path = "/curator_recipes_barrier_path";

		for (int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
						barrier = new DistributedBarrier(client, path);
						RPIDLogger.info(Thread.currentThread().getName() + "号barrier设置");
						barrier.setBarrier(); //设置barrier
						barrier.waitOnBarrier(); //等待barrier释放
						RPIDLogger.info("启动");
					} catch (Exception e) {
						RPIDLogger.error(e);
					}
				}
			}).start();
		}
		Thread.sleep(3000);
		RPIDLogger.info("remove barrier");
		barrier.removeBarrier(); //释放barrier
	}
}
