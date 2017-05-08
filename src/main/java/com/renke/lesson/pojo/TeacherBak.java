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
public class TeacherBak extends Base{
	//常量
	private int maxLesson;
	private int minLesson;
	private int maxClass;
	private Course course;
	
	//条件变量
	private AtomicInteger surplusClass;
	private AtomicInteger useLesson;
	private int day;
	private volatile boolean hasLesson = true;
	private volatile boolean useMinLesson = false;
	private volatile boolean hasClass = true;
	
	public int getMaxLesson() {
		return maxLesson;
	}
	public void setMaxLesson(int maxLesson) {
		this.maxLesson = maxLesson;
	}
	public int getMinLesson() {
		return minLesson;
	}
	public void setMinLesson(int minLesson) {
		this.minLesson = minLesson;
	}
	public int getUseLesson() {
		return useLesson.get();
	}
	public void setUseLesson(int useLesson) {
		this.useLesson = new AtomicInteger(useLesson);
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void resetDay(int day){
		setUseLesson(0);
		useMinLesson = false;
		this.day = day;
	}
	

	public void resetAll(){
		setUseLesson(0);
		setSurplusClass(maxClass);
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
			if(useLesson.incrementAndGet() > maxLesson){
				hasLesson = false;
			}
		}
		return hasLesson;
	}
	
	public synchronized boolean useMinLesson(){
		if(!useMinLesson){
			if(useLesson.get() >= minLesson){
				useMinLesson = true;
			}
		}
		return useMinLesson;
	}
}
