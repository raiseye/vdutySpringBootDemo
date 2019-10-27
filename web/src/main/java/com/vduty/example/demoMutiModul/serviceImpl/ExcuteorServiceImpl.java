package com.vduty.example.demoMutiModul.serviceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ExcuteorServiceImpl {

	private ExecutorService executorService;
	private int threadNum = 0;

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(int threadnum) {
		this.threadNum = threadnum;
		if (this.executorService == null || this.executorService.isShutdown()) {
			System.out.println("executorService is null , creaste ..............................");
			this.executorService = Executors.newFixedThreadPool(threadnum); //Executors.newCachedThreadPool();
			
			
		} else {

			this.shutdown();
		}

	}

	public boolean shutdown() {
		if (this.getExecutorService() != null && this.getExecutorService().isShutdown() == false) {
			this.getExecutorService().shutdown();
			try {

				this.await();
				return true;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;

	}

	public ExcuteorServiceImpl() {

	}

	@SuppressWarnings("unused")
	private void await() throws InterruptedException {
		System.out.println("await...............");
		
		boolean awaitTermination = executorService.awaitTermination(1, TimeUnit.SECONDS);
		if (!awaitTermination) {
			await();
		} else {
			executorService.shutdownNow();
			System.out.println("awaitTermination is true good good to create new ...............");
			this.executorService = Executors.newFixedThreadPool(this.threadNum);
		}
		
		while(!this.executorService.isShutdown()) {
			System.out.println("continue to shoutdown ...............");
			this.executorService.shutdown();
		}

	}
	
	
	

}
