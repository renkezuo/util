package com.renke.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestTask implements Runnable{
	public static AtomicInteger atoCount = new AtomicInteger(5);
	
	public static int intCount = 5;
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		if(atoCount.incrementAndGet() < 10){
//			System.out.println(Thread.currentThread().getId()+"count < 10 :" + atoCount.get());
//		}else{
//			System.out.println(Thread.currentThread().getId()+"count >= 10 :" + atoCount.get());
//		}
		intCount = intCount + 1;
	}

}
