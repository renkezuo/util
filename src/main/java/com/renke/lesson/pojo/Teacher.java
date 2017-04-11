package com.renke.lesson.pojo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 老师
 * 最大课时数/日
 * 剩余课时数/日
 * 最多班级数/学年
 * 剩余班级数/学年
 * @author Z.R.K
 * @time 2017-04-07 11:45:15
 */
public class Teacher extends Base{
	private int maxLesson;
	private AtomicInteger surplusLesson;
//	private int minLesson;
	private int maxClass;
	private AtomicInteger surplusClass;
	private Long courseId;
	private Course course;
	
	private volatile boolean hasLesson = true;
	private volatile boolean hasClass = true;
	
	public int getMaxLesson() {
		return maxLesson;
	}
	public void setMaxLesson(int maxLesson) {
		this.maxLesson = maxLesson;
	}
	public int getSurplusLesson() {
		return surplusLesson.get();
	}
	public void setSurplusLesson(int surplusLesson) {
		this.surplusLesson = new AtomicInteger(surplusLesson);
		this.hasLesson = true;
	}
	public int getMaxClass() {
		return maxClass;
	}
	public void setMaxClass(int maxClass) {
		this.maxClass = maxClass;
	}
	public int getSurplusClass() {
		return surplusClass.get();
	}
	public void setSurplusClass(int surplusClass) {
		this.surplusClass = new AtomicInteger(surplusClass);
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void resetDay(){
		setSurplusLesson(maxLesson);
	}
	
	public boolean hasClass(){
		if(hasClass){
			if(surplusClass.decrementAndGet() < 0){
				hasClass = false;
			}
		}
		return hasClass;
	}

	public boolean hasLesson(){
		if(hasLesson){
			if(surplusLesson.decrementAndGet() < 0){
				hasLesson = false;
			}
		}
		return hasLesson;
	}
}
