package com.renke.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class MyTest {
	public static void main(String[] args) {
		Lock lock;
		ReadWriteLock rwl;
	}

	// @Test
	public void interrupt() throws InterruptedException {
		final Lock lock = new ReentrantLock();
		lock.lock();
		Thread.sleep(1000);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// lock.lockInterruptibly();
					lock.lock();
				} catch (Exception e) {
					System.out.println(Thread.currentThread().getName() + " interrupted.");
				}
			}
		});
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
		Thread.sleep(1000000);
	}

	// @Test
	public void testInterrupted() throws InterruptedException {
		LockTestThread lt1 = new LockTestThread();
		LockTestThread lt2 = new LockTestThread();
		Thread t1 = new Thread(lt1);
		Thread t2 = new Thread(lt2);
		t1.start();
		t2.start();
		Thread.sleep(10000);
		t1.interrupt();
		Thread.sleep(1000);
	}

	@Test
	public void testLockSupport() {
		String a = "123";
		String b = "456";
		a = b = "789";
		System.out.println(a);
		System.out.println(b);
		
//		Thread thread = Thread.currentThread();
//		LockSupport.unpark(thread);// 释放许可
//		LockSupport.park();// 获取许可
//		System.out.println("b");
	}

}
