package com.renke.util.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class BugController {
	static Lock lock = new ReentrantLock();
	static int k = 0;
	public static void main(String[] args)  throws InterruptedException {
		//wait��notify
//		waitController();
		//park��unpark
//		parkController();
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<100;i++)
						increment();
				}
			}).start();
		}
		
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<100;i++)
						decrement();
				}
			}).start();
		}
		
		Thread.sleep(3000);
		System.out.println(k);
	}
	
	
	public static void increment(){
		lock.lock();
		k ++ ;
		lock.unlock();
	}
	
	public static void decrement(){
		lock.lock();
		k --;
		lock.unlock();
	}
	
	public static void parkController() throws InterruptedException {
		System.out.println("���߳�������");
		Thread currentThread = Thread.currentThread();
		currentThread.setName("controllerThread");
		Thread thread1 = new Thread(new ParkHandler(currentThread), "parkThread1");
		Thread thread2 = new Thread(new ParkHandler(currentThread), "parkThread2");
		Thread thread3 = new Thread(new ParkHandler(currentThread), "parkThread3");
		thread1.start();
		thread2.start();
		thread3.start();
		Thread.sleep(2000);
		while(true){
			System.out.println(currentThread.getName()+" : ����bug���뽻����õĴ���������");
			int best = selectBest();
			if(best == 0){
				LockSupport.unpark(thread1);
			}else if(best == 1){
				LockSupport.unpark(thread2);
			} else{
				LockSupport.unpark(thread3);
			}
			LockSupport.park();
			System.out.println(currentThread.getName()+" : �������գ����Ժ�...");
			System.out.println(currentThread.getName()+" : ����ͨ����OK");
			Thread.sleep(5000);
			System.out.println("");
		}
	}
	
	public static void waitController() throws InterruptedException {
		System.out.println("���߳�������");
		Thread currentThread = Thread.currentThread();
		currentThread.setName("controllerThread");
		Object controller = new Object();
		Object handler = new Object();
		Thread thread1 = new Thread(new WaitHandler(handler,controller), "waitThread1");
		Thread thread2 = new Thread(new WaitHandler(handler,controller), "waitThread2");
		Thread thread3 = new Thread(new WaitHandler(handler,controller), "waitThread3");
		thread1.start();
		thread2.start();
		thread3.start();
		Thread.sleep(2000);
		while(true){
			System.out.println(currentThread.getName()+" : ����bug���봦��");
			doit(handler);
			synchronized (controller) {
				controller.wait();
			}
			System.out.println(currentThread.getName()+" : �������գ����Ժ�...");
			System.out.println(currentThread.getName()+" : ����ͨ����OK");
			Thread.sleep(5000);
			System.out.println("");
		}
	}
	
	public static void doit(Object handler){
		System.out.println("Selector : ���ѡ�����������Եȣ�");
		synchronized (handler) {
			handler.notify();
		}
	}
	
	public static int selectBest(){
		System.out.println("Selector : �Ѿ�Ϊ��ѡ����ô����������Եȣ�");
		Random random = new Random();
		return random.nextInt(100) % 3;
	}
}
