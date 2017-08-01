package com.renke.study;

import java.util.Calendar;

public class 位运算 {
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
//		int i = 2<<3;
//		int i = 2* 8;
//		int i = 1<<3;
		int i = 8>>2;
		System.out.println(i);
//		System.out.println(1<<3);
		System.out.println(System.currentTimeMillis()-begin);
		int page = 10;
		String sql ="select top 8 letterid from letterreceiverinfo where letterreceiver='' and receiverinfoid not in(select top "+(page-1)*8+" receiverinfoid from letterreceiverinfo ) and isread=1";
		System.out.println(sql);
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTimeInMillis());
		System.out.println(System.currentTimeMillis());
		System.out.println(c.getTime());
		c.set(2017,7,1,0,58,0);
		
		if(System.currentTimeMillis() > c.getTimeInMillis()){
			System.out.println("over");
		}else{
		}
		
	}
}
