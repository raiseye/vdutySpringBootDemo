package com.example.demoMutiModul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class Configs {
	
	public Configs() {
		System.out.println("初始化配置");
	}

	@Bean("configNormal")
	public ConfigNormals configNormal() {		
		ConfigNormals configNormals = new ConfigNormals();
		return configNormals;		
	}
	
	 //实例化一个线程池任务调度类,可以使用自定义的ThreadPoolTaskScheduler
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        return  executor;
    }
	
}
