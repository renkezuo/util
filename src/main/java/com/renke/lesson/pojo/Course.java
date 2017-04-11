package com.renke.lesson.pojo;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * ��Ŀ��Ϣ
 * ��ʦ�б�
 * @author Z.R.K
 * @time 2017-04-07 14:29:01
 */
public class Course extends Base{
	private Teacher[] teachers;
	
	private AtomicInteger index = new AtomicInteger(0);
	
	public void setTeachers(Teacher[] teachers){
		this.teachers = teachers;
	} 
	
	//��ȡ��Ŀ��ʦ[��ƽ����]
	//��ʦ�༶��Ϊ0ʱ�����ɻ�ȡ
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
