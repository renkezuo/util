package com.renke.test;

import org.junit.Test;

public class MathTest {
	public static void main(String[] args) {
		double i = 202;
		int days = 5;
		int lessons = 9 ;
		System.out.println(i/days/lessons);
	}
	
	@Test
	public void printUserIds(){
		StringBuilder userIds = new StringBuilder();
		for(int i= 2000 ;i < 3000;i++){
			userIds.append(",").append(i);
		}
		userIds.deleteCharAt(0);
		System.out.println(userIds.toString());
	}
}
