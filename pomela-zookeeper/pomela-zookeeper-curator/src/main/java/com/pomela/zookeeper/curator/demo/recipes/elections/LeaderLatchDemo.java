package com.pomela.zookeeper.curator.demo.recipes.elections;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * In distributed computing, leader election is the process of designating a
 * single process as the organizer of some task distributed among several
 * computers (nodes). Before the task is begun, all network nodes are unaware
 * which node will serve as the "leader," or coordinator, of the task. After a
 * leader election algorithm has been run, however, each node throughout the
 * network recognizes a particular, unique node as the task leader.
 * 
 * NOTE: Curator has two leader election recipes. Which one to use depends on
 * your requirements.
 *
 * @Participating-Classes
 * LeaderLatch
 *
 * @Error-Handling
 * LeaderLatch instances add a ConnectionStateListener to watch for connection problems.
 * If SUSPENDED or LOST is reported, the LeaderLatch that is the leader will report that it is no longer the leader
 * (i.e. there will not be a leader until the connection is re-established).
 * If a LOST connection is RECONNECTED, the LeaderLatch will delete its previous ZNode and create a new one.
 *
 * Users of LeaderLatch must take account that connection issues can cause leadership to be lost. i.e. hasLeadership()
 * returns true but some time later the connection is SUSPENDED or LOST.At that point hasLeadership() will return false.
 * It is highly recommended that LeaderLatch users register a ConnectionStateListener.
 *
 * http://curator.apache.org/curator-recipes/leader-latch.html
 */
public class LeaderLatchDemo {

    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/examples/leader";

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        TestingServer server = new TestingServer();
        try {
            for (int i = 0; i < CLIENT_QTY; ++i) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                LeaderLatch example = new LeaderLatch(client, PATH, "Client #" + i);
                examples.add(example);
                client.start();
                example.start();
            }
            Thread.sleep(20000);
            LeaderLatch currentLeader = null;
            for (int i = 0; i < CLIENT_QTY; ++i) {
                LeaderLatch example = examples.get(i);
                if (example.hasLeadership())
                    currentLeader = example;
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            currentLeader.close();
            examples.get(0).await(2, TimeUnit.SECONDS);
            System.out.println("Client #0 maybe elected as the leader or not although it want to be");
            System.out.println("the new leader is " + examples.get(0).getLeader().getId());

            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Shutting down...");
            for (LeaderLatch exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
            CloseableUtils.closeQuietly(server);
        }
    }
}
