package com.renke.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Z.R.K
 * @description
 * @create 2018-10-12 09:42:31
 **/
public class SyncTest {
	public final static Map<Integer, Long> xxx = new HashMap<>();
	static {
		xxx.put(123, 1000L);
		xxx.put(456, 1000L);
		xxx.put(567, 2000L);
	}
	
	static class MyThread implements Runnable {
		
		private int key = 0;
		public MyThread(int key){
			this.key = key;
		}
		
		@Override
		public void run() {
			Long lock = xxx.get(key);
			synchronized (lock){
				try {
					System.out.println(key + " ... synchronized ... in ");
					Thread.sleep(5000);
					System.out.println(key + " ... synchronized ... out");
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread(123));
		Thread t2 = new Thread(new MyThread(456));
		Thread t3 = new Thread(new MyThread(123));
		Thread t4 = new Thread(new MyThread(456));
		Thread t5 = new Thread(new MyThread(567));
		Thread t6 = new Thread(new MyThread(567));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}
