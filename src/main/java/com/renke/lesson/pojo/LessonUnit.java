package com.renke.lesson.pojo;

/**
 * ��ʱ
 * ��ʱ�ȼ����ߣ��У���
 * ��ʱ�������ڼ��ڿ�
 * ��ֹʱ��
 * ���ÿ�ʱ[Ĭ�Ͽ�]
 * �γ�
 * �ο���ʦ
 * 
 * @author Z.R.K
 * @time 2017-04-17 14:04:51
 */
public class LessonUnit extends Base{
	private int level;
	private int index;
	private String beginTime;
	private String endTime;
	private LessonUnit continuous;
	private Course2 course;
	private Teacher2[] teachers;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public LessonUnit getContinuous() {
		return continuous;
	}
	public void setContinuous(LessonUnit continuous) {
		this.continuous = continuous;
	}
	public Course2 getCourse() {
		return course;
	}
	public void setCourse(Course2 course) {
		this.course = course;
	}
	public Teacher2[] getTeachers() {
		return teachers;
	}
	public void setTeachers(Teacher2[] teachers) {
		this.teachers = teachers;
	}
	
}
