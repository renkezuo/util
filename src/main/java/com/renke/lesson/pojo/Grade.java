package com.renke.lesson.pojo;

/***
 * �꼶
 * �������ԣ���ʱ������ʱʱ��min���μ���Ϣ���Ͽ�����
 * @author renke.zuo@foxmail.com
 * @time 2017-03-09 17:37:40
 */
public class Grade {
	//ʱ��
	private int mins;
	//�Ͽ�����
	private int workDays;
	//ÿ���ʱ��
	private int dayHours;
	//�γ���
	private int courses;
	
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	public int getWorkDays() {
		return workDays;
	}
	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}
	public int getDayHours() {
		return dayHours;
	}
	public void setDayHours(int dayHours) {
		this.dayHours = dayHours;
	}
	public int getCourses() {
		return courses;
	}
	public void setCourses(int courses) {
		this.courses = courses;
	}
}
