package com.renke.exception;

public class CatchException {
	public static void main(String[] args) {
		ExceptionProduce produce = new ExceptionProduce();
		try{
//			produce.getMyException();
			produce.getNull();
		}catch(MyException e){
			System.out.println(1);
		}catch (Exception e) {
			System.out.println(2);
		}
	}
}
