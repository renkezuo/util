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
			System.out.println(Thread.currentThread().getName()+" : ׼����������bug�������!");
			while(true){
				synchronized (handler) {
					System.out.println("�������ͷ������ҿ��Եȴ���");
					handler.wait();
				}
				System.out.println(Thread.currentThread().getName()+" : �յ������Ե�...");
				Random random = new Random();
				int sleep = 3000 + random.nextInt(7000);
				Thread.sleep(sleep);
				System.out.println(Thread.currentThread().getName()
						+" : bug�Ѿ��������ʱ"+sleep/1000+"s������bug�������!");
				synchronized (controller) {
					controller.notify();
					//���ڴ˴����sleep������notify�����ͷ���
					//Thread.sleep(10000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}