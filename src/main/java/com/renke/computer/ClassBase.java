package com.renke.computer;

public class ClassBase {
	private static String a = "1";
	private String b = "2";
	private final String c= "3";
	public final String d = "4";
	static{
		System.out.println("static segments");
		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//		System.out.println(d);
	}
	
	{
		System.out.println("instance segments");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
	
	
	public static void classMethod(){
		System.out.println("classMethod");
	}
	
	public void instanceMethod(){
		System.out.println("instanceMethod");
	}
	
	public static void main(String[] args) {
		ClassBase.classMethod();
		ClassBase cb = new ClassBase();
		cb.instanceMethod();
	}
	
}


