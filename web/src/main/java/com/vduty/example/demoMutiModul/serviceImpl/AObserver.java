package com.vduty.example.demoMutiModul.serviceImpl;

import com.vduty.example.demoMutiModul.service.Observer;

/**
 * 实际观察者A
 * 
 * @author yeluxing
 *
 */
public class AObserver implements Observer {

	private String name;
	private String content;

	public AObserver(String name) {
		this.name = name;
	}
	
	@Override
	public void updateContent(String content) {
		//
		this.content = content;
		this.show();
	}

	private void show() {
		System.out.print(this.name + " new content:" + content);
	}

}
