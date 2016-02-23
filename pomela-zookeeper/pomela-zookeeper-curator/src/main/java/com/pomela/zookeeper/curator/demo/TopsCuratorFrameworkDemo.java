package com.pomela.zookeeper.curator.demo;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * Created by hetor on 16/2/21.
 */
public class TopsCuratorFrameworkDemo {
    public static void main(String[] args) throws Exception {
        TopsCuratorFramework client = TopsCuratorFramework.getInstance();

        String path = "/my/path";
        if(null != client.exists(path)) {
            RPIDLogger.debug("client delete node -> {}", path);
            client.deleteNode(path);
        }

        String dataStr = "Hello Zookeeper & Curator !!!";
        client.createNode(path, dataStr.getBytes("UTF-8"), CreateMode.EPHEMERAL);
        RPIDLogger.debug("client create node -> path:{}, data:{}", path, dataStr);

        byte[] data = client.getData(path);

        RPIDLogger.info("client get date -> {}", new String(data));
    }
}
