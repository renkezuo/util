package com.renke.study;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Ten {
	public static void main(String[] args) {
		//1,2,5和为10
//		double i = 6222858109901063696.0;
//		double j = 6222858109901063700.0;
//		System.out.println(i);
//		String abc = "123";
//		try{
//			abc = "456";
//			String str = null;
//			str.equals("");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(abc);
		double wrong_count = Double.parseDouble("2");
		double check_count = Double.parseDouble("3");
		double rate = (wrong_count*100)/check_count;
		BigDecimal bd = new BigDecimal(rate);
		System.out.println(bd.setScale(2,BigDecimal.ROUND_HALF_UP));
		System.out.println(-10<<10);
	}
}
