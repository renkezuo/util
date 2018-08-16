package com.renke.jdk8.future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Async {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		Thread thread = new Thread(()->{
//			SleepTask sleepTask = new SleepTask(2800L);
//			CompletableFuture.runAsync(sleepTask);
//			System.out.println(Thread.currentThread().getName());
//			return ;
//		});
//		thread.start();
//		Thread.sleep(10000);
		
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
//		Iterator<String> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			String item = iterator.next();
//			if ("1".equals(item)) {
//				iterator.remove();
//			}
//			System.out.println(item);
//		}
		
//		for (String item : list) {
//			if ("1".equals(item)) {
//				list.remove(item);
//			}
//		}
		
		Map<String, String> map = new HashMap<>();
		map.keySet();
		Integer i = 100;
		Integer y = 200;
		Integer x = 300;
		Integer c = 400;
		Integer d = 500;
		Integer e = new Integer(400);
		Integer f = new Integer(500);
		Integer g = new Integer(300);
		Integer h = new Integer(200);
		Integer m = new Integer(100);
		
		System.out.println(m == i);
		System.out.println(i == 100);
		System.out.println(m == 100);
		System.out.println(y == h);
		System.out.println(y==200);
		System.out.println(h == 200);
		System.out.println(x == g);
		System.out.println(x==300);
		System.out.println(g == 300);
		System.out.println(c == e);
		System.out.println(c == 400);
		System.out.println(e == 400);
		System.out.println(f == d);
		System.out.println(d == 500);
		System.out.println(f == 500);
		String str = "a,b,c,,";
		String[] ary = str.split(",");
		System.out.println(ary.length);
	}
	
}


