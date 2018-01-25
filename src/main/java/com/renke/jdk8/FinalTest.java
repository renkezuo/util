package com.renke.jdk8;

import java.time.LocalTime;

public final class FinalTest {
	public int x = 0;
	public int y = 1;
	
	public final int[] a;
	public FinalTest(){
		a = new int[2];
	}
	public static void main(String[] args) {
		FinalTest ft = new FinalTest();
		ft.a[0] = 3;
		ft.a[1] = 4;
		ft.a[0] = 9;
		
		LocalTime begin = LocalTime.of(8, 00);
		LocalTime end = LocalTime.of(8, 45);
		
		
		
		LocalTime b1 = LocalTime.of(8, 30);
		LocalTime e1 = LocalTime.of(9, 15);
		
		System.out.println(b1.isAfter(begin) && b1.isBefore(end));
		System.out.println(e1.isAfter(begin) && e1.isBefore(end));
	}
}
