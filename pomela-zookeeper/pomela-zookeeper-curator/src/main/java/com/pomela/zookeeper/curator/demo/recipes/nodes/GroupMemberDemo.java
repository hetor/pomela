package com.pomela.zookeeper.curator.demo.recipes.nodes;

/**
 * Created by hetor on 16/3/2.
 *
 * @Description
 * Group membership management. Adds this instance into a group and keeps a
 * cache of members in the group.
 *
 * GroupMember must be started:group.start();
 * When you are through with the GroupMember instance, you should call close:group.close();NOTE: this will remove the instance from the group
 * You can get a current view of the members by calling:group.getCurrentMembers();
 *
 * @Participating-Classes
 * GroupMember
 * PersistentNode
 * PathChildrenCache
 *
 * @Error-Handling
 * GroupMember instances internally handle all error states recreating the node as necessary.
 *
 * http://curator.apache.org/curator-recipes/group-member.html
 */
public class GroupMemberDemo {
}
