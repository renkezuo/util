package com.renke.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTestThread implements Runnable {
	public static Lock lock = new ReentrantLock();
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName);
		lock.lock();
		try {
			System.out.println(threadName+"我的地盘，哈哈哈！");
			lock.lockInterruptibly();
			System.out.println(threadName+"我得听党的");
			Thread.sleep(100000000);
		} catch (InterruptedException e) {
			System.out.println(threadName+"党要调走我！我不想走！");
		} finally{
			System.out.println("居然把我拖走了？");
			lock.unlock();
		}
	}

}
