package com.renke.reference;

import java.util.ArrayList;
import java.util.List;

public class MyTest implements Comparable<String> {
	static List<String> listStr = new ArrayList<String>();

	static List<Integer> listInt = new ArrayList<Integer>();
	static List<Object> listObj = new ArrayList<Object>();

	public int compareTo(String str) {
		return 0;
	}
	
	public static <T> T get() {
        return (T) "x";
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(get()));
    }

	
//
//	public static void main(String[] args) {
//		listStr.add("123");
//		listInt.add(123);
//		listObj.add("123");
//	}
}
