package com.pomela.zookeeper.curator.demo.recipes.locks;

import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import locking.FakeLimitedResource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * A counting semaphore that works across JVMs. All processes in all JVMs that
 * use the same lock path will achieve an inter-process limited set of leases.
 * Further, this semaphore is mostly "fair" - each user will get a lease in the
 * order requested (from ZK's point of view).
 * 
 * There are two modes for determining the max leases for the semaphore. In the
 * first mode the max leases is a convention maintained by the users of a given
 * path. In the second mode a SharedCountReader is used as the method for
 * semaphores of a given path to determine the max leases.
 * 
 * If a SharedCountReader is not used, no internal checks are done to prevent
 * Process A acting as if there are 10 leases and Process B acting as if there
 * are 20. Therefore, make sure that all instances in all processes use the same
 * numberOfLeases value.
 * 
 * The various acquire methods return Lease objects that represent acquired
 * leases. Clients must take care to close lease objects (ideally in a finally
 * block) else the lease will be lost. However, if the client session drops
 * (crash, etc.), any leases held by the client are automatically closed and
 * made available to other clients.
 *
 * @Participating-Classes
 * InterProcessSemaphoreV2
 * Lease
 * SharedCountReader
 *
 * @Error-Handling
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state changes.
 * If a SUSPENDED state is reported you cannot be certain that you still hold the lock unless you subsequently receive a RECONNECTED state.
 * If a LOST state is reported it is certain that you no longer hold the lock.
 *
 * http://curator.apache.org/curator-recipes/shared-semaphore.html
 */
public class SharedSemaphoreDemo {

	private static final int MAX_LEASE = 10;

	private static final String PATH = "/examples/locks";

	public static void main(String[] args) throws Exception {
		FakeLimitedResource resource = new FakeLimitedResource();

        CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, PATH, MAX_LEASE);
        Collection<Lease> leases = semaphore.acquire(5);
        System.out.println("get " + leases.size() + " leases");
        Lease lease = semaphore.acquire();
        System.out.println("get another lease");
        resource.use();
        Collection<Lease> leases2 = semaphore.acquire(5, 10, TimeUnit.SECONDS);
        System.out.println("Should timeout and acquire return " + leases2);
        System.out.println("return one lease");
        semaphore.returnLease(lease);
        System.out.println("return another 5 leases");
        semaphore.returnAll(leases);
	}
}
