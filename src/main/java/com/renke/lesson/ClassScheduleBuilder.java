package com.renke.lesson;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;

public class ClassScheduleBuilder{
	//老师列表
	private Teacher[] teachers;
	//课程列表
	private Course[] courses;
	//班级列表
	private Klass[] klasses;
	
	public ClassScheduleBuilder(Teacher[] teachers,Course[] courses,Klass[] klasses){
		//初始化，老师[班级数，课时数]，科目[老师列表]，班级[科目列表]
		this.teachers = teachers;
		this.courses = courses;
		this.klasses = klasses;
	}
	
	//获取班级科目
	
	//获取老师科目
	
	//检查老师科目
}
