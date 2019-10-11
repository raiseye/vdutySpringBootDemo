package com.vduty.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component 
public class WebUtils {
	 @Value("${server.servlet.context-path}")
	 String rootPath;
	public  String getCurrentHost (HttpServletRequest request){
		if (request == null) return "";
		String url = request.getScheme() + "://" + request.getServerName()
		+ (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()) + rootPath;
		
		return url;
		
	}
	
}
