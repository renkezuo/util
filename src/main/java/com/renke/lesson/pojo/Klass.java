package com.renke.lesson.pojo;

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
	private int lessonCount;
	private Course[] courses;
	private Teacher[] teachers;
	
	//����
	private volatile boolean isDone;
	private Teacher[] dayTeachers;
	private int[] courseCounts;
	
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
