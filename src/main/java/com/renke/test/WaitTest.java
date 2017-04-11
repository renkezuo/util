package com.renke.test;

public class WaitTest {

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("ta");
		synchronized (ta) { // 通过synchronized(ta)获取“对象ta的同步锁”
			try {
				System.out.println("开始");
				ta.start();
				Thread.sleep(1000);
				System.out.println("MAIN线程进入等待...");
				// 主线程等待
				ta.wait();
				System.out.println("MAIN线程被叫醒，准备叫醒一个线程");
				Thread.sleep(5000);
				System.out.println("MAIN线程叫醒一个线程，但是它还要等待我释放锁");
				Thread.sleep(1000);
				ta.notify();
				System.out.println("3s后，MAIN线程释放TA线程锁");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MAIN线程释放TA线程锁");
		}
		System.out.println("主线程结束");
	}

	static class ThreadA extends Thread {
		public ThreadA(String name) {
			super(name);
		}
		public void run() {
			synchronized (this) { // 通过synchronized(this)获取“当前对象的同步锁”
				try {
					System.out.println("TA线程开始执行，5s后叫醒一个线程");
					sleep(5000);
					System.out.println("TA叫醒一个线程");
					notify();
					Thread.sleep(1000);
					System.out.println("TA线程准备等待");
					sleep(5000);
					System.out.println("TA等待，释放锁");
					wait();
				} catch (Exception e1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("TA子线程被叫醒");
			}
			System.out.println("TA子线程结束");
		}
	}
}