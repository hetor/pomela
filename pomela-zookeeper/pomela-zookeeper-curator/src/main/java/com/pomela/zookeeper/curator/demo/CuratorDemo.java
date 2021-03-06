package com.pomela.zookeeper.curator.demo;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by hetor on 16/2/17.
 */
public class CuratorDemo {

    private static final CuratorFramework client;

    static {
        //指数补偿
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //You only need one CuratorFramework object for each ZooKeeper cluster you are connecting to
        client = CuratorFrameworkFactory.builder()
                .canBeReadOnly(true)
                .connectString("10.240.139.162:2181,10.240.137.153:2181,10.240.139.113:2181")
                .sessionTimeoutMs(10 * 1000)
                .connectionTimeoutMs(10 * 1000) //连接创建超时时间
                .retryPolicy(retryPolicy)
                .build();

        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if(ConnectionState.CONNECTED == newState) {
                    System.out.println("zk connected");
                } else if(ConnectionState.LOST == newState) {
                    System.out.println("zk lost");
                } else if(ConnectionState.RECONNECTED == newState) {
                    System.out.println("zk reconnected");
                } else if(ConnectionState.SUSPENDED == newState) {
                    System.out.println("zk suspend");
                } else if(ConnectionState.READ_ONLY == newState) {
                    System.out.println("zk readOnly");
                } else {
                    System.out.println(newState.name());
                }
            }
        });

        client.start();
    }

    public static void main(String[] args) {
        String path = "/my/path";
        try {
            //check exist
            Stat stat = client.checkExists().forPath(path);

            //delete if exist
            if(null != stat) {
//                client.delete().forPath(path);
//                client.delete().withVersion(stat.getVersion()).forPath(path); //强制指定版本进行删除
//                client.delete().guaranteed().forPath(path); //curator引入失败重试机制,只要客户端会话有效,会重试直到删除成功
                client.delete().deletingChildrenIfNeeded().forPath(path);
            }

            //create node
            String s = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath(path, "Hello World!".getBytes());
            RPIDLogger.info("create path result: {}", s);

            //get data
            byte[] data = client.getData().forPath(path);
//            Stat stat1 = new Stat();
//            byte[] bytes = client.getData().storingStatIn(stat1).forPath(path); //同时获取该节点stat
            RPIDLogger.info("get path data: {}", new String(data, "UTF-8"));

            //set date
            client.setData().forPath(path);
//            client.setData().withVersion(stat.getVersion()).forPath(path); //强制指定版本进行更新
        } catch (Exception e) {
            RPIDLogger.error(e);
        }
    }
}
