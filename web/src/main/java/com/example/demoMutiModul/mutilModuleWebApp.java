package com.example.demoMutiModul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * s
 *
 */
@ComponentScan(basePackageClasses = {mutilModuleWebApp.class})
@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class mutilModuleWebApp 
{
    public static void main( String[] args )
    {
        
    	SpringApplication.run(mutilModuleWebApp.class, args);
    }
}
