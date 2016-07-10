package com.pomela.zookeeper.curator.demo.recipes.nodes;

/**
 * Created by hetor on 16/3/2.
 *
 * @Description
 * A persistent node is a node that attempts to stay present in ZooKeeper, even
 * through connection and session interruptions.
 *
 * PersistentNodes must be started:node.start();
 * When you are through with the PersistentNode instance, you should call close:node.close(); NOTE: this will delete the node
 *
 * @Participating-Classes
 * PersistentNode
 *
 * @Error-Handling
 * PersistentNode instances internally handle all error states recreating the node as necessary.
 *
 * http://curator.apache.org/curator-recipes/persistent-ephemeral-node.html
 */
public class PersistentEphemeralNodeDemo {
}
