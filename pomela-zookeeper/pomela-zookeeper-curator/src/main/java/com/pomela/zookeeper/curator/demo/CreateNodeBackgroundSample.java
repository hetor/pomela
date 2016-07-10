package com.pomela.zookeeper.curator.demo;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hetor on 16/7/10.
 *
 * BackgroundCallback, CuratorEvent
 * 
 *
 * 事件类型CuratorEventType
 *
 * 响应码(int)
 *
 * Executor:
 * 在zookeeper中所有的异步通知事件处理都是由EventThread这个线程来处理的——EventThread线程用于串行处理所有的事件通知.
 * EventThread的"串行处理机制"在绝大部分应用场景下能够保证对事件处理的顺序性,但这个特性也有其弊端,
 * 就是一旦碰上一个复杂的处理单元就会消耗过长的处理时间,从而影响对其他事件的处理.因此在inBackground接口中,
 * 允许用户传入一个Executor实例,这样可以把复杂的事件处理放到一个专门的线程池中去.
 */
public class CreateNodeBackgroundSample {
	static String path = "/zk-book";
	static CountDownLatch countDownLatch = new CountDownLatch(2);
	static ExecutorService tp = Executors.newFixedThreadPool(2);
	static CuratorFramework client;

	static {
		try {
			client = TopsCuratorFramework.getInstance().getCuratorFramework();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Main thread: " + Thread.currentThread().getName());

		//此处传入了自定义的Executor
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				RPIDLogger.info("event[code: " + event.getResultCode() + ", type: " + event.getType() + "] ");
				RPIDLogger.info("Thread of processResult: " + Thread.currentThread().getName());
				countDownLatch.countDown();
			}
		}, tp).forPath(path, "init".getBytes());

		//此处没有传入自定义的Executor
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				RPIDLogger.info("event[code: " + event.getResultCode() + ", type: " + event.getType() + "] ");
				RPIDLogger.info("Thread of processResult: " + Thread.currentThread().getName());
				countDownLatch.countDown();
			}
		}).forPath(path, "init".getBytes());

		countDownLatch.await();
		tp.shutdown();
	}
}
