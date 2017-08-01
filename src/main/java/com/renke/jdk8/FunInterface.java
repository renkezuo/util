package com.renke.jdk8;


/**
 * 有且仅有一个public的未实现方法
 * 
 * @author renke.zuo@foxmail.com
 * @time 2017-03-07 18:14:58
 */
@FunctionalInterface
public interface FunInterface {
	public void world(); //delete this method err : FunInterface is not FunctionalInterface
//	public void zeze(); //err: FunInterface is not FunctionalInterface
	//也有interface enhance的新特性
	default void test(){System.out.println("test1");}
	default void test2(){System.out.println("test2");}
//	public static void xixi();//err :This method requires a body instead of a semicolon
	public static void hello(){
		System.out.println("hello world!");
	};
	public static void main(String[] args) {
		hello();
	}
}
