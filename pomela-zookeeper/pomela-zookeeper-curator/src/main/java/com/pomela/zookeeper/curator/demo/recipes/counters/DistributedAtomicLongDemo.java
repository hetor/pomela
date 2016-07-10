package com.pomela.zookeeper.curator.demo.recipes.counters;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import com.pomela.zookeeper.curator.core.TopsCuratorFramework;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;

/**
 * Created by hetor on 16/2/29.
 *
 * @Description
 * A counter that attempts atomic increments. It first tries using optimistic
 * locking. If that fails, an optional InterProcessMutex is taken. For both
 * optimistic and mutex, a retry policy is used to retry the increment.
 *
 * Examine the result AtomicValue: You must first check succeeded() which returns true if the operation succeeded.
 * If false is returned, the operation failed and the atomic was not updated.
 * If the operation succeeded, you can get the value prior to the operation via preValue() and the value after the operation postValue()
 *
 * @Participating-Classes
 * DistributedAtomicLong
 * AtomicValue
 * PromotedToLock
 *
 * @Error-Handling
 * All the atomic instances access the ZooKeeper server for each method call.
 * Therefore, the standard retry mechanism will be applied and any errors
 * executing the operations will result in an Exception being thrown.
 *
 * http://curator.apache.org/curator-recipes/distributed-atomic-long.html
 */
public class DistributedAtomicLongDemo {

	public static void main(String[] args) throws Exception {
		final String path = "/curator_recipes_distatomiclong_path";
		final CuratorFramework client = TopsCuratorFramework.getInstance().getCuratorFramework();
		DistributedAtomicLong distributedAtomicLong = new DistributedAtomicLong(client, path, new RetryNTimes(3, 1000));
		AtomicValue<Long> rc = distributedAtomicLong.add(8L);
		RPIDLogger.info("Result:{}, postValue:{}, preValue:{}", rc.succeeded(), rc.postValue(), rc.preValue());
	}
}
