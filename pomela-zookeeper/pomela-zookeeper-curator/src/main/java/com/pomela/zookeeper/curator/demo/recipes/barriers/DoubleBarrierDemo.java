package com.pomela.zookeeper.curator.demo.recipes.barriers;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * An implementation of the Distributed Double Barrier ZK recipe.
 * Double barriers enable clients to synchronize the beginning and the end of a
 * computation. When enough processes have joined the barrier, processes start
 * their computation and leave the barrier once they have finished.
 * 同一个JVM中使用CyclicBarrier,分布式环境中使用DistributedDoubleBarrier.
 *
 * 与CyclicBarrier非常类似的实现,它们都指定了进入Barrier的成员数的阈值,每个Barrier的参与者都会在调用DistributedDoubleBarrier.enter()
 * 方法之后进行等待,此时处于准备进入状态.一旦准备进入Barrier的成员数达到5个后,所有的成员会被同时出发进入.
 * 之后调用DistributedDoubleBarrier.leave()方法则会再次等待,此时处于准备退出状态.一旦准备退出Barrier的成员数达到5个后,
 * 所有的成员同样会出发退出.
 *
 * 特点: 自动同进同退
 * 
 * @Participating-Classes
 * DistributedDoubleBarrier
 *
 * @Error-Handling
 * DistributedDoubleBarrier instances watch for connection loss and will throw
 * an exception from enter() and/or leave().
 *
 * http://curator.apache.org/curator-recipes/double-barrier.html
 */
public class DoubleBarrierDemo {

	public static void main(String[] args) {
		final String path = "/curator_recipes_barrier_path";

		for (int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
						DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path, 5);
						Thread.sleep(Math.round(Math.random() * 3000));
						RPIDLogger.info(Thread.currentThread().getName() + "号进入barrier");
						barrier.enter();
						RPIDLogger.info("启动...");
						Thread.sleep(Math.round(Math.random() * 3000));
						barrier.leave();
						RPIDLogger.info("退出...");
					} catch (Exception e) {
						RPIDLogger.error(e);
					}
				}
			}).start();
		}
	}
}
