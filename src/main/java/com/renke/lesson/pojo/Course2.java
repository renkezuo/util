package com.renke.lesson.pojo;

/***
 * 科目信息
 * 老师列表
 * 优先级
 * @author Z.R.K
 * @time 2017-04-07 14:29:01
 */
public class Course2 extends Base{
	private Teacher2[] teachers;
	private int level;
	private int index = 0 ;
	
	public void setTeachers(Teacher2[] teachers){
		this.teachers = teachers;
	}
	public Teacher2[] getTeachers(){
		return this.teachers;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Teacher2 getTeacher(){
		return teachers[index++%teachers.length];
	}
}
