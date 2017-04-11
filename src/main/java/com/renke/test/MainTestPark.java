package com.renke.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainTestPark {
	public static void main(String[] args) throws Exception{
		TestTask task = new TestTask();
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for(int i=0;i<1000;i++)
			tpe.execute(task);
		while(tpe.getActiveCount() > 0){
			Thread.sleep(1000);
		}
		tpe.shutdown();
		System.out.println("int:"+TestTask.intCount);
		System.out.println("AtomicInteger:"+TestTask.atoCount);
	}
}
