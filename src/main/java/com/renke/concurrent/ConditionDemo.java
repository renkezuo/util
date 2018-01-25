package com.renke.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();
	
	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					condition.await();
					System.out.println(Thread.currentThread().getName() + ":signal");
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + ":done!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					condition.signal();
					lock.unlock();
				}
			}
		};
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.submit(task);
		}
		es.shutdown();
		try {
			lock.lock();
			Thread.sleep(2000);
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
