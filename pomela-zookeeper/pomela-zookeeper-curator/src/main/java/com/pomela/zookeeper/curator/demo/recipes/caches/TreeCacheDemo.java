package com.pomela.zookeeper.curator.demo.recipes.caches;

/**
 * Created by hetor on 16/3/2.
 *
 * @Description
 * A utility that attempts to keep all data from all children of a ZK path
 * locally cached. This class will watch the ZK path, respond to
 * update/create/delete events, pull down the data, etc. You can register a
 * listener that will get notified when changes occur.
 *
 * @Participating-Classes
 * TreeCache
 * TreeCacheListener
 * TreeCacheEvent
 * ChildData
 *
 * @Error-Handling
 * TreeCache instances internally monitor a ConnectionStateListener.
 * If the connection state changes, the cache will receive messages detailing the change.
 *
 * http://curator.apache.org/curator-recipes/tree-cache.html
 */
public class TreeCacheDemo {

}
