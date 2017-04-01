package com.renke.lesson.pojo;

public class Son extends Parent {
	public Son(){
		hello();
	}
	public Son(int i){
		this();
		hello();
	}
	
	public String getStr(){
		return "123";
	}
	

	public static String getStaticStr(String aaab){
		return "456";
	}
	public static void main(String[] args) {
		new Son(111);
	}
}
