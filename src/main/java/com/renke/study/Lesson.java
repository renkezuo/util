package com.renke.study;


public class Lesson {
		 // 10元钱的组成，1，2，5任意组合
	public static int i = 1;
	public static void fun(String log, int n) {
		  // int num = n;
		  if (0 == n) {
		   System.out.println(log.substring(0, log.length() - 1) + "=");
		   return;
		  } else if (1 == n) {
		   System.out.println(log + "1" + "=");
		   return;
		  }
		  if (n >= 1)
			  fun(log + "1+", n - 1);
		  if (n >= 2)
			  fun(log + "2+", n - 2);
		  if (n >= 5)
			  fun(log + "5+", n - 5);
		  i++;
	}

	public static void main(String[] args) {
		System.out.println("google".hashCode());
		System.out.println("You".hashCode());
	}
}
