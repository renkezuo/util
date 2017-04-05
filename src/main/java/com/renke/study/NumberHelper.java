package com.renke.study;

public class NumberHelper {

	public static void main(String[] args) {
		int i = -10240;
		int j = -10;
		int k = 10;
		System.out.println("j======>"+Integer.toBinaryString(j));
		System.out.println("-10240====>"+Integer.toBinaryString(j<<10));
		System.out.println("j<<10====>"+Integer.toBinaryString(i));
		System.out.println("k======>"+Integer.toBinaryString(k));
//		11111111111111111111111111110110;
//		00000000000000000000000000001010;
//		11111111111111111111111111110110;
	}
}
