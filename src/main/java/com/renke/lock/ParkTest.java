package com.renke.lock;

import java.util.concurrent.locks.LockSupport;

public class ParkTest {

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("ta");
		ta.mainThread = Thread.currentThread();
		try {
			System.out.println("��ʼ");
			ta.start();
			System.out.println("MAIN�߳̽���ȴ�...");
			Thread.sleep(1000);
			// ���̵߳ȴ�
			LockSupport.park();
			System.out.println("MAIN�̱߳����ѣ�׼������TA");
			Thread.sleep(5000);
			System.out.println("MAIN�߳̽���TA�߳�");
			Thread.sleep(1000);
			LockSupport.unpark(ta);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			LockSupport.unpark(ta);
		}
		System.out.println("���߳̽���");
	}

	static class ThreadA extends Thread {

		public Thread mainThread;
		
		public ThreadA(String name) {
			super(name);
		}

		public void run() {
			try {
				System.out.println("TA�߳̿�ʼִ�У�5s�����MAIN�߳�");
				sleep(5000);
				System.out.println("TA����MAIN�߳�");
				LockSupport.unpark(mainThread);
				Thread.sleep(1000);
				System.out.println("5s��TA�̵߳ȴ�");
				sleep(5000);
				LockSupport.park();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("TA�̱߳����ѣ�����");
		}
	}
}
