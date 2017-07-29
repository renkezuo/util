package com.renke.jdk8;

public class MainTest {
	public static void main(String[] args){
		FunInterface fi = new FunInterface(){
			@Override
			public void world() {
				System.out.println("hello world");
			}
		};
		fi.test();
		
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("inner class");
			}
		});
		
		Thread t1 = new Thread(() -> {System.out.println("lambda");});
		t.start();
		t1.start();
	}
}
