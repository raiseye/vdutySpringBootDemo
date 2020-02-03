package com.vduty.example.demoMutiModul.service;

/**
 * 抽象的观察者
 * 当被观察者调用notifyObserver，updateStatus方法被执行
 * @author yeluxing
 *
 */
public interface Observer {
	 public void updateContent(String content);
}
