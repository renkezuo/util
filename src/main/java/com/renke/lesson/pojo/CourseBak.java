package com.renke.lesson.pojo;

public class CourseBak {
	//教师
	private TeacherBak teacher;
	//总天数，默认无
	private int allDays;
	//已经排课天数
	private int useDays;
	//课时数
	private int allHours;
	//使用课时数
	private int useHours;
	//课程级别
	private Level level;
	
	public TeacherBak getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherBak teacher) {
		this.teacher = teacher;
	}
	public int getAllDays() {
		return allDays;
	}
	public void setAllDays(int allDays) {
		this.allDays = allDays;
	}
	public int getUseDays() {
		return useDays;
	}
	public void setUseDays(int useDays) {
		this.useDays = useDays;
	}
	public int getAllHours() {
		return allHours;
	}
	public void setAllHours(int allHours) {
		this.allHours = allHours;
	}
	public int getUseHours() {
		return useHours;
	}
	public void setUseHours(int useHours) {
		this.useHours = useHours;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
