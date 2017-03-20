package com.renke.jdk8.lambda;

public class StringCompare {

	public static int compare(String str1, String str2) {
		char[] c1 = str1.toLowerCase().toCharArray();
		char[] c2 = str2.toLowerCase().toCharArray();
		int sort = -1;
		int length = c1.length;
		if(c1.length > c2.length){
			length = c2.length;
			sort = 1;
		}
		for(int i = 0 ; i< length ; i++){
			if(c1[i] > c2[i]){
				return 1;
			}else if(c1[i]< c2[i]){
				return -1;
			}
		}
		return sort;
	}
	
}
