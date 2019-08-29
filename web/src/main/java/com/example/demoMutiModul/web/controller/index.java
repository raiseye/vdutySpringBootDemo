package com.example.demoMutiModul.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/index",method = RequestMethod.GET)
public class index {

	@RequestMapping(value = "",method = RequestMethod.GET)
	public String index() {	
		
		StringBuilder sb = new StringBuilder();
		sb.append(" <!DOCTYPE html>	<html lang='zh-CN'><head><title>java技术点测试项目</title><meta charset='UTF-8'></head><body>");
		sb.append("<a href='/index/mutilapleMuduleMethod'>STS-ECLIPSE创建多模块工程</a>");
		sb.append("<br/>");
		sb.append("<a href='/hello/index'>注解Annotation、AOP测试、@Configuration@Bean测试</a>");
		sb.append("</body></html>");
		return sb.toString();
	}
	
	@RequestMapping(value = "/mutilapleMuduleMethod",method = RequestMethod.GET)
	public String mutilapleMuduleMethod() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>STS-ECLIPSE创建多模块工程</h1>");
		sb.append("<h3>创建父工程1</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程1.png'/></div>");
		sb.append("<h3>创建父工程2</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程2.PNG'/></div>");
		sb.append("<h3>创建父工程3要改packaging为pom</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程3要改packaging为pom.PNG'/></div>");
		sb.append("<h3>父工程右键创建子工程1</h3>");
		sb.append("<div class='imgdiv'><img src='/images/父工程右键创建子工程1.PNG'/></div>");
		sb.append("<h3>创建子工程的Package和GroupId的命名要以父工程的名为前缀</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建子工程的Package和GroupId的命名要以父工程的名为前缀.PNG'/></div>");
		sb.append("<h3>创建父工程3要改packaging为pom</h3>");
		sb.append("<div class='imgdiv'><img src='/images/创建父工程3要改packaging为pom.PNG'/></div>");
		sb.append("<h3>设置子工程的依赖2输入自动显示可用-选择即可</h3>");
		sb.append("<div class='imgdiv'><img src='/images/设置子工程的依赖2输入自动显示可用-选择即可.PNG'/></div>");
		
		return sb.toString();
	}
	
}
