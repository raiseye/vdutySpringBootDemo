package com.vduty.example.demoMutiModul.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
  Here configure multiple bean of TaskExecutor, have diffrent bean name.
  如果是CPU密集型应用，则线程池大小设置为N+1
  如果是IO密集型应用，则线程池大小设置为2N+1
 * @author yeluxing
 *
 */
@Configuration
@EnableAsync
public class ExecutorBeanConfig {

    @Bean("BaseExecutor")
    public TaskExecutor BaseExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        executor.setCorePoolSize(5);//Nthreads=Ncpu*Ucpu*（1+W/C）
        
        executor.setMaxPoolSize(10);
        
        executor.setQueueCapacity(100);
        
        executor.setKeepAliveSeconds(60);
        
        executor.setThreadNamePrefix("pool base-");
        
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        
        executor.setWaitForTasksToCompleteOnShutdown(true);
        
        return executor;
    }
    @Bean("VdutyExecutor")
    public TaskExecutor VdutyExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        executor.setCorePoolSize(3);//Nthreads=Ncpu*Ucpu*（1+W/C）
        
        executor.setMaxPoolSize(10);
        
        executor.setQueueCapacity(100);
        
        executor.setKeepAliveSeconds(10);
        
        executor.setThreadNamePrefix("pool duty-");
        
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        
        executor.setWaitForTasksToCompleteOnShutdown(true);
        
        return executor;
    }
    
}