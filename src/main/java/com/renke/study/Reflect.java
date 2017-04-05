package com.renke.study;

import java.lang.reflect.Method;

public class Reflect {
	public static String fun1(){
		System.out.println("fun1");
		return "fun1";
	}
	public static String fun2(){
		System.out.println("fun2");
		return "fun2";
	}
	public static String fun3(){
		System.out.println("fun3");
		return "fun3";
	}
	public static String fun4(String msg){
		System.out.println(msg);
		return "fun4";
	}
	
	public String print(String className){
		System.out.println("method : print1:"+getLine());
		return "method : print:"+getLine();
	}
	public int getLine(){
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		int line = 0;
		for(int i=0;i<ste.length;i++){
			line = ste[2].getLineNumber();
		}
		return line;
	}
	public static void main(String[] args) {
		Reflect ref = new Reflect();
		System.out.println(ref.getLine());
		try {
			Class cls[] = new Class[1];
			cls[0] = String.class;
			Object obj[] = new Object[1];
			obj[0] = "hello reflect";
			Method aMethod = ref.getClass().getMethod("fun4",cls);
			aMethod.invoke(null,obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
