package com.renke.computer;

public class Switch {
	public static void printSwitch(int x){
		switch(x){
			default : 
				System.out.println("default");
			case 1 :
				System.out.println(1);
			case 3 :
				System.out.println(3);
			case 2 :
				System.out.println(2);
			case 4 :
				System.out.println(4);
			case 7 :
				System.out.println(7);
			case 5 :
				System.out.println(5);
			case 6 :
				System.out.println(6);
		}
	}
	
	public static void main(String[] args) {
		printSwitch(6);
	}
	
}
