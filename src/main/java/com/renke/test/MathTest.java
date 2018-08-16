package com.renke.test;

//import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MathTest {
	public static void main(String[] args) {

		Map<String,String> map = new HashMap<>();

		map.put("123", "456");
		map.putIfAbsent("123", "666");

		System.out.println(map.get("123"));

		Map<String,String> cMap = new ConcurrentHashMap<>();

		cMap.put("123", "456");
		cMap.putIfAbsent("123", "666");

		System.out.println(cMap.get("123"));

//		double i = 202;
//		int days = 5;
//		int lessons = 9 ;
//		System.out.println(i/days/lessons);
	}
	
//	@Test
	public void printUserIds(){
		StringBuilder userIds = new StringBuilder();
		for(int i= 2000 ;i < 3000;i++){
			userIds.append(",").append(i);
		}
		userIds.deleteCharAt(0);
		System.out.println(userIds.toString());
	}
}
