package com.renke.jdk8;


/**
 * ���ҽ���һ��public��δʵ�ַ���
 * 
 * @author renke.zuo@foxmail.com
 * @time 2017-03-07 18:14:58
 */
@FunctionalInterface
public interface FunInterface {
	public void world(); //delete this method err : FunInterface is not FunctionalInterface
//	public void zeze(); //err: FunInterface is not FunctionalInterface
	default void test(){}
//	public static void xixi();//err :This method requires a body instead of a semicolon
	public static void hello(){
		System.out.println("hello world!");
	};
	public static void main(String[] args) {
		hello();
	}
}
