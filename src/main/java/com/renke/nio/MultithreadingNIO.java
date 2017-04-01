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
	
	//方案
	//一个线程监听ServerSocketChannel accept请求
	//其他线程均匀服务客户端连接后产生的通道
	//监听线程1个
	//每个线程一个Selector
	//Selector在一个数组里面，数组的大小代表了服务线程的个数
	
	
}
