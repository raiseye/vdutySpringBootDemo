package com.vduty.example.demoMutiModul.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vduty.example.demoMutiModul.service.Observer;
import com.vduty.example.demoMutiModul.service.Observerable;

/**
 * A具体的被观察者
 * @author yeluxing
 *
 */
public class AObserverable implements Observerable {

	private List<Observer> lObserverable  = new ArrayList<Observer>();//Observer接口集合！
	private String content;
	
	public AObserverable() {
		
	}
	@Override
	public void registerObserver(Observer o) {
		// 
		this.lObserverable.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO 
		if (!lObserverable.isEmpty())
        this.lObserverable.remove(o);
	}

	@Override
	public void notifyObserver() {
		// 
		Iterator<Observer> iter=  lObserverable.iterator();
		while(iter.hasNext()) {
			Observer observer = (Observer)iter.next();
			observer.updateContent(content);
		}
		
	}
	
	public void updateContent(String content) {
		this.content = content;
		//通知所有的观察者
		notifyObserver();
	}

	
}
