package com.example.demoMutiModul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	
}
