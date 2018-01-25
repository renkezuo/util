package com.renke.computer;

import java.util.Random;

public class Function {
	public static Random random = new Random();
	
	
	public static int recursion(int i){
		i = i + random.nextInt(10);
		if(i>1000000){
			return i;
		}else{
			return recursion(i);
		}
	}
	
	public static int whileToRec(int i){
		while(i > 10000000){
			i = i + random.nextInt(10);
		}
		return i;
	}
	
	public static void main(String[] args) {
		recursion(0);
//		whileToRec(0);
	}
	
}
