package com.renke.lesson.pojo;

public class CourseBak {
	//��ʦ
	private TeacherBak teacher;
	//��������Ĭ����
	private int allDays;
	//�Ѿ��ſ�����
	private int useDays;
	//��ʱ��
	private int allHours;
	//ʹ�ÿ�ʱ��
	private int useHours;
	//�γ̼���
	private Level level;
	
	public TeacherBak getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherBak teacher) {
		this.teacher = teacher;
	}
	public int getAllDays() {
		return allDays;
	}
	public void setAllDays(int allDays) {
		this.allDays = allDays;
	}
	public int getUseDays() {
		return useDays;
	}
	public void setUseDays(int useDays) {
		this.useDays = useDays;
	}
	public int getAllHours() {
		return allHours;
	}
	public void setAllHours(int allHours) {
		this.allHours = allHours;
	}
	public int getUseHours() {
		return useHours;
	}
	public void setUseHours(int useHours) {
		this.useHours = useHours;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
