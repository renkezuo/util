package com.renke.classloader;

public class StaticSegment {
	public static int k = 123;
	static{
		System.out.println("k is :"+k);
	}
}
