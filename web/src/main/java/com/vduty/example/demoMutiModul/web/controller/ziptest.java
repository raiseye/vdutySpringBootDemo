package com.vduty.example.demoMutiModul.web.controller;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/zip")

public class ziptest {
	
   private @Autowired
   com.vduty.utils.file.zip zipUtils;
   @Value("${vduty.upload-path}") 
   private String UPLOAD_PATH; //read parameter from application config file(.yml or .properties)
   @RequestMapping(value = "/dozip", method = RequestMethod.GET)
   public String doZip() {
	   long s = System.currentTimeMillis();
	   String sourcePath =UPLOAD_PATH + File.separator + "fromzip";	
	   System.out.println(sourcePath);
	   String targetPath = UPLOAD_PATH + File.separator +"temp";
	   String targetFilename = "javaZiptest.zip";
	   zipUtils.createZip(sourcePath, targetPath,targetFilename);
	   long used = System.currentTimeMillis() - s;
	   
	   return "compress dir to zip completed! used time length:" + String.valueOf(used/1000)+"s";
	   
   }
	
}
