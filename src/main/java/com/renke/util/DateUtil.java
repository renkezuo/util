package com.renke.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * ��ȡ��ǰ��������offset����֮�����һ����ֹ����
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
		//�������㣬����Ϊÿ�ܵ�һ��
		c.add(Calendar.DAY_OF_MONTH, -1);
		//���õ�ǰ����Ϊ��һ
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		dates[0] = c.getTime();

		
		//��Ϊ����Ϊһ�ܵ�һ�죬�ʣ�ȡ��������
		c.add(Calendar.DAY_OF_MONTH, 7);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		
		dates[1] = c.getTime();
		
		return dates;
	}
	
	/**
	 * ����ӵ�����ǰdayCount��ʱ��
	 * @author gjx
	 * @date 2017��3��20��
	 * @param dayCount
	 * @param init true��ʼ������㣬falseʱ���벻��
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
	//System.currentTimeMillis() = UTC��ǰʱ������� - 0 [UTC 1970-01-01 00:00:00������]
	//��ν��ʱ����������UTC�����ϣ����Ӽ���
	public static void main(String[] args) {
		Date dates[] = getWeekDate(18);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(dates[0]));
		System.out.println(sdf.format(dates[1]));
	}
}

