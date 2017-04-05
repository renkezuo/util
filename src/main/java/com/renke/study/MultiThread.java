package com.renke.study;

import java.io.IOException;

//���̱߳��
public class MultiThread {
	public static void main(String args[]) {
		System.out.println("�������߳�!");
		// ���洴���߳�ʵ��thread1
		ThreadUseExtends thread1 = new ThreadUseExtends();
		// ����thread2ʱ��ʵ����Runnable�ӿڵ�THhreadUseRunnable��ʵ��Ϊ����
		Thread thread2 = new Thread(new ThreadUseRunnable(), "SecondThread");
		thread1.start();// �����߳�thread1ʹ֮���ھ���״̬
		// thread1.setPriority(6);//����thread1�����ȼ�Ϊ6
		// ���ȼ�������cpu�ճ�ʱ�����ھ���״̬���߳�˭��ռ��cpu��ʼ����
		// ���ȼ���Χ1��10,MIN_PRIORITY,MAX_PRIORITY,NORM_PAIORITY
		// ���̼̳߳д������ĸ��߳����ȼ�,���߳�ͨ������ͨ���ȼ���5NORM_PRIORITY
		System.out.println("���߳̽�����7��!");
		try {
			Thread.sleep(7000);// ���̹߳���7��
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("�ֻص������߳�!");
		if (thread1.isAlive()) {
			thread1.stop();// ���thread1��������ɱ����
			System.out.println("thread1���߹���,���߳�ɱ����thread1!");
		} else
			System.out.println("���߳�û����thread1,thread1����˳��ִ�н�����!");
		thread2.start();// ����thread2
		System.out.println("���߳��ֽ�����7��!");
		try {
			Thread.sleep(7000);// ���̹߳���7��
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("�ֻص������߳�!");
		if (thread2.isAlive()) {
			thread2.stop();// ���thread2��������ɱ����
			System.out.println("thread2���߹��������߳�ɱ����thread2!");
		} else
			System.out.println("���߳�û����thread2,thread2����˳��ִ�н�����!");
		System.out.println("������������������!");
		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println(e.toString());
		}

	}// main
}// MultiThread

class ThreadUseExtends extends Thread{
// ͨ���̳�Thread��,��ʵ�����ĳ��󷽷�run()
// �ʵ�ʱ�򴴽���һThread�����ʵ����ʵ�ֶ��̻߳���
// һ���߳�������Ҳ���������״̬��һ�����CPU���Զ���������run()����
	ThreadUseExtends() {}// ���캯��

	public void run() {
		System.out.println("����Thread������߳�ʵ��!");
		System.out.println("�ҽ�����10��!");
		System.out.println("�ص����߳�,���Ե�,�ղ����̹߳�����ܻ�û�ѹ�����");
		try {
			sleep(10000);// ����5��
		} catch (InterruptedException e) {
			return;
		}
		// �����run()����˳��ִ������,�߳̽��Զ�����,�����ᱻ���߳�ɱ��
		// ���������ʱ�����,���̻߳����,���ܱ�stop()ɱ��
	}

}

class ThreadUseRunnable implements Runnable{
// ͨ��ʵ��Runnable�ӿ��е�run()����,�������ʵ����run()��������
// Ϊ��������Thread���߳�ʵ��
	// Thread thread2=new Thread(this);
	// �����ʵ����Runnable�ӿ���run()��������Ϊ��������Thread����߳�ʵ��
	ThreadUseRunnable() {}// ���캯��

	public void run() {
		System.out.println("����Thread����߳�ʵ������ʵ����Runnable�ӿڵ���Ϊ����!");
		System.out.println("�ҽ�����1��!");
		System.out.println("�ص����߳�,���Ե�,�ղ����̹߳�����ܻ�û�ѹ�����");
		try {
			Thread.sleep(1000);// ����5��
		} catch (InterruptedException e) {
			return;
		}
		// �����run()����˳��ִ������,�߳̽��Զ�����,�����ᱻ���߳�ɱ��
		// ���������ʱ�����,���̻߳����,���ܱ�stop()ɱ��
	}

}
