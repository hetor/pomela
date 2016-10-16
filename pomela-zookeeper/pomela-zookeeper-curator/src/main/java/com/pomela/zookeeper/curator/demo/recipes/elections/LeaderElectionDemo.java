package com.pomela.zookeeper.curator.demo.recipes.elections;

import com.pomela.zookeeper.curator.core.RPIDLogger;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import com.pomela.zookeeper.curator.core.TopsCuratorFramework;

/**
 * Created by hetor on 16/2/22.
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
 * LeaderSelector
 * LeaderSelectorListener
 * LeaderSelectorListenerAdapter
 * CancelLeadershipException
 *
 * @Error-Handling
 * The LeaderSelectorListener class extends ConnectionStateListener.
 * When the LeaderSelector is started, it adds the listener to the Curator instance.
 * Users of the LeaderSelector must pay attention to any connection state changes.
 * If an instance becomes the leader, it should respond to notification of being SUSPENDED or LOST.
 * If the SUSPENDED state is reported, the instance must assume that it might no longer be the leader until it receives a RECONNECTED state.
 * If the LOST state is reported, the instance is no longer the leader and its takeLeadership method should exit.
 *
 * IMPORTANT: The recommended action for receiving SUSPENDED or LOST is to throw CancelLeadershipException.
 * This will cause the LeaderSelector instance to attempt to interrupt and cancel the thread that is executing the takeLeadership method.
 * Because this is so important, you should consider extending LeaderSelectorListenerAdapter.
 * LeaderSelectorListenerAdapter has the recommended handling already written for you.
 *
 * http://curator.apache.org/curator-recipes/leader-election.html
 */
public class LeaderElectionDemo {

	public static void main(String[] args) throws Exception {
		//@see curator-examples

		final String path = "/curator_recipes_master_path";

		for(int i=0; i<5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
						public void takeLeadership(CuratorFramework client) throws Exception {
							// this callback will get called when you are the leader
							// do whatever leader work you need to and only exit
							// this method when you want to relinquish leadership
							RPIDLogger.info(Thread.currentThread().getName() +": I get Master, start to do task");
							while(true) {
								Thread.sleep(3000);
								RPIDLogger.info(Thread.currentThread().getName() +": doing job");
							}
//				RPIDLogger.info("task is done, release Master");
						}
					};

					LeaderSelector selector = null;
					try {
						selector = new LeaderSelector(TopsCuratorFramework.getInstance().getCuratorFramework(), path,
								listener);
					} catch (Exception e) {
						e.printStackTrace();
					}
					selector.autoRequeue(); // not required, but this is behavior that you
					// will probably expect
					selector.start();
				}
			}, "LEADER-" + i).start();
		}


		while(true) {
			Thread.sleep(Integer.MAX_VALUE);
		}

//		selector.close();
	}
}
