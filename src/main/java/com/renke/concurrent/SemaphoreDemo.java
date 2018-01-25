package com.renke.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class SemaphoreDemo {
	static Semaphore semap = new Semaphore(5);
	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					semap.acquire(1);
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + ": done!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					semap.release();
				}
			}
		};
		ExecutorService es = Executors.newFixedThreadPool(20);
		for(int i=0;i<20;i++){
			es.submit(task);
		}
		es.shutdown();
	}
}
