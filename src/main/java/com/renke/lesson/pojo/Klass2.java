package com.renke.lesson.pojo;

/**
 * �༶
 * ��Ŀ��ʦ�ֵȼ��б�
 * ��ѧ���б�
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
