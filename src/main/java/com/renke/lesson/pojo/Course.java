package com.renke.lesson.pojo;

import com.renke.lesson.tools.RandomTool;

/***
 * ��Ŀ��Ϣ
 * ��ʦ�б�
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
	
	//��ȡ��Ŀ��ʦ[��ƽ����]
	//��ʦ�༶��Ϊ0ʱ�����ɻ�ȡ
	//��
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
