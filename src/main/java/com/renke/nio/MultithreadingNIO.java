package com.renke.nio;

import java.nio.channels.Selector;

public class MultithreadingNIO{
	public static Selector[] selectors;
	
	public MultithreadingNIO(int count){
		selectors = new Selector[count];
	}
	
	public MultithreadingNIO(){
		this(Runtime.getRuntime().availableProcessors() * 2);
	}
	
	//����
	//һ���̼߳���ServerSocketChannel accept����
	//�����߳̾��ȷ���ͻ������Ӻ������ͨ��
	//�����߳�1��
	//ÿ���߳�һ��Selector
	//Selector��һ���������棬����Ĵ�С�����˷����̵߳ĸ���
	
	
}
