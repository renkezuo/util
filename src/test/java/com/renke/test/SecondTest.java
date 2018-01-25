package com.renke.test;

public class SecondTest {
	public static void main(String[] args) {
		double x = -103.1;
		int i = (int)x;
		System.out.println(Double.doubleToLongBits(-x));
		System.out.println("正数："+Long.toBinaryString(Double.doubleToLongBits(-x)));
		System.out.println("负数："+Long.toBinaryString(Double.doubleToLongBits(x)));
		System.out.println(i / 3);
		System.out.println(x == 103);
	}
}
