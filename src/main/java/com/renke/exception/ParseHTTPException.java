package com.renke.exception;

public class ParseHTTPException extends Exception{
	static boolean b;
	private static final long serialVersionUID = 1L;
	public ParseHTTPException(){
		super();
	}
	public ParseHTTPException(String msg){
		super(msg);
	}
	public ParseHTTPException(Throwable e){
		super(e);
	}
	public static void main(String[] args) {
		System.out.println(b);
	}
}
