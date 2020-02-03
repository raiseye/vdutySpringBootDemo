package com.vduty.example.demoMutiModul.domain;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableGotowork implements Callable<Boolean>{

	public static AtomicInteger atomInt = new AtomicInteger(0);
	@Override
	public Boolean call() throws Exception {
		            System.out.println("去上班");
        
        return true;
	}
	
	

}
