package com.renke.lesson.service;

import java.util.Map;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass3;
import com.renke.lesson.pojo.TeacherBak;
import com.renke.lesson.tools.Print;

public class Monitor implements Runnable{
	private Map<Long, TeacherBak[]> courseTeachers;
	private Course[] courses;
	private Klass3[] klasses;
//	private Map<String,Teacher[]> schedule;
	
	@Override
	public void run() {
		while(true){
			Print.klass(klasses);
			Print.teacher(courseTeachers,courses);
			Print.cutLine();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listener(Map<Long, TeacherBak[]> courseTeachers,Course[] courses,Klass3[] klasses){
		this.courseTeachers = courseTeachers;
		this.klasses = klasses;
		this.courses = courses;
	}
	
	
	//需要监听
	//1、班级，老师绑定情况，剩余科目情况
	//2、班级剩余各科目课时数
	//3、班级当日课表情况
	//4、老师当日剩余课时数
	//5、老师绑定情况
	
}
