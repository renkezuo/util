package com.renke.study;

public class StudyException extends Exception{
	private static final long serialVersionUID = 1L;
	public StudyException(){
		super();
	}
	public StudyException(String msg){
		super(msg);
	}
	public StudyException(String msg,Throwable e){
		super(msg,e);
	}
	public StudyException(Throwable e){
		super(e);
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
