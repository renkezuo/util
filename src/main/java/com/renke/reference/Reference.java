package com.renke.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 测试强引用，软引用，弱引用，虚引用
 * 测试结果：
 * 1、当4种引用同时GC时，phantom被GC之后weak被GC，hard可能在任何时候被gc，soft最后被GC，也可能不被GC
 * 2、
 * @author renke.zuo@foxmail.com
 * @time 2017-03-19 16:07:46
 */
public class Reference {
	public static void main(String[] args) {
		Million h = new Million("Hardx");
		Million s = new Million("Softx",0);
		Million w = new Million("Weakx",0);
		Million p = new Million("Phantomx",0);
		byte[] buf = new byte[1024 * 1024 * 5];
		
		ReferenceQueue<Million> pQueue = new ReferenceQueue<Million>();
		ReferenceQueue<Million> wQueue = new ReferenceQueue<Million>();
		ReferenceQueue<Million> sQueue = new ReferenceQueue<Million>();
		SoftReference<Million> softRef = new SoftReference<Million>(s,sQueue);
		WeakReference<Million> weakRef = new WeakReference<Million>(w,wQueue);
		PhantomReference<Million> PhantomRef = new PhantomReference<Million>(p,pQueue);
		w = null;
		s = null;
		//必须和引用队列一起使用
		p = null;
		h = null;
		
		System.out.println(" gc  ready ...");
		System.out.println("my_gc_before");
//		System.gc();
		//最大程度使用内存，使其触发GC
		maxUseMemory();
		
		circlePrint(wQueue);
		circlePrint(sQueue);
		circlePrint(pQueue);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("my_gc_end");
//		System.gc();
		
	}
	
	public static void printQueue(ReferenceQueue<Million> queue){
		java.lang.ref.Reference<? extends Million> ref  = null;
		while((ref= queue.poll())!=null){
			System.out.println(ref + "--> " + ref.get());
		}
	}
	/**
	 * 不间断的创建对象，促使内存不够
	 * @author renke.zuo@foxmail.com
	 * @time 2017-03-19 16:49:04
	 */
	public static void maxUseMemory(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			es.execute(new MaxUseMemory());
		}
	}
	
	/**
	 * while true形式打印引用队列
	 * @author renke.zuo@foxmail.com
	 * @time 2017-03-19 16:50:48
	 * @param queue
	 */
	public static void circlePrint(ReferenceQueue<Million> queue){
		new Thread(new Runnable() {
			@Override
			public void run() {
				long begin = System.currentTimeMillis();
				while(true){
					printQueue(queue);
					if(System.currentTimeMillis() - begin >= 12000){
						break;
					}
				}
			}
		}).start();
	}
}
class MaxUseMemory implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		for(int i= 0; i< 5;i++){
			Million hh = new Million("hard"+i,3);
			hh = null;
			try {
				//避免GC跟不上new的速度
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
