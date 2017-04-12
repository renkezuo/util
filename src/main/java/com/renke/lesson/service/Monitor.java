package com.renke.lesson.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;

public class Monitor implements Runnable{
	private Map<Long, Teacher[]> courseTeachers;
	private Course[] courses;
	private Klass[] klasses;
//	private Map<String,Teacher[]> schedule;
	private final Logger logger = LoggerFactory.getLogger(Monitor.class);
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			printCutLine();
			printKlass();
			printTeacher();
		}
	}
	
	public void listener(Map<Long, Teacher[]> courseTeachers,Course[] courses,Klass[] klasses){
		this.courseTeachers = courseTeachers;
		this.klasses = klasses;
		this.courses = courses;
	}
	
	public void printCutLine(){
		System.out.println("---------------------------------------------------------------");
	}
	
	//需要监听
	//1、班级，老师绑定情况，剩余科目情况
	//2、班级剩余各科目课时数
	//3、班级当日课表情况
	//4、老师当日剩余课时数
	//5、老师绑定情况
	
	public void printKlass(){
		if(klasses == null) return; 
		for(Klass klass : klasses){
			Course[] courses = klass.getCourses();
			for(int index=0;index < courses.length;index ++){
				Teacher teacher =  klass.getTeachers()[index];
				logger.info("班级:{},科目:{},老师:{},剩余课时:{}",klass.getName(),courses[index].getName(),teacher == null ? null : teacher.getName()
						,klass.getCourseCounts()[index]);
			}
		}
	}
	
	public void printTeacher(){
		if(courseTeachers == null || courses == null) return;
		for(long i = 1;i< courses.length+1;i++){
			Teacher[] teachers = courseTeachers.get(i);
			for(Teacher teacher: teachers){
				logger.info("日期:{},老师:{},科目:{},可绑定班级:{},当日已分配课程数:{}"
						,teacher.getDay(),teacher.getName(),teacher.getCourse().getName()
						,teacher.getSurplusClass(),teacher.getUseLesson());
			}
		}
	}
	
}
