package com.renke.lesson.pojo;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 科目信息
 * 老师列表
 * @author Z.R.K
 * @time 2017-04-07 14:29:01
 */
public class Course extends Base{
	private Teacher[] teachers;
	
	private AtomicInteger index = new AtomicInteger(0);
	
	public void setTeachers(Teacher[] teachers){
		this.teachers = teachers;
	} 
	
	//获取科目老师[公平分配]
	//老师班级数为0时，不可获取
	public Teacher getTeacher(){
		if(teachers == null) return null;
		Teacher teacher = teachers[index.incrementAndGet()%teachers.length];
		if(teacher.hasClass()){
			return teacher;
		}else{
			return getTeacher();
		}
	}
}
