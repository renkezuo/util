package com.renke.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 获取当前日期增加offset天数之后的那一周起止日期
	 * @author renke.zuo@foxmail.com
	 * @time 2017-03-14 10:02:25
	 * @param offset
	 * @return
	 */
	public static Date[] getWeekDate(int offset){
		Date[] dates = new Date[2];
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, offset);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		//日历计算，周日为每周第一天
		c.add(Calendar.DAY_OF_MONTH, -1);
		//设置当前日期为周一
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		dates[0] = c.getTime();

		
		//因为周日为一周第一天，故，取下周周日
		c.add(Calendar.DAY_OF_MONTH, 7);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		
		dates[1] = c.getTime();
		
		return dates;
	}
	
	/**
	 * 计算从当日起前dayCount天时间
	 * @author gjx
	 * @date 2017年3月20日
	 * @param dayCount
	 * @param init true初始化到零点，false时分秒不变
	 * @return
	 */
	public static Date calStartDate(int dayCount,Boolean init){
		Date startTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		cal.add(Calendar.DATE, dayCount);
		startTime = cal.getTime();
		SimpleDateFormat sdf = null;
		if(init) {
			sdf = new SimpleDateFormat("yyyy-MM-dd 0:0:0");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(startTime);
		try {
			startTime = dateFormat.parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	//System.currentTimeMillis() = UTC当前时间毫秒数 - 0 [UTC 1970-01-01 00:00:00毫秒数]
	//所谓的时区，都是在UTC基础上，做加减法
	public static void main(String[] args) {
		Date dates[] = getWeekDate(18);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dates[0]));
		System.out.println(sdf.format(dates[1]));
	}
}

