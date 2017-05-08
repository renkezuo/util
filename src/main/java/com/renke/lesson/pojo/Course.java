package com.renke.lesson.pojo;

/***
 * ��Ŀ��Ϣ
 * ��ʦ�б�
 * ���ȼ�
 * @author Z.R.K
 * @time 2017-04-07 14:29:01
 */
public class Course extends Base{
	private TeacherBak[] teachers;
	private int level;
	private int index = 0;
	
	public void setTeachers(TeacherBak[] teachers){
		this.teachers = teachers;
	}
	public TeacherBak[] getTeachers(){
		return this.teachers;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	//��ȡ��Ŀ��ʦ[��ƽ����]
	public TeacherBak getTeacher(){
		if(teachers != null)
			for(int i=0;i<teachers.length;i++){
				index ++;
				TeacherBak teacher = teachers[index%teachers.length];
				if(teacher.hasClass()){
					return teacher;
				}
			}
		return null;
	}
}
