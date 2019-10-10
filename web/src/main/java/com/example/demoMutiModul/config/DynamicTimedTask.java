package com.example.demoMutiModul.config;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demoMutiModul.domain.TaskInfo;

@Component
//@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode= ScopedProxyMode.INTERFACES)
@Scope("request")
public class DynamicTimedTask {
    
    private ScheduledFuture<?> future;
    private TaskInfo taskInfo;

    public TaskInfo getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}

	@Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

   
    class doTask implements Runnable{

        @Override
        public void run() {
        	
            System.out.println("执行了定时任务"+taskTitle+"！！！"+ (new Date()));
        }        
         
    }
   
    
    public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	private String cron = "0/5 * * * * ? "; 
    private String taskTitle ="xxx任务";
    

   
    public boolean startScheduleTask(Runnable task) {  	    	
    	
        boolean flag = false;               
        
        future = threadPoolTaskScheduler.schedule(task,
                triggerContext -> {
                    
                    if (StringUtils.isEmpty(cron)) {
                       
                    }
                    
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
        if (future!=null){
            flag = true;
            System.out.print(taskTitle+"任务启动成功！");
        }else {
        	 System.out.print(taskTitle+"任务启动失败！");
        }
        return flag;
    }

   
    public boolean stopScheduleTask() {
        boolean flag = false;
        if (future != null) {
            boolean cancel = future.cancel(true);
            if (cancel){
                flag = true;
                System.out.print(taskTitle+"任务停止成功！！！");
            }else {
            	 System.out.print(taskTitle+"任务停止失败！！！");
            }
        }else {
            flag = true;
            System.out.print(taskTitle + "任务已经停止！！！");
        }
        return flag;
    }   

}
