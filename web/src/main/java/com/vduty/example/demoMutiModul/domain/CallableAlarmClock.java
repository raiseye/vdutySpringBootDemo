package com.vduty.example.demoMutiModul.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本想和future配合开发例子，但实在没有想象出真正的使用用场景。
 * 如果a线程的执行需要b线程的结果，那他们在同一个线程排队执行就好了？！若采用future.get() 的方法会阻塞线程，那还要创建线程干嘛?
 * 如果需要监听future.done()在取结果，那不同这种方法，自线程计算结果放在全局变量（可以是缓存、数据库）里，要用取这个变量值就好了？
 * @author yeluxing
 *
 */
public class CallableAlarmClock implements Callable<Boolean>{

	public static AtomicInteger atomInt = new AtomicInteger(0);
	@Override
	public Boolean call() throws Exception {
		            System.out.println("闹钟响");
        
        return true;
	}
	
	

}
