package com.vduty.example.demoMutiModul.web.controller;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vduty.example.demoMutiModul.config.Globals;
import com.vduty.example.demoMutiModul.serviceImpl.AsyncTaskService;
import com.vduty.example.demoMutiModul.serviceImpl.ExcuteorServiceImpl;
import com.vduty.example.demoMutiModul.utils.ThreadUtils;
import com.vduty.utils.WebUtils;

/**
 * 要实现：1、ThreadPoorExecutor的单例模式；
 * 2、阻塞调用：任务阻塞模式的使用场景不大，它几乎等于顺序执行，这样多线程还有什么意义？在需要结果关联的场景下适合。
 * 可用不用在调用时不使用future的get产生阻塞，而是启用另一个线程监听future的执行情况，如while(future.isdone){...}来获取结果。
 * 也可以在task任务体里在运算的最后把结果存入到体格hashmap里或者数据库里。
 * 3、测试原子操作atomicinteger
 * 4、测试线程池的优化配置
 * @author yeluxing
 *
 */

@RestController
@RequestMapping("threadpoor/")
public class threadpoor {
	public static AtomicInteger atomicInteger = new AtomicInteger(0);
	public static int mutipleThreadTestInt = 0;
	@Autowired
	WebUtils webUtils;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	ExcuteorServiceImpl excuteorServiceImpl;

	@Autowired
	Globals globals;
	@Autowired
	AsyncTaskService asyncTaskService;
	
	/**
	 * 
	 * @param id 设置任务id，用于多用户请求该接口
	 * @param isblock 是否为阻塞模式
	 * @return
	 */
	@RequestMapping("doTaskBaseExcutor/{id}/{isblock}")
	public String doTaskBaseExcutor(@PathVariable("id") Integer id,@PathVariable("isblock")String isblock) {
		if (id == null) id = 123;
		boolean _isblock;
		if (isblock == null ) _isblock = false;
		else
			_isblock = true;
		String ru = asyncTaskService.doTaskBaseExcutor(id,_isblock,asyncTaskService );
		
		return "do complete!ru=" + ru + " threadCountForAsync="  + globals.threadCountForAsync;
	}
	/**
	 * 
	 * @param recycledo repeat run task number ， 重复执行次数
	 * @param isblock if block model run task, if is that can get task return value; 阻塞模式
	 * @return
	 */
	@RequestMapping("doTaskBaseExcutorRecycleDo/{recycledo}/{isblock}")
	public String doTaskBaseExcutorRecycleDo(@PathVariable("recycledo") Integer recycledo, @PathVariable("isblock") String isblock) {
		long star = System.currentTimeMillis();
		if (recycledo==null) {
			recycledo =100;
			System.out.print("recycledo:" +recycledo);
		}
		Globals.threadCountForAtom = new AtomicInteger(0);//如果重复操作，初为零。
		Globals.futureList.clear();//init futureList
		
		boolean _isblock;
		
		 if (isblock!=null && !isblock.equals("true") ) {
			 _isblock = false;
			 
		}
		else
			_isblock = true;
		//if not block:Through practice, we find that when the number of tasks exceeds the maximum number of thread pool threads + queue threads, the controller will block still all task be reseive(but no all do complete).   
		//if block is true : it will block still all task do complete and get result
		
		String ru = asyncTaskService.doTaskBaseExcutorRecycleDo(_isblock,recycledo,asyncTaskService );
        System.out.println("--------------------------get result ----------------------------");
        long end = System.currentTimeMillis();
        long used  = end - star;
		return "use time "+ used /1000 + "s,调用次数："+Globals.threadCountForAtom + "  do complete! recycledo:"+ recycledo +" ru = " + ru;
	}
	
	 
	
	


}
