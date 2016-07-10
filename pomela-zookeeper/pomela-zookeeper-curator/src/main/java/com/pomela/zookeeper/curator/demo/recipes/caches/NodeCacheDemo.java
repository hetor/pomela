package com.pomela.zookeeper.curator.demo.recipes.caches;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;

/**
 * Created by hetor on 16/3/2.
 *
 * @Description
 * A Node Cache is used to watch a ZNode. Whenever the data is modified or the
 * ZNode is deleted, the Node Cache will change its state to contain the current
 * data (or null if ZNode was deleted).
 *
 * @Participating-Classes
 * NodeCache
 * NodeCacheListener
 * ChildData
 *
 * @Error-Handling
 * NodeCache instances internally monitor a ConnectionStateListener.
 *
 * http://curator.apache.org/curator-recipes/node-cache.html
 */
public class NodeCacheDemo {
	static String path = "/zk-book/nodeCache";

	public static void main(String[] args) throws Exception {
		CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
		client.create()
				.creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath(path, "init".getBytes());
		final NodeCache nodeCache = new NodeCache(client, path, false); //dataIsCompressed是否进行数据压缩
		//start方法的boolean参数默认是false,
		//如果设置为true,那么NodeCache在第一次启动的时候就会立刻从Zookeeper上读取对应节点的数据内容并保存在Cache中.
		nodeCache.start(true);
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				ChildData currentData = nodeCache.getCurrentData();
				if(null != currentData) {
					RPIDLogger.info("Node data update, new data: {}", new String(currentData.getData()));
				} else {
					RPIDLogger.info("Node data deleted");
				}
			}
		});
		client.setData().forPath(path, "u".getBytes());
		Thread.sleep(1000);
		client.delete().deletingChildrenIfNeeded().forPath("/zk-book");
		Thread.sleep(Integer.MAX_VALUE);
	}
}
