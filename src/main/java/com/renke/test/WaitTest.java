package com.renke.test;

public class WaitTest {

	public static void main(String[] args) {
		ThreadA ta = new ThreadA("ta");
		synchronized (ta) { // ͨ��synchronized(ta)��ȡ������ta��ͬ������
			try {
				System.out.println("��ʼ");
				ta.start();
				Thread.sleep(1000);
				System.out.println("MAIN�߳̽���ȴ�...");
				// ���̵߳ȴ�
				ta.wait();
				System.out.println("MAIN�̱߳����ѣ�׼������һ���߳�");
				Thread.sleep(5000);
				System.out.println("MAIN�߳̽���һ���̣߳���������Ҫ�ȴ����ͷ���");
				Thread.sleep(1000);
				ta.notify();
				System.out.println("3s��MAIN�߳��ͷ�TA�߳���");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MAIN�߳��ͷ�TA�߳���");
		}
		System.out.println("���߳̽���");
	}

	static class ThreadA extends Thread {
		public ThreadA(String name) {
			super(name);
		}
		public void run() {
			synchronized (this) { // ͨ��synchronized(this)��ȡ����ǰ�����ͬ������
				try {
					System.out.println("TA�߳̿�ʼִ�У�5s�����һ���߳�");
					sleep(5000);
					System.out.println("TA����һ���߳�");
					notify();
					Thread.sleep(1000);
					System.out.println("TA�߳�׼���ȴ�");
					sleep(5000);
					System.out.println("TA�ȴ����ͷ���");
					wait();
				} catch (Exception e1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("TA���̱߳�����");
			}
			System.out.println("TA���߳̽���");
		}
	}
}