package com.renke.lesson.pojo;

/**
 * 班级
 * 科目老师分等级列表
 * 教学日列表
 * 
 * @author Z.R.K
 * @time 2017-04-17 14:13:37
 */
public class Klass2 extends Base{
	private CourseTeacher[][] levelCourseTeachers;
	private Day[] days;
	public CourseTeacher[][] getLevelCourseTeachers() {
		return levelCourseTeachers;
	}
	public void setLevelCourseTeachers(CourseTeacher[][] levelCourseTeachers) {
		this.levelCourseTeachers = levelCourseTeachers;
	}
	public Day[] getDays() {
		return days;
	}
	public void setDays(Day[] days) {
		this.days = days;
	}
}
