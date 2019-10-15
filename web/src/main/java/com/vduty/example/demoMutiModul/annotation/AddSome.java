package com.vduty.example.demoMutiModul.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AddSome {
	
	public String string() default "";

}
