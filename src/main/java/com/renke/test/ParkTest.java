package com.renke.test;

import java.util.concurrent.locks.LockSupport;

public class ParkTest {

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("ta");
		ta.mainThread = Thread.currentThread();
		try {
			System.out.println("开始");
			ta.start();
			System.out.println("MAIN线程进入等待...");
			Thread.sleep(1000);
			// 主线程等待
			LockSupport.park();
			System.out.println("MAIN线程被叫醒，准备叫醒TA");
			Thread.sleep(5000);
			System.out.println("MAIN线程叫醒TA线程");
			Thread.sleep(1000);
			LockSupport.unpark(ta);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			LockSupport.unpark(ta);
		}
		System.out.println("主线程结束");
	}

	static class ThreadA extends Thread {

		public Thread mainThread;
		
		public ThreadA(String name) {
			super(name);
		}

		public void run() {
			try {
				System.out.println("TA线程开始执行，5s后叫醒MAIN线程");
				sleep(5000);
				System.out.println("TA叫醒MAIN线程");
				LockSupport.unpark(mainThread);
				Thread.sleep(1000);
				System.out.println("5s后，TA线程等待");
				sleep(5000);
				LockSupport.park();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("TA线程被叫醒，结束");
		}
	}
}
