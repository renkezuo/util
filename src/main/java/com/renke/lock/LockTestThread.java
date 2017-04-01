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
			System.out.println(threadName+"�ҵĵ��̣���������");
			lock.lockInterruptibly();
			System.out.println(threadName+"�ҵ�������");
			Thread.sleep(100000000);
		} catch (InterruptedException e) {
			System.out.println(threadName+"��Ҫ�����ң��Ҳ����ߣ�");
		} finally{
			System.out.println("��Ȼ���������ˣ�");
			lock.unlock();
		}
	}

}
