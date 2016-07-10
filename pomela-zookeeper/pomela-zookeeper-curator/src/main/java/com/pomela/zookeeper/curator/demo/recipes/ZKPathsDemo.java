package com.pomela.zookeeper.curator.demo.recipes;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by hetor on 16/7/10.
 *
 * ZKPaths提供了一些简单的API来构建ZNode路径,递归创建和删除节点等
 */
public class ZKPathsDemo {
	static String path = "/curator_path_sample";

	public static void main(String[] args) throws Exception {
		final CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();

		ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();

		RPIDLogger.info(ZKPaths.fixForNamespace(path, "/sub"));
		RPIDLogger.info(ZKPaths.makePath(path, "/sub"));

		RPIDLogger.info(ZKPaths.getNodeFromPath("/curator_zkpath_sample/sub1"));
		ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode("/curator_zkpath_sample/sub1");
		RPIDLogger.info(pn.getPath());
		RPIDLogger.info(pn.getNode());

		String dir1 = path + "/child1";
		String dir2 = path + "/child2";
		ZKPaths.mkdirs(zooKeeper, dir1);
		ZKPaths.mkdirs(zooKeeper, dir2);
		RPIDLogger.info(ZKPaths.getSortedChildren(zooKeeper, path).toString());

		ZKPaths.deleteChildren(zooKeeper, path, true);

	}
}
