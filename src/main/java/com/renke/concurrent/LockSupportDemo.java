package com.renke.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("park before");
				LockSupport.park();
//				LockSupport.park();
				System.out.println("park after");
			}
		};
		Thread t1 = new Thread(task);
//		Thread t2 = new Thread(task);
		t1.start();
//		t2.start();
		LockSupport.unpark(t1);LockSupport.unpark(t1);
		Thread.sleep(4000);
//		LockSupport.unpark(t2);
	}
}
