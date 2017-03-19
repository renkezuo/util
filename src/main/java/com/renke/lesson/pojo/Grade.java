package com.renke.lesson.pojo;

/***
 * 年级
 * 包含属性：课时数，课时时长min，课间休息，上课天数
 * @author renke.zuo@foxmail.com
 * @time 2017-03-09 17:37:40
 */
public class Grade {
	//时长
	private int mins;
	//上课天数
	private int workDays;
	//每天课时数
	private int dayHours;
	//课程数
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
