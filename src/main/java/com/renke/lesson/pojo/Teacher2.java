package com.renke.lesson.pojo;

public class Teacher2 extends Base{
	//³£Á¿
	private int maxLessonUnit;
	private Course2 course;
	private volatile int useLessonUnit;
	private volatile boolean hasLessonUnit = true;
	public int getMaxLessonUnit() {
		return maxLessonUnit;
	}
	public void setMaxLessonUnit(int maxLessonUnit) {
		this.maxLessonUnit = maxLessonUnit;
	}
	public Course2 getCourse() {
		return course;
	}
	public void setCourse(Course2 course) {
		this.course = course;
	}
	public int getUseLessonUnit() {
		return useLessonUnit;
	}
	public void setUseLessonUnit(int useLessonUnit) {
		this.useLessonUnit = useLessonUnit;
	}

	public void reset(int day){
		setUseLessonUnit(0);
		hasLessonUnit = true;
	}

	public boolean checkAndAddUnit(int i){
		if(hasLessonUnit){
			useLessonUnit = useLessonUnit+i;
			if(useLessonUnit > maxLessonUnit){
				hasLessonUnit = false;
				useLessonUnit = useLessonUnit - i;
			}
		}
		return hasLessonUnit;
	}

}
