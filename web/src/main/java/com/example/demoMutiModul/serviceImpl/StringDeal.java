package com.example.demoMutiModul.serviceImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.example.demoMutiModul.annotation.AddSome;

/**
 * 测试通过构造函数调用注解处理器，使类在被实例化（或注入时）能够根据注解作出特定的处理。
 * @author lx.ye
 *
 */
@Service("StringDeal")
public class StringDeal {

	private String add = "";

	@AddSome(string = "hello,this is addSome")
	public String getString(String str) {

		return this.add + " ,parameter is :" + str;

	}

	public StringDeal() {

		this.readAnnotationWhenInit();

	}

	/**
	 * if somewhere use this structure function to instance (for example
	 * use @Autowire annotation),this function enable to read annotation info and
	 * initial it.
	 */
	private void readAnnotationWhenInit() {

		// get annotation
		Class<StringDeal> c = StringDeal.class;
		try {
			Method method = c.getMethod("getString", String.class);
			if (method.isAnnotationPresent(AddSome.class)) {
				// 获取该方法的MyAnnotation注解实例
				AddSome myAnnotation = method.getAnnotation(AddSome.class);
				// 执行该方法
				// method.invoke(stringDeal, new Object[]{});
				// 获取myAnnotation
				String string = myAnnotation.string();
				this.add = string;//利用它，使getString方法作出不同的处理
			}
			// 获取方法上的所有注解
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.println(annotation);
			}

		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
