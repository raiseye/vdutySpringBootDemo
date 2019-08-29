package com.example.demoMutiModul.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解
 * @author lx.ye
 *
 */


/*
  @Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、
 类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
 */
@Target(ElementType.METHOD)

/*	java.lang.annotation.Retention可以在您定义Annotation型态时，指示编译器如何对待您的自定义 Annotation，
	 * 预设上编译器会将Annotation资讯留在class档案中，但不被虚拟机器读取，而仅用于编译器或工具程式运行时提供资讯。 
	在使用Retention型态时，需要提供java.lang.annotation.RetentionPolicy的列举型态： 
	package java.lang.annotation; 
	public enum RetentionPolicy { 
	SOURCE, //编译器处理完Annotation资讯后就没事了 
	CLASS, //编译器将Annotation储存于class档中，预设 
	RUNTIME //编译器将Annotation储存于class档中，可由VM读入 
} 
*/

@Retention(RetentionPolicy.RUNTIME)
public @interface AnalysisActuator  {
    String note() default "";
}
