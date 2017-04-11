package com.renke.lesson.pojo;

import java.util.Random;

/***
 * �༶
 * ÿ�տ�ʱ��
 * ��Ŀ�б�
 * ��Ŀ��ʱ��
 * ��ʦ�б�
 * �����Ͽ���ʦ�б�
 * @author Z.R.K
 * @time 2017-04-07 11:55:13
 */
public class Klass extends Base{
	private static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>();
	private int lessonCount;
	private Course[] courses;
	private int[] courseCounts;
	private Teacher[] teachers;
	private Teacher[] dayTeachers;
	
	private volatile boolean isDone;

	public static int getRandomInt(int bound){
		Random random = threadLocal.get();
		if(random == null){
			random = new Random();
			threadLocal.set(random);
		}
		return random.nextInt(bound);
	}
	
	public int getLessonCount() {
		return lessonCount;
	}
	public void setLessonCount(int lessonCount) {
		this.lessonCount = lessonCount;
		this.dayTeachers = new Teacher[lessonCount];
	}
	public Course[] getCourses() {
		return courses;
	}
	public void setCourses(Course[] courses) {
		this.courses = courses;
		this.teachers = new Teacher[courses.length];
	}
	public int[] getCourseCounts() {
		return courseCounts;
	}
	public void setCourseCounts(int[] courseCounts) {
		this.courseCounts = courseCounts;
	}
	public Teacher[] getTeachers() {
		return teachers;
	}
	public void setTeachers(Teacher[] teachers) {
		this.teachers = teachers;
	}

	public Teacher[] getDayTeachers() {
		return dayTeachers;
	}

	public void setDayTeachers(int index , Teacher teacher) {
		this.dayTeachers[index] = teacher;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	public void resetDay(){
		setLessonCount(8);
		setDone(false);
	}
}
