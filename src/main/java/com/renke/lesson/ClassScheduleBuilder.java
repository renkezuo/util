package com.renke.lesson;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;

public class ClassScheduleBuilder{
	//��ʦ�б�
	private Teacher[] teachers;
	//�γ��б�
	private Course[] courses;
	//�༶�б�
	private Klass[] klasses;
	
	public ClassScheduleBuilder(Teacher[] teachers,Course[] courses,Klass[] klasses){
		//��ʼ������ʦ[�༶������ʱ��]����Ŀ[��ʦ�б�]���༶[��Ŀ�б�]
		this.teachers = teachers;
		this.courses = courses;
		this.klasses = klasses;
	}
	
	//��ȡ�༶��Ŀ
	
	//��ȡ��ʦ��Ŀ
	
	//�����ʦ��Ŀ
}
