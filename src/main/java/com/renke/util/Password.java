package com.renke.util;

import java.util.Base64;



public class Password {
	public static String encodeBase64(String password){
		byte[] bytes = password.getBytes();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static String decodeBase64(String password){
		byte[] bytes = password.getBytes();
		return new String(Base64.getDecoder().decode(bytes));
	}
	
	public static void main(String[] args) {
		System.out.println(encodeBase64("a1234567"));
		System.out.println(decodeBase64("YTEyMzQ1Njc="));
	}
}
