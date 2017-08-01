package com.renke.classloader;

public class ClassTest {
	public static int i=123;
	public static void main(String[] args) {
		try {
			//加载--验证--准备--解析--初始化--使用--卸载
			//加载会产生class
			//验证
			ClassLoader.getSystemClassLoader().loadClass("com.renke.classloader.StaticSegment");//仅加载到虚拟机
//			Class.forName("com.renke.classloader.StaticSegment");//会执行静态方法块[初始化]
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
