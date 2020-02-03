package com.vduty.example.demoMutiModul.utils;
/**
 * 线程方法类
 * @author 职道 yeluxing
 *
 */
public class ThreadUtils {
	/**
	 * 获取线程数量
	 * @return
	 */
	public static int getThreadNum() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		int curestimatedSize = group.activeCount()*2;
		Thread[] curlist = new Thread[curestimatedSize];
		group.enumerate(curlist);
		System.out.println("current thread num is: "+curlist.length);
		ThreadGroup topGroup = group;
		// 遍历线程组树，获取根线程组
		while (group != null) {
			topGroup = group;
			group = group.getParent();
		}
		// 激活的线程数加倍
		int estimatedSize = topGroup.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = topGroup.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
//		System.arraycopy(slackList, 0, list, 0, actualSize);
//		System.out.println("Thread list size == " + list.length);
//		for (Thread thread : list) {
//			System.out.println(thread.getName());
//		}
		return list.length;
	}
	
	
	
	

}
