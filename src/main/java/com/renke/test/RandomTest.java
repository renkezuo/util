package com.renke.test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomTest {
	public static void main(String[] args) {
		Runnable runner = new RandomThread();
		for(int i=0;i<5;i++){
			Thread thread = new Thread(runner);
			thread.start();
		}
		while(RandomThread.count.get() != 5){
			//循环的次数越多，越平均
			//500000次，平均5000次
			//最少有4700
			//最多有5177
		}
		for(int i=0;i<=100;i++){
			System.out.println("key:"+i+" --> cnts:"+RandomThread.map.get(i));
		}
	}
}
class RandomThread implements Runnable{
	public static Map<Integer,Integer> map = new ConcurrentHashMap<>();
	public volatile static AtomicInteger count = new AtomicInteger(0);
	@Override
	public void run() {
		Random random = new Random();
		for(int i=0;i<100000;i++){
			Integer key = random.nextInt(100);
			map.put(key,map.get(key) == null ? 1 : map.get(key)+ 1);
		}
		count.incrementAndGet();
	}
}
