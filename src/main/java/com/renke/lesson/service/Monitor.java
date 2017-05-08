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
	
	
	//��Ҫ����
	//1���༶����ʦ�������ʣ���Ŀ���
	//2���༶ʣ�����Ŀ��ʱ��
	//3���༶���տα����
	//4����ʦ����ʣ���ʱ��
	//5����ʦ�����
	
}
