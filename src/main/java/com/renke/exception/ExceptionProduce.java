package com.renke.exception;

public class ExceptionProduce {
	public void getNull(){
		Object obj = null;
		System.out.println(obj.toString());
	}
	
	public void getMyException(){
		throw new MyException();
	}
}
