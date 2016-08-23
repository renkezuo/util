package com.renke.exception;

public class ParseHTTPException extends Exception{
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
}
