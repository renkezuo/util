package com.renke.study;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	static String getYesteryDate(String tj_date){
		Calendar   cal   =   Calendar.getInstance();
		if(tj_date!=null && tj_date.length()==8){
			int year = Integer.parseInt(tj_date.substring(0,4));
			int month = Integer.parseInt(tj_date.substring(4,6))-1;
			int date = Integer.parseInt(tj_date.substring(6,8));
			cal.set(year,month,date);
		}
		cal.add(Calendar.DATE,   -1); 
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yesterday;
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.format(System.currentTimeMillis()));
//		Date date1 = new Date("11/12-2011 ");
//		Date date2 = new Date("10/12-2011 ");
//		System.out.println(date1.after(date2));
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM-yyyy HH:mm:ss");
//		try {
//			Date date = sdf.parse("11/12-2011");
//			System.out.println(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Calendar c = Calendar.getInstance();
//		c.set(2011, 11, 19,13,14,23);
//		System.out.println(c.getTime());
//		System.out.println(sdf.format(c.getTime()));
//		System.out.println( 1<2?3:(4<5?6:7));
//		System.out.println(c.SECOND);
//		c.set(c.YEAR, c.get(c.YEAR)+1);
//		c.setTimeInMillis(System.currentTimeMillis());
//		date = c.getTime();
//		System.out.println(date);
//		System.out.println(c.get(c.YEAR)+1);
//		System.out.println(System.currentTimeMillis()/1000);
//		System.out.println(System.currentTimeMillis()/(1000*60*60*24*366));
		System.out.println(DateHelper.getYesteryDate("20121220"));
	}
}
