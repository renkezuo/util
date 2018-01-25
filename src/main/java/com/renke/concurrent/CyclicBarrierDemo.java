package com.renke.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
	public static CyclicBarrier cyclic = new CyclicBarrier(10, new Runnable() {
		@Override
		public void run() {
			System.out.println("done!");
		}
	});
	public static void main(String[] args) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":wait other");
					cyclic.await();
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() + ": doSth!");
					System.out.println(Thread.currentThread().getName() + ": wait other do!");
					cyclic.await();
					System.out.println(Thread.currentThread().getName() + ": OK!");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		};
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.submit(task);
		}
		es.shutdown();
	}
}
