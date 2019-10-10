package com.example.demoMutiModul.web.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoMutiModul.config.Globals;
import com.example.demoMutiModul.domain.TaskInfo;
import com.example.demoMutiModul.serviceImpl.ThreadHello;

@RestController
@RequestMapping(value = "/index", method = RequestMethod.GET)
public class index {

	private int count = 0;
	@Autowired
	private ThreadHello helloThread;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {

		StringBuilder sb = new StringBuilder();
		sb.append(
				" <!DOCTYPE html>	<html lang='zh-CN'><head><title>java技术点测试项目</title><meta charset='UTF-8'></head><body>");
		sb.append("<a href='/index/mutilapleMuduleMethod'>STS-ECLIPSE创建多模块工程</a>");
		sb.append("<br/>");
		sb.append("<a href='/hello/index'>注解Annotation、AOP测试、@Configuration@Bean测试</a>");
		sb.append("</body></html>");
		return sb.toString();
	}

	@RequestMapping(value = "/mutilapleMuduleMethod", method = RequestMethod.GET)
	public String mutilapleMuduleMethod() {

		StringBuilder sb = new StringBuilder();
		sb.append("<h1>STS-ECLIPSE创建多模块工程</h1>");
		sb.append("<h3>创建父工程1</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程1.png'/></div>");
		sb.append("<h3>创建父工程2</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程2.PNG'/></div>");
		sb.append("<h3>创建父工程3要改packaging为pom</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程3要改packaging为pom.PNG'/></div>");
		sb.append("<h3>父工程右键创建子工程1</h3>");
		sb.append("<div class='imgdiv'><img src='/images/父工程右键创建子工程1.PNG'/></div>");
		sb.append("<h3>创建子工程的Package和GroupId的命名要以父工程的名为前缀</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建子工程的Package和GroupId的命名要以父工程的名为前缀.PNG'/></div>");
		sb.append("<h3>创建父工程3要改packaging为pom</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程3要改packaging为pom.PNG'/></div>");
		sb.append("<h3>设置子工程的依赖2输入自动显示可用-选择即可</h3>");
		sb.append("<div class='imgdiv'><img src='/images/设置子工程的依赖2输入自动显示可用-选择即可.PNG'/></div>");

		return sb.toString();
	}

	@RequestMapping(value = "/threadhello/{id}", method = RequestMethod.GET) 
	public String helloThread(@PathVariable("id") int id) {		

		// 限时调用
		// helloThread.sayHello("yan").get(1, TimeUnit.SECONDS);
		doTask(String.valueOf(id));
		return String.valueOf(count);
	}
	
	@RequestMapping(value = "/threadhello", method = RequestMethod.POST) 
	public String helloThreadPost(int id) {
		System.out.println("post ok..." + id);
		count++;
		doTask(String.valueOf(id)) ;		
		return  String.valueOf(count);
	}
	private void doTask(String id) {
		// 阻塞调用
				// helloThread.sayHello("yan" + count).get();
				count++;
				// 非阻塞调用
				String threadName = "yan-" + id ;
				if (Globals.taskMap.get(threadName)!=null && Globals.taskMap.get(threadName).isPackageSuccess()) {
					TaskInfo _t = 	Globals.taskMap.get(threadName);
					_t.setPackageSuccess(false);
					Globals.taskMap.put(threadName, _t);
				}
				
				   Future<String> future = helloThread.sayHello(threadName );
				   LoggerFactory.getLogger(index.class).info("已经排入" + threadName);
				   /*
				   //if future.isDone() .......
				   while (true) { ///这里使用了循环判断，等待获取结果信息
					      if (future.isDone()) { //判断是否执行完毕
					        System.out.println("Result from asynchronous process - " + future.get());
					        break;
					      }
					      System.out.println("Continue doing something else. ");
					      System.out.println("main end:" + LocalDateTime.now() +
					          ",id:" + Thread.currentThread().getId());
					 
					    }
					    */
				
	}
	
	@RequestMapping(value = "/viewthread", method = RequestMethod.GET)	 
	public String viewThread() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(""+Globals.taskMap.size() + "<br/>");
	    int count=0;
	    for(TaskInfo t : Globals.taskMap.values()) {
	    	count++;
	    	String color="yellow";
	    	if (!t.isPackageSuccess()) {
	    		color = "red";
	    	}
	    	sb.append("<span style='background-color:"+ color+";margin-top:3px;'>"+t.getName() + "-" + t.isPackageSuccess() + " </span>&nbsp;&nbsp;&nbsp;");
	    	if (count % 5 ==0) {
	    		sb.append("<br/>");
	    	}
	    	
	    }	
	    sb.append("<script>");
	    sb.append("var interval = setInterval(()=>{location.href='http://127.0.0.1:8086/index/viewthread'},1000)");
	    sb.append("</script>");
		return sb.toString();
		
	}
	
	

}
