package com.renke.computer.inherit;

public class Main {
//	public class Greeting {
//		String intro = "Hello";
//		String target() {
//			return "world";
//		}
//	}

//	public class FrenchGreeting extends Greeting {
//		String intro = "Bonjour";
//
//		String target() {
//			return "le monde";
//		}
//	}
	
	public static void main(String[] args) {
		// 引用类型Greeting
		Greeting english = new Greeting();
		// 引用类型Greeting
		Greeting french = new FrenchGreeting();
		// hello world
		System.out.println(english.intro + "," + english.target());
		// hello le monde
		System.out.println(french.intro + "," + french.target());
		
	}
}
