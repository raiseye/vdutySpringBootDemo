package com.vduty.example.demoMutiModul.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.vduty.example.demoMutiModul.domain.TaskInfo;
import com.vduty.utils.WebUtils;

@Component
public class Globals {

	public static ConcurrentHashMap<String, TaskInfo> taskMap = new ConcurrentHashMap<String, TaskInfo>();
	public static AtomicInteger threadCountForAtom = new AtomicInteger(0);
	public static AtomicInteger getfutureResultCount = new AtomicInteger(0);
	public static AtomicLong threadCountForAsync = new AtomicLong(0);//原子操作测试
	public static ConcurrentHashMap<String,ListenableFuture<String> > futureList = new ConcurrentHashMap<String,ListenableFuture<String>>();
    @Autowired
	
    WebUtils webUtils;

    public static final int intervalRequest = 500;//ms
    
	public String setIntervalRequest(javax.servlet.http.HttpServletRequest request, String subpath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("var interval = setInterval(()=>{location.href='" + webUtils.getCurrentHost(request)
				+ ""+ subpath +"'},"+ intervalRequest +")");
		sb.append("</script>");
		return sb.toString();
	}

}
