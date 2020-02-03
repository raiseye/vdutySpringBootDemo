package com.vduty.example.demoMutiModul.serviceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.vduty.example.demoMutiModul.config.Globals;
import com.vduty.example.demoMutiModul.domain.TaskInfo;
import com.vduty.example.demoMutiModul.web.controller.index;
@Component
public class AsyncTaskService {	
	@Async("BaseExecutor")//指定TaskExecutor	
	public ListenableFuture<String> sayHello(String name) {
	    String res = name ;
	    long use =0;
	    AsyncResult<String> aResult = null;
	    try {
	    	TaskInfo taskInfo = new TaskInfo(name);    	
	    	
	    	long start = System.currentTimeMillis();//ms毫秒
	    	double result=0;
			/**
			 * 模拟复杂的计算
			 */
	    	for(long i=0;i<100000000;i++) {
				result += 103944940303.777*i;
			}
			
			long end =  System.currentTimeMillis(); 
			 use  = end - start;
			taskInfo.setPackageSuccess(true);
			
			LoggerFactory.getLogger(AsyncTaskService.class).info("完成执行 " + res + "  "+(use) + "  " + String.valueOf(result));
			LoggerFactory.getLogger(AsyncTaskService.class).info("theradneme-----" + Thread.currentThread().getName());
		    Globals.threadCountForAtom.getAndIncrement();//atomic operate，获取调用次数
		    aResult = new AsyncResult<String>(use+"ms");
		    Globals.futureList.put("name", aResult);
	    
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    return aResult;
	}
	@Async("VdutyExecutor")
	public void sayWelcome(String name) {
		 String res = name ;
		   // LoggerFactory.getLogger(ThreadHello.class).info(res);
		    try {
		    	TaskInfo taskInfo = new TaskInfo(name);    	
		    	Globals.taskMap.put(name, taskInfo);
		    	long start = System.currentTimeMillis();//ms毫秒
		    	double result=0;
				
		    	for(long i=0;i<100000000;i++) {
					result += 103944940303.777*i;
				}
				
				long end =  System.currentTimeMillis(); 
				long use  = end - start;
				taskInfo.setPackageSuccess(true);
				Globals.taskMap.put(name, taskInfo);
				LoggerFactory.getLogger(AsyncTaskService.class).info("完成执行 " + res + "  "+(use) + "  " + String.valueOf(result));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		    //return new AsyncResult<String>(res);
	}
	
	
	 public String doTaskBaseExcutorRecycleDo(boolean isBlockRun,int recycleDo,AsyncTaskService asyncTaskService) {
	    StringBuilder sb = new StringBuilder();
	    for(int i=0;i<recycleDo;i++) {
	    	sb.append(asyncTaskService.doTaskBaseExcutor(i, isBlockRun, asyncTaskService));
	    	sb.append(" , ");
	    }
	    return sb.toString();
	 }
	 public String doTaskBaseExcutor(int id, boolean isBlockRun,AsyncTaskService asyncTaskService) {
		// 阻塞调用
		// helloThread.sayHello("???").get();
		Globals.threadCountForAsync.getAndIncrement();
		// 非阻塞调用
		String taskName  = "async-" + id;				
		Future<String> future = asyncTaskService.sayHello(taskName);//如果future.get就能获取结果，阻塞线程
		LoggerFactory.getLogger(AsyncTaskService.class).info("已经排入" + taskName);
		
		if (isBlockRun) {
			try {
				String taskResult =  future.get();
				LoggerFactory.getLogger(index.class).info("taskResult-----" + taskResult);
				return taskResult;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return String.valueOf(id);
		/*
		 * //if future.isDone() ....... while (true) { if (future.isDone()) {
		 * System.out.println("Result from asynchronous process - " + future.get());
		 * break; } System.out.println("Continue doing something else. ");
		 * System.out.println("main end:" + LocalDateTime.now() + ",id:" +
		 * Thread.currentThread().getId());
		 * 
		 * }
		 */

	}
	 
	public String getFutureResult() {
		Globals.getfutureResultCount = new AtomicInteger(0);
		StringBuilder sb = new StringBuilder();
//		 new Thread( () -> {
//	            System.out.println("In Java8, Lambda expression rocks !!");
//	        }).start();
		
	Thread  fThread =  new Thread(()-> {
		int getfutureResultCount = Globals.getfutureResultCount.get();
		int threadCountForAtom = Globals.threadCountForAtom.get();
		while (getfutureResultCount >=threadCountForAtom ) {
			for(String key : Globals.futureList.keySet()) {
            	AsyncResult<String>  future = (AsyncResult<String>) Globals.futureList.get(key);
            	if (future.isDone()) {
            		try {
						sb.append(future.get());
						sb.append(",");
						Globals.getfutureResultCount.getAndIncrement();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
		}
			
		});
	   fThread.start();
	   
	   
		LoggerFactory.getLogger("结果：");
		LoggerFactory.getLogger(AsyncTaskService.class).info(sb.toString());
		return sb.toString();
	}
}
		
	
