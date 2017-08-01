package com.renke.util.thread;

import java.util.Random;

public class WaitHandler implements Runnable{
	public Object handler;
	public Object controller;
	public WaitHandler(Object handler, Object controller){
		this.handler = handler;
		this.controller = controller;
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+" : 准备就绪，有bug请呼叫我!");
			while(true){
				synchronized (handler) {
					System.out.println("其他人释放锁，我可以等待！");
					handler.wait();
				}
				System.out.println(Thread.currentThread().getName()+" : 收到，请稍等...");
				Random random = new Random();
				int sleep = 3000 + random.nextInt(7000);
				Thread.sleep(sleep);
				System.out.println(Thread.currentThread().getName()
						+" : bug已经解决，用时"+sleep/1000+"s，再有bug请呼叫我!");
				synchronized (controller) {
					controller.notify();
					//可在此处添加sleep，测试notify不会释放锁
					//Thread.sleep(10000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
