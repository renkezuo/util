package com.renke.util.thread;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;

public class ParkHandler implements Runnable{
	public Thread wakeupThread;
	public ParkHandler(Thread wakeupThread){
		this.wakeupThread = wakeupThread;
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" : ׼����������bug�������@"+wakeupThread.getName());
			while(true){
				LockSupport.park();
				System.out.println(Thread.currentThread().getName()+" : �յ������Ե�...");
				Random random = new Random();
				int sleep = 3000 + random.nextInt(7000);
				Thread.sleep(sleep);
				System.out.println(Thread.currentThread().getName()
						+" : bug�Ѿ��������ʱ"+sleep/1000+"s������bug�������@"+wakeupThread.getName());
				LockSupport.unpark(wakeupThread);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
