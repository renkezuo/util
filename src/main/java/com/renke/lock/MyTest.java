package com.renke.lock;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//import org.junit.Test;

public class MyTest {
	final ReadWriteLock rwl = new ReentrantReadWriteLock();
	//测试3个读锁之后，添加一个写锁，写锁添加之后，同一时间添加5个读锁
	class Reader implements Runnable{
		@Override
		public void run() {
			System.out.println("thread-"+Thread.currentThread().getId()+": readLock.lock");
			rwl.readLock().lock();
			System.out.println("thread-"+Thread.currentThread().getId()+": readLock run");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread-"+Thread.currentThread().getId()+": readLock.unlock "+System.currentTimeMillis());
			rwl.readLock().unlock();
		}
	}
	
	class Writer implements Runnable{
		@Override
		public void run() {
			System.out.println("thread-"+Thread.currentThread().getId()+": writeLock.lock");
			rwl.writeLock().lock();
			System.out.println("thread-"+Thread.currentThread().getId()+": writeLock run");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread-"+Thread.currentThread().getId()+": writeLock.unlock");
			rwl.writeLock().unlock();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		MyTest mytest = new MyTest();
		Reader reader = mytest.new Reader();
		Writer writer = mytest.new Writer();
		
		
		ThreadPoolExecutor tpe = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		tpe.execute(reader);
		Thread.sleep(1000);
		tpe.execute(reader);
		Thread.sleep(1000);
		tpe.execute(reader);
		Thread.sleep(1000);
		tpe.execute(reader);
		Thread.sleep(1000);
		tpe.execute(writer);
		tpe.execute(reader);
		tpe.execute(reader);
		tpe.execute(reader);
		tpe.execute(reader);
		
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

//	@Test
	public void testLockSupport() {
		String a = "123";
		String b = "456";
		a = b = "789";
		System.out.println(a);
		System.out.println(b);
		System.out.println(1>>>16);
//		Thread thread = Thread.currentThread();
//		LockSupport.unpark(thread);// 释放许可
//		LockSupport.park();// 获取许可
//		System.out.println("b");
	}

}
