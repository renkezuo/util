package com.renke.util;

public class TestOpcode {
	public void finallyTest(){
//		int i = 0;
//		int j = 1;
//		int m = 2;
//		for(i=0;i<100;i++){
//			;
//		}
//		try{
//			int k = i;
//			Object obj = null;
//			obj.getClass();
//			int n = 18;
//		}catch(NullPointerException e){
//			int n = 10;
//		}catch(Exception e2){
//			int n = 12;
//		}finally{
//			int k = 14;
//		}
	}
	public void syncTest(Object obj){
		synchronized (obj) {
			obj.getClass();
		}
	}
}
