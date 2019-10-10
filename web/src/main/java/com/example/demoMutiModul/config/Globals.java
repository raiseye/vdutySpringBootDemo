package com.example.demoMutiModul.config;

import java.util.concurrent.ConcurrentHashMap;

import com.example.demoMutiModul.domain.TaskInfo;

public class Globals {
	
	public static ConcurrentHashMap<String,TaskInfo> taskMap = new ConcurrentHashMap<String,TaskInfo>() ;

}
