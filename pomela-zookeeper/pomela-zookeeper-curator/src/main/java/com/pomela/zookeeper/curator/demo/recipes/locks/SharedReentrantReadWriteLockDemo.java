package com.pomela.zookeeper.curator.demo.recipes.locks;

import locking.FakeLimitedResource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * A re-entrant read/write mutex that works across JVMs. Uses Zookeeper to hold
 * the lock. All processes in all JVMs that use the same lock path will achieve
 * an inter-process critical section. Further, this mutex is "fair" each user
 * will get the mutex in the order requested (from ZK's point of view).
 * 
 * A read write lock maintains a pair of associated locks, one for read-only
 * operations and one for writing. The read lock may be held simultaneously by
 * multiple reader processes, so long as there are no writers. The write lock is
 * exclusive.
 * 
 * Reentrancy This lock allows both readers and writers to reacquire read or
 * write locks in the style of a re-entrant lock. Non-re-entrant readers are not
 * allowed until all write locks held by the writing thread/process have been
 * released. Additionally, a writer can acquire the read lock, but not
 * vice-versa. If a reader tries to acquire the write lock it will never
 * succeed.
 * 
 * Lock Downgrading Re-entrancy also allows downgrading from the write lock to a
 * read lock, by acquiring the write lock, then the read lock and then releasing
 * the write lock. However, upgrading from a read lock to the write lock is not
 * possible.
 *
 * @Participating-Classes
 * InterProcessReadWriteLock
 * InterProcessLock
 *
 * @Error-Handling
 * It is strongly recommended that you add a ConnectionStateListener and watch for SUSPENDED and LOST state changes.
 * If a SUSPENDED state is reported you cannot be certain that you still hold the lock unless you subsequently receive a RECONNECTED state.
 * If a LOST state is reported it is certain that you no longer hold the lock.
 *
 * http://curator.apache.org/curator-recipes/shared-reentrant-read-write-lock.html
 * 
 * 一个读写锁管理一对相关的锁。 一个负责读操作，另外一个负责写操作。 读操作在写锁没被使用时可同时由多个进程使用，而写锁使用时不允许读 (阻塞)。
 * 此锁是可重入的。一个拥有写锁的线程可重入读锁，但是读锁却不能进入写锁。 这也意味着写锁可以降级成读锁， 比如请求写锁 --->读锁 ---->释放写锁。
 * 从读锁升级成写锁是不成的。
 */
public class SharedReentrantReadWriteLockDemo {
    private final InterProcessReadWriteLock lock;
    private final InterProcessMutex readLock;
    private final InterProcessMutex writeLock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public SharedReentrantReadWriteLockDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessReadWriteLock(client, lockPath);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!writeLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the writeLock");
        }
        System.out.println(clientName + " has the writeLock");

        if (!readLock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the readLock");
        }
        System.out.println(clientName + " has the readLock too");

        try {
            resource.use(); //access resource exclusively
        } finally {
            System.out.println(clientName + " releasing the lock");
            readLock.release(); // always release the lock in a finally block
            writeLock.release(); // always release the lock in a finally block
        }
    }
}
