package com.example.demoMutiModul.serviceImpl;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import com.example.demoMutiModul.config.Globals;
import com.example.demoMutiModul.domain.TaskInfo;
@Component
public class ThreadHello {

	
	
	
	@Async
	public ListenableFuture<String> sayHello(String name) {
	    String res = name ;
	   // LoggerFactory.getLogger(ThreadHello.class).info(res);
	    try {
	    	TaskInfo taskInfo = new TaskInfo(name);    	
	    	Globals.taskMap.put(name, taskInfo);
	    	long start = System.currentTimeMillis();
	    	double result=0;
			for(long i=0;i<100000000;i++) {
				result += 103944940303.777*i;
			}	
			
			long end =  System.currentTimeMillis(); 
			long use  = end - start;
			taskInfo.setPackageSuccess(true);
			Globals.taskMap.put(name, taskInfo);
			LoggerFactory.getLogger(ThreadHello.class).info("完成执行 " + res + "  "+(use) + "  " + String.valueOf(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    return new AsyncResult<String>(res);
	}
}
