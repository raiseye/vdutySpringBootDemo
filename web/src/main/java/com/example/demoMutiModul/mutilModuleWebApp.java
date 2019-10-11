package com.example.demoMutiModul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 
 *职道 vduty java springboot demo
 */
@ComponentScan(basePackageClasses = {mutilModuleWebApp.class})
@ComponentScan(value="com.vduty.utils")//添加扫描
@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class mutilModuleWebApp 
{
    public static void main( String[] args )
    {
        
    	SpringApplication.run(mutilModuleWebApp.class, args);
    	 System.out.println("===================================================");
         System.out.println("==================职道 http://127.0.0.1:8086 Demo runing......=================================");
         System.out.println("===================================================");
    }
}
