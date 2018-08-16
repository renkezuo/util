package com.renke.concurrent;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.util.internal.PlatformDependent;

public class ConcurrentSetTest {
	public static void main(String[] args) {
		Queue<Array> collection = PlatformDependent.newMpscQueue();
//		Queue<Array> collection = new ConcurrentLinkedQueue<>();
//		CopyOnWriteArrayList<Array> collection = new CopyOnWriteArrayList<>();
//		DisruptorBlockingQueueFactory<E>
		
		
		for(int i=0;i<10;i++){
			Thread t = new Thread(new CollectionTask(collection));
			t.start();
		}
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
		ExecutorService es = Executors.newSingleThreadExecutor();
		ReportTask report = new ReportTask(collection);
		ses.scheduleAtFixedRate(() -> {
			es.submit(report);
		}, 0, 10000, TimeUnit.MILLISECONDS);
	}
}
class CollectionTask implements Runnable {
	
	Collection<Array> collection;
	
	public CollectionTask(Collection<Array> collection){
		this.collection = collection;
	}
	
	@Override
	public void run() {
		int i=0;
		while(true){
			long sleepTime = 1;
			i++;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String str = Thread.currentThread().getName() + "----" + i;
			Array array = new Array(str,8);
			collection.add(array);
			
			if(i >= 100000){
				sleepTime = 10;
			}
			if(i>=200000){
				sleepTime = 100;
			}
			if(i>=500000){
				sleepTime = 500;
			}
		}
	}
}

class ReportTask implements Runnable{
	int times = 0;
	
	Collection<Array> collection;
	
	public ReportTask(Collection<Array> collection){
		this.collection = collection;
	}
	
	@Override
	public void run() {
		times ++;
		long begin = System.currentTimeMillis();
		int size = collection.size();
		for(Array array : collection) {
			array.name.toString();
		}
		System.out.println("报告次数："+times+"；遍历长度："+size+"；遍历时长："+(System.currentTimeMillis() - begin) +"ms");
	}
}
