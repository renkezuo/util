package com.renke.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamTest {

	public static void parallelSort() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		long start = System.nanoTime();// 获取系统开始排序的时间点
		int count = (int) ((Stream<String>) list.stream().parallel()).sorted().count();
		System.out.println(count);
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到并行排序所用的时间
		System.out.println(ms + "ms");
	}

	public static void sequentialSort() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		long start = System.nanoTime();// 获取系统开始排序的时间点
		int count = (int) ((Stream<String>) list.stream().sequential()).sorted().count();
		System.out.println(count);
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到串行排序所用的时间
		System.out.println(ms + "ms");
	}
	
	public static void listStream(){
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 10; i++){
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		List<String> result = new ArrayList<String>();
		result = list.stream().map(v->{
			System.out.println(v);
			return v;
		}).collect(Collectors.toList());
		System.out.println(result.size());
	}

	public static void main(String[] args) {
		listStream();
		sequentialSort();
		parallelSort();
	}
}