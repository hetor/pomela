package com.pomela.zookeeper.curator.core;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 * 对curator做的标准化定制。该类实现了单例模式，保证一个进程只有一个zookeeper连接。
 *
 * Created by hetor on 16/2/17.
 */
public class TopsCuratorFramework {
	private static Logger logger = LoggerFactory.getLogger(TopsCuratorFramework.class);

	private CuratorFramework client = null;
	private CountDownLatch connectedLatch = new CountDownLatch(1);
	
	public class Node {
		private String path;
		private String data;
		private List<Node> childNodes;
		
		public Node(String path) throws Exception{
			this.path = path;
			this.data = new String(TopsCuratorFramework.this.getData(path), "UTF-8");
		}
		
		public String getPath() {
			return path;
		}
		
		public String getData() {
			return data;
		}
		
		/**
		 * 获取所有的直接子节点
		 * @return
		 * @throws Exception
		 */
		public List<Node> getChildNodes() throws Exception {
			if(childNodes != null)
				return childNodes;
			childNodes = new ArrayList<>();
			for(String childNodePath : listPaths(path)){
				childNodes.add(new Node(path + "/" + childNodePath));
			}
			return childNodes;
		}
		
		/**
		 * 当前节点是否是子节点
		 * @return
		 * @throws Exception
		 */
		public boolean isLeafNode() throws Exception {
			return getChildNodes().size() == 0;
		}
		
	}

	private static class InstanceHolder {
		private static TopsCuratorFramework INSTANCE = new TopsCuratorFramework();
		static {
			INSTANCE.init();
		}
	}

	private void init() {
		//tag the zkSrc
//		TzkClient.setZkSrc(TzkClient.ZkSrc.TopsCuratorFramework);

		RPIDLogger.info("start ....");
		try {
			connectedLatch.await();
		} catch (Exception e) {
			RPIDLogger.error("waitUntilConnected", e);
		}
		RPIDLogger.info("end ....");
	}

	public static TopsCuratorFramework getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public ZooKeeper getZookeeper() {
		assertClient();
		try {
			return getInstance().client.getZookeeperClient().getZooKeeper();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	private TopsCuratorFramework() {
//		String connectionString = TopsConfigReaderProps.getZookeeperService();
//		if (StringUtil.isEmpty(connectionString))
//			return;
//		connectionString = connectionString.trim();
		String connectionString = "10.240.139.162:2181,10.240.137.153:2181,10.240.139.113:2181";
		try {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

			//make canReadOnly to improve available
			client = CuratorFrameworkFactory.builder().canBeReadOnly(true)//
					.connectString(connectionString)
					.sessionTimeoutMs(10 * 1000)
					.connectionTimeoutMs(10 * 1000)
					.retryPolicy(retryPolicy)
					.build();

			try {
				addConnectionStateListener(new ConnectionStateListener() {

					@Override
					public void stateChanged(CuratorFramework client, ConnectionState newState) {
						if (newState == ConnectionState.CONNECTED) {
							RPIDLogger.info("zookeeper is connected");
							if (connectedLatch != null) {
								RPIDLogger.info("connectedLatch  is countDown");
								connectedLatch.countDown();
							}
						}
						if (newState == ConnectionState.LOST) {
							RPIDLogger.info("zookeeper is lost");
						}
						if (newState == ConnectionState.RECONNECTED) {
							RPIDLogger.info("zookeeper is reconnected");
						}
						if (newState == ConnectionState.SUSPENDED) {
							RPIDLogger.info("zookeeper is suspended");
						}
					}

				});
			} catch (Throwable thr) {
				RPIDLogger.error("", thr);
			}
			
			client.start();
			RPIDLogger.info("CuratorFramework constructed !!!");

			//may not  be call for zk down on startup
//			TzkClient.setZkAndSignal(client.getZookeeperClient().getZooKeeper());

		} catch (Exception e) {
			RPIDLogger.error("init CuratorFramework error", e);
			client = null;
		}
	}

	/**
	 * 获取curatorFramework
	 * 
	 * @return
	 * @throws Exception
	 */
	public CuratorFramework getCuratorFramework() throws Exception {
		if (client == null)
			throw new IllegalStateException("没有可用的curatorFramework");
		else
			return InstanceHolder.INSTANCE.client;
	}

	/**
	 * 检查client
	 */
	public void assertClient() {
		if (null == client) {
			throw new IllegalStateException("no usable CuratorFramework instance (client)");
		}
	}

	/**
	 * 增加连接状态监听器
	 * 
	 * @param listener
	 * @throws Exception
	 */
	public void addConnectionStateListener(ConnectionStateListener listener) throws Exception {
		assertClient();
		if (null == listener) {
			throw new IllegalStateException("ConnectionStateListener is null");
		}
		client.getConnectionStateListenable().addListener(listener);
		RPIDLogger.info("ConnectionStateListener added");
	}

	public PathChildrenCache addPathChildrenCache(String nodePath) {
		assertClient();
		if (TZUtil.isEmpty(nodePath))
			throw new IllegalStateException("nodePath is null");
		PathChildrenCache pathChildrenCache = new PathChildrenCache(client, nodePath, true);
		return pathChildrenCache;
	}

	/**
	 * 删除某个节点
	 * 
	 * @param nodePath
	 * @throws Exception
	 */
	public void deleteNode(String nodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		Stat stat = exists(nodePath);
		if (stat != null) {
			RPIDLogger.info("delete -- > {}", nodePath);
			client.delete().forPath(nodePath);
		}
	}

	/**
	 * 删除所有文件
	 * deleteChildrenNode ???
	 */
	public void deleteNodesFromPath(String nodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		Stat stat = exists(nodePath);
		if (stat != null) {
			List<String> listPaths = listPaths(nodePath);
			if (!TZUtil.isEmpty(listPaths)) {
				for (String path : listPaths) {
					deleteNodesFromPath(nodePath + File.separator + path);
				}
			}
			deleteNode(nodePath);
		}
	}

	/**
	 * 创建某个节点 path: 路径 data: 数据 mode: 创建模式 PERSISTENT：创建后只要不删就永久存在
	 * 
	 * EPHEMERAL：会话结束年结点自动被删除
	 * 
	 * SEQUENTIAL：节点名末尾会自动追加一个10位数的单调递增的序号，同一个节点的所有子节点序号是单调递增的
	 * 
	 * PERSISTENT_SEQUENTIAL：结合PERSISTENT和SEQUENTIAL
	 * 
	 * EPHEMERAL_SEQUENTIAL：结合EPHEMERAL和SEQUENTIAL
	 * 
	 * @param nodePath
	 * @param data
	 * @param mode
	 * @throws Exception
	 */
	public void createNode(String nodePath, byte[] data, CreateMode mode) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		if (TZUtil.isEmpty(mode)) {
			throw new IllegalStateException("mode is empty");
		}
		client.create().creatingParentsIfNeeded().withMode(mode).forPath(nodePath, data);
	}

	/**
	 * 获得数据
	 * 
	 * @throws Exception
	 */
	public byte[] getData(String nodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		return client.getData().forPath(nodePath);
	}




	public Stat exists(String nodePath) throws Exception {
		return exists(nodePath, null);
	}

	/**
	 * 检查节点 采用一个watcher
	 * 
	 * @param nodePath
	 * @param watcher
	 * @return
	 * @throws Exception
	 */
	public Stat exists(String nodePath, CuratorWatcher watcher) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		if (TZUtil.isEmpty(watcher)) {
			return client.checkExists().forPath(nodePath);
		} else {
			return client.checkExists().usingWatcher(watcher).forPath(nodePath);
		}
	}

	/**
	 * 检查节点 采用一个watcher
	 * 
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public List<String> listPaths(String nodePath) throws Exception {
		assertClient();
		if (TZUtil.isEmpty(nodePath)) {
			throw new IllegalStateException("nodePath is empty");
		}
		if (nodePath.length() > 2 && nodePath.endsWith("/")) {
			nodePath = nodePath.substring(0, nodePath.length() - 1);
		}
		List<String> list = client.getChildren().forPath(nodePath);
		if (!TZUtil.isEmpty(list)) {
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String str1, String str2) {
					return str1.compareTo(str2);
				}

			});
		}
		return list;
	}
	
	/**
	 * 获取所有的叶子节点
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public List<Node> getAllLeafNodes(String nodePath) throws Exception{
		List<Node> allNodes = new ArrayList<>();
		Node parentNode = new Node(nodePath);
		for(Node childNode : parentNode.getChildNodes()){
			if(childNode.isLeafNode())
				allNodes.add(childNode);
			else allNodes.addAll(getAllLeafNodes(childNode.getPath()));
		}
		return allNodes;
	}
	

//	/**
//	 * 返回一个读写锁
//	 *
//	 * @throws Exception
//	 */
//	public InterProcessMutex getInterProcessMutex(String readWriteLockPath) throws Exception {
//		assertClient();
//		if (TZUtil.isEmpty(readWriteLockPath))
//			throw new IllegalStateException("readWriteLockPath is null");
//		InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, readWriteLockPath);
//		InterProcessMutex writeLock = readWriteLock.writeLock();
//
//		if (writeLock.acquire(2, TimeUnit.SECONDS)) {
//			return writeLock;
//		}
//		return null;
//	}
}
