package com.renke.lesson.pojo;

import com.renke.lesson.tools.RandomTool;

/***
 * 科目信息
 * 老师列表
 * @author Z.R.K
 * @time 2017-04-07 14:29:01
 */
public class Course extends Base{
	private Teacher[] teachers;
	
//	private AtomicInteger index = new AtomicInteger(0);
	
	public void setTeachers(Teacher[] teachers){
		this.teachers = teachers;
	}
	public Teacher[] getTeachers(){
		return this.teachers;
	}
	
	//获取科目老师[公平分配]
	//老师班级数为0时，不可获取
	//锁
	public Teacher getTeacher(){
		RandomTool teacherRandom = new RandomTool(new int[teachers.length]);
		if(teachers == null) return null;
		while(true){
			Teacher teacher = teachers[teacherRandom.nextIndex()];
			if(teacher.hasClass()){
				teacherRandom = null;
				return teacher;
			}
		}
		
	}
}
