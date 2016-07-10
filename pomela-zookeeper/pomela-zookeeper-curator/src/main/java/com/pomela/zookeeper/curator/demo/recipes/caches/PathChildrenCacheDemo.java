package com.pomela.zookeeper.curator.demo.recipes.caches;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;

/**
 * Created by hetor on 16/3/1.
 *
 * @Description
 * A Path Cache is used to watch a ZNode. Whenever a child is added, updated or
 * removed, the Path Cache will change its state to contain the current set of
 * children, the children's data and the children's state.
 *
 * @Participating-Classes
 * PathChildrenCache
 * PathChildrenCacheEvent
 * PathChildrenCacheListener
 * ChildData
 *
 * @Error-Handling
 * PathChildrenCache instances internally monitor a ConnectionStateListener.
 * If the connection state changes, the cache is reset (the PathChildrenCacheListener will receive a RESET).
 *
 * http://curator.apache.org/curator-recipes/path-cache.html
 */
public class PathChildrenCacheDemo {

	static String path = "/zk-book";

	public static void main(String[] args) throws Exception {
		CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
		final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
		pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
		pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
					case CHILD_ADDED:
						RPIDLogger.info("CHILD_ADDED," + event.getData().getPath());
						break;
					case CHILD_UPDATED:
						RPIDLogger.info("CHILD_UPDATED," + event.getData().getPath());
						break;
					case CHILD_REMOVED:
						RPIDLogger.info("CHILD_REMOVED," + event.getData().getPath());
						break;
					default:
						break;
				}
			}
		});
		client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		Thread.sleep(1000);
		client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
		Thread.sleep(1000);
		client.setData().forPath(path+"/c1", "u".getBytes());
		Thread.sleep(1000);
		client.delete().forPath(path+"/c1");
		Thread.sleep(1000);
		client.delete().forPath(path);
		Thread.sleep(Integer.MAX_VALUE);
	}

}
