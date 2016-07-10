package com.pomela.zookeeper.curator.demo.recipes.counters;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * Manages a shared integer. All clients watching the same path will have the
 * up-to-date value of the shared integer (considering ZK's normal consistency
 * guarantees).
 *
 * @Participating-Classes
 * SharedCount
 * SharedCountReader
 * SharedCountListener
 *
 * @Error-Handling
 * The SharedCountListener class extends ConnectionStateListener.
 * When the SharedCount is started, it adds the listener to the Curator instance.
 * Users of the SharedCount must pay attention to any connection state changes.
 *
 * If the SUSPENDED state is reported, the instance must assume that, until it receives a RECONNECTED state,
 * the count is no longer accurate and isn't being updated. If the LOST state is reported, the count is permanently down.
 *
 * http://curator.apache.org/curator-recipes/shared-counter.html
 */
public class SharedCounterDemo {
}
