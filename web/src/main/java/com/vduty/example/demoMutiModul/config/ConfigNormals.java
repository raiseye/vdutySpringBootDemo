package com.vduty.example.demoMutiModul.config;

import java.util.concurrent.ConcurrentHashMap;

import org.omg.CORBA.Current;

import com.vduty.example.demoMutiModul.domain.TaskInfo;

public class ConfigNormals {
	
	public static ConcurrentHashMap<String,TaskInfo> currentTasks  = new ConcurrentHashMap<String,TaskInfo>();

	private String siteName = "vduty.com";

	final public int ERROR_OVERFLOW = 1;
	final public int ERROR_DUMP_NAME = 2;
	
	public String getErrorName(int errcode)
	{
		if (errcode==1) return "ERROR_OVERFLOW";
		else
			return "ERROR_DUMP_NAME";
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
