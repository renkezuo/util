package com.renke.study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Page{
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date.setTime(131792896);
		String today = sdf.format(date);
		System.out.println(today);
	}
}
