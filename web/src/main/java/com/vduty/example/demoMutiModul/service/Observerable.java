package com.vduty.example.demoMutiModul.service;



/**
 * 观察则会模式样例
 * 抽象的被观察者
 * @author yeluxing
 *
 */
public interface Observerable {
	/**
	 * 注册观察者
	 * @param o
	 */
	public void registerObserver(Observer o);

	/**
	 * 删除观察者注册
	 * @param o
	 */
	public void removeObserver(Observer o);

	/**
	 * 通知观察者
	 */
	public void notifyObserver();
}
