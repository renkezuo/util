package com.renke.nio.netty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class HaveNameThreadFactory implements ThreadFactory {
	private String threadName;
	
	private static AtomicInteger count = new AtomicInteger(1);
	
	public HaveNameThreadFactory(String threadName){
		this.threadName = threadName;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,threadName + count.getAndIncrement());
	}

}
