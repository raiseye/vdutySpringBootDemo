package com.example.demoMutiModul.web.controller;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoMutiModul.annotation.*;
import com.example.demoMutiModul.common.Concat;
import com.example.demoMutiModul.config.ConfigNormals;
import com.example.demoMutiModul.service.StringDeal;
import com.example.demoMutiModul.utils.SpringUtil;

@RestController
@RequestMapping(value = "/hello",method = RequestMethod.GET)

public class hello {

	@Autowired
	private Concat concat;
	
	@Autowired
	SpringUtil springUtil;
	
	@Autowired 
	private StringDeal stringDeal;
	
	@Autowired 
	ConfigNormals configNormals;
	
	 @AnalysisActuator(note = "hello used time")
	 @RequestMapping("/index")
	 public String b(String a) {
		 
		 
		int b = 0;
		for (int i = 0; i < 100000000; i++) {

			if (i % 1000 == 0) {
				b++;
			}
		}
		 
		 String curMethodName =  Thread.currentThread().getStackTrace()[1].getMethodName();//get current method name
		 String analysisActuatorNote = "";
		  for (Method m : this.getClass().getDeclaredMethods()) {
		         if (m.getName().equals(curMethodName)) {
		        	 AnalysisActuator analysisActuator =   m.getAnnotation(AnalysisActuator.class);//get annotation of  method b
		        	 if (analysisActuator!=null) {
		        		 analysisActuatorNote =   analysisActuator.note(); //get set value of annotation 
		        	 }
		         }
		  }
		  /**
		   * @Configuration @Bean的使用
		   * 
		   */
		  ConfigNormals configNormals =  (ConfigNormals)springUtil.getBean("configNormal");			  
		  StringBuilder sb = new StringBuilder();
		  
		  sb.append("<h2> @Configuration @Bean的使用</h2>");
		  sb.append("<span style='color:gray'>configNormals.ERROR_DUMP_NAME：</span>");
		  sb.append("<span style=''>");
		  sb.append( configNormals.ERROR_DUMP_NAME);
		  sb.append("</span>");
		  
		  sb.append("<h2> 利用注解+注解处理器+构造函数使对象的方法有不同的处理</h2>");
		  sb.append("<span style='color:gray'>stringDeal.getString(\"good!\")：</span>");
		  sb.append("<span style=''>");
		  sb.append( stringDeal.getString("good!"));
		  sb.append("</span>");
		  
		  sb.append("<h2> 获取注解的值</h2>");
		  sb.append("<span style='color:gray'>analysisActuatorNote：</span>");
		  sb.append("<span style=''>");
		  sb.append( analysisActuatorNote);
		  sb.append("</span>");
		  
		  sb.append("<h2> 循环计算结果，为了测试执行时间</h2>");
		  sb.append("<span style='color:gray'> String.valueOf(b)：</span>");
		  sb.append("<span style=''>");
		  sb.append(  String.valueOf(b));
		  sb.append("</span>");
		  
		  
		  sb.append("<h2> 采用@Autowired获取@Configureation+@Bean的bean对象</h2>");
		  sb.append("<span style='color:gray'> this.configNormals.ERROR_OVERFLOW：</span>");
		  sb.append("<span style=''>");
		  sb.append( this.configNormals.ERROR_OVERFLOW);
		  sb.append("</span>");
		  
		  
		 
		 return  concat.con( " Hello " , "world" , " this is a example of multiple module spring boot project be created by STS", sb.toString() );
			
		 
	 }
	
}
