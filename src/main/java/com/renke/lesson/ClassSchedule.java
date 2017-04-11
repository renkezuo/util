package com.renke.lesson;

import java.util.Random;

public class ClassSchedule {
	public static void main(String[] args) {
		Random random = new Random();
		for(int i=0;i<100;i++){
			System.out.println("cycle1:"+random.nextInt(100));
		}
		random = new Random();
		for(int i=0;i<100;i++){
			System.out.println("cycle2:"+random.nextInt(100));
		}
	}
}
