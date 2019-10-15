package com.vduty.example.demoMutiModul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;

@Component
@Configuration
//@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer{

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
//		Cron表达式参数分别表示：
//
//		秒（0~59） 例如0/5表示每5秒
//		分（0~59）
//		时（0~23）
//		日（0~31）的某天，需计算
//		月（0~11）
//		周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
//		
		taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                	System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                	System.out.println("执行了动态定时任务");
                
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = "0/5 * * * * *";//TODO从数据库获取执行周期
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
		
		taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                	System.out.println("执行动态定时任务2: " + LocalDateTime.now().toLocalTime());
                	System.out.println("执行了动态定时任务2");
                
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = "0/5 * * * * *";//TODO从数据库获取执行周期
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
		
		
	}
	

}
