package com.renke.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	public static CountDownLatch cdl = new CountDownLatch(10);
	
	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(new Random().nextInt(10) * 1000);
					cdl.countDown();
					System.out.println(Thread.currentThread().getName() + "check Down!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.submit(task);
		}
		es.shutdown();
		try {
			cdl.await();
			System.out.println("All Check pass , go!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
