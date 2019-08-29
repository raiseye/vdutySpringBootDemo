package com.example.demoMutiModul.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Concat {
	
	public String con(String ...s) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length;i++) {
			sb.append(s[i]);
		}
		
		return sb.toString();
	}
	

}
