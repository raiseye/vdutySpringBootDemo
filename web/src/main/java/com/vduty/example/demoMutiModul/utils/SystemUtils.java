package com.vduty.example.demoMutiModul.utils;

public class SystemUtils {
	
	public void getSystemInfo() {
		Runtime rt =  Runtime.getRuntime();
        System.out.println(rt.freeMemory()/(1024*1024));
        System.out.println(rt.maxMemory()/(1024*1024));
        System.out.println(rt.totalMemory()/(1024*1024));
        System.out.println(rt.availableProcessors());
	}

}
