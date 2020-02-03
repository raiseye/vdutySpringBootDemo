package com.vduty.example.demoMutiModul.serviceImpl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * test AtomicInteger 原子操做测试
 * @author yeluxing
 *
 */
public class ThreadAtomic extends Thread{
	public static AtomicInteger atomicInt = new AtomicInteger(0);
	public static int val  = 0;
	
	@Override
	public void run() {
		try {
			Thread.sleep((long)((Math.random())*100));
			atomicInt.incrementAndGet();
			val ++;
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
