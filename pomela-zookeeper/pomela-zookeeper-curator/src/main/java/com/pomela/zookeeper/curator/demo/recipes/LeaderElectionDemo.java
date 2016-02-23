package com.pomela.zookeeper.curator.demo.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import com.pomela.zookeeper.curator.core.TopsCuratorFramework;

/**
 * Created by hetor on 16/2/22.
 */
public class LeaderElectionDemo {
	public static void main(String[] args) throws Exception {
		String path = "";

		LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
			public void takeLeadership(CuratorFramework client) throws Exception {
				// this callback will get called when you are the leader
				// do whatever leader work you need to and only exit
				// this method when you want to relinquish leadership
			}
		};

		LeaderSelector selector = new LeaderSelector(TopsCuratorFramework.getInstance().getCuratorFramework(), path,
				listener);
		selector.autoRequeue(); // not required, but this is behavior that you will probably expect
		selector.start();
	}
}
