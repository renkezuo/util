package com.renke.util.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
	public static void main(String[] args) {
		Hello obj = new Hello();
		System.out.println("main Thread ===>" + obj);
		Thread t = new Thread(new MyRun(obj));
		Thread t1 = new Thread(new MyRun(obj));
		Thread t2 = new Thread(new MyRun(obj));
//		for(int i=0;i<10;i++){
			t.start();
			t1.start();
			t2.start();
//		}
//			AsynchronousServerSocketChannel
	}
}

class ThreadLocalParam{
	private static ThreadLocal<Hello> local = new ThreadLocal<Hello>();
	private static AtomicInteger i = new AtomicInteger(1);
	public static void set(Hello obj){
		local.set(obj);
	}
	
	public static void get(){
//		int m = i.addAndGet(1);
		int m = i.incrementAndGet();
		Hello h = local.get();
		h.setId(h.getId()+m);
		System.out.println(Thread.currentThread().getName()+"====>"+local.get());
	}
	
}

class MyRun implements  Runnable {
	public Hello obj;
	public MyRun(Hello obj){
		this.obj = obj;
	}
	@Override
	public void run() {
		ThreadLocalParam.set(obj);
		ThreadLocalParam.get();
	}
}

class Hello {
	public String id;
	public Hello(){
		this.id = "123";
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String toString() {
		return id;
	};
}
