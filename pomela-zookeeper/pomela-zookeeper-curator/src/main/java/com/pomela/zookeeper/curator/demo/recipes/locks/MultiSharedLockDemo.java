package com.pomela.zookeeper.curator.demo.recipes.locks;

import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import locking.FakeLimitedResource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * A container that manages multiple locks as a single entity. When acquire() is
 * called, all the locks are acquired. If that fails, any paths that were
 * acquired are released. Similarly, when release() is called, all locks are
 * released (failures are ignored).
 *
 * @Participating-Classes
 * InterProcessMultiLock
 * InterProcessLock
 *
 * @Error-Handling
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state changes.
 * If a SUSPENDED state is reported you cannot be certain that you still hold the lock unless you subsequently receive a RECONNECTED state.
 * If a LOST state is reported it is certain that you no longer hold the lock.
 *
 * http://curator.apache.org/curator-recipes/multi-shared-lock.html
 */
public class MultiSharedLockDemo {
    private static final String PATH1 = "/examples/locks1";
    private static final String PATH2 = "/examples/locks2";

    public static void main(String[] args) throws Exception {
        FakeLimitedResource resource = new FakeLimitedResource();
        CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();

        InterProcessLock lock1 = new InterProcessMutex(client, PATH1);
        InterProcessLock lock2 = new InterProcessSemaphoreMutex(client, PATH2);

        InterProcessMultiLock lock = new InterProcessMultiLock(Arrays.asList(lock1, lock2));
        if (!lock.acquire(10, TimeUnit.SECONDS)) {
            throw new IllegalStateException("could not acquire the lock");
        }
        System.out.println("has the lock");

        System.out.println("has the lock1: " + lock1.isAcquiredInThisProcess());
        System.out.println("has the lock2: " + lock2.isAcquiredInThisProcess());

        try {
            resource.use(); //access resource exclusively
        } finally {
            System.out.println("releasing the lock");
            lock.release(); // always release the lock in a finally block
        }
        System.out.println("has the lock1: " + lock1.isAcquiredInThisProcess());
        System.out.println("has the lock2: " + lock2.isAcquiredInThisProcess());
    }
}
