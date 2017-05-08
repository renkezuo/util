package com.renke.lesson.pojo;

import java.util.List;

/***
 * �༶
 * @author Z.R.K
 * @time 2017-04-07 11:55:13
 */
public class Klass3 extends Base{
	private int lessonCount;
	private int userCount;
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	//��Ŀ�б�
	private Course[] courses;
	//�ܿ�Ŀ��ʱ��
	private int[] weekNeedCourseLessons;
	//���տ�Ŀ����ʱ��
	private int[] dayMaxCourseLessons;
	//��Ŀ�������
	private int[] courseMaxDays;
	//��Ŀ���ܵ����
	//��һ�֣�1*[0]+2*[1]+3*[2]+4*[3]���� 1*x+2*y+3*z+4*n
	//�ڶ��֣�[indexN]:[0-4] 0 2 3 1 0
	private List<List<int[]>> courseCombi;
	//��Ŀ��Ӧ��ʦ
	private TeacherBak[] teachers;
	private int maxWeekLessons;
	//ÿ�죬�����ʱ���������ʱ��
	private int[] dayAMLessons;
	private int[] dayPMLessons;
	
	public int[] getDayAMLessons() {
		return dayAMLessons;
	}
	public void setDayAMLessons(int[] dayAMLessons) {
		this.dayAMLessons = dayAMLessons;
	}
	public int[] getDayPMLessons() {
		return dayPMLessons;
	}
	public void setDayPMLessons(int[] dayPMLessons) {
		this.dayPMLessons = dayPMLessons;
	}

	//����
	//�Ƿ���ɿα�
	private volatile boolean isDone;
	//������ʦ
	private TeacherBak[] dayTeachers;
	//��Ŀʣ���ܿ�ʱ��
	private int[] courseSurplusCounts;
	private List<List<Integer>> lessonsCoursesIndex;
	
	public int getLessonCount() {
		return lessonCount;
	}
	public void setLessonCount(int lessonCount) {
		this.lessonCount = lessonCount;
		this.dayTeachers = new TeacherBak[lessonCount];
	}
	public Course[] getCourses() {
		return courses;
	}
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	public int[] getCourseSurplusCounts() {
		return courseSurplusCounts;
	}
	public void setCourseSurplusCounts(int[] courseSurplusCounts) {
		this.courseSurplusCounts = courseSurplusCounts;
	}
	public int[] getWeekNeedCourseLessons() {
		return weekNeedCourseLessons;
	}
	public void setWeekNeedCourseLessons(int[] weekNeedCourseLessons) {
		this.weekNeedCourseLessons = weekNeedCourseLessons;
	}
	public int[] getDayMaxCourseLessons() {
		return dayMaxCourseLessons;
	}
	public void setDayMaxCourseLessons(int[] dayMaxCourseLessons) {
		this.dayMaxCourseLessons = dayMaxCourseLessons;
	}
	public int[] getCourseMaxDays() {
		return courseMaxDays;
	}
	public void setCourseMaxDays(int[] courseMaxDays) {
		this.courseMaxDays = courseMaxDays;
	}
	public List<List<int[]>> getCourseCombi() {
		return courseCombi;
	}
	public void setCourseCombi(List<List<int[]>> courseCombi) {
		this.courseCombi = courseCombi;
	}
	public TeacherBak[] getTeachers() {
		return teachers;
	}
	public void setTeachers(TeacherBak[] teachers) {
		this.teachers = teachers;
	}

	public TeacherBak[] getDayTeachers() {
		return dayTeachers;
	}

	public void setDayTeachers(int index , TeacherBak teacher) {
		this.dayTeachers[index] = teacher;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public  List<List<Integer>> getLessonsCoursesIndex() {
		return lessonsCoursesIndex;
	}
	public void setLessonsCoursesIndex(List<List<Integer>> lessonsCoursesIndex) {
		this.lessonsCoursesIndex = lessonsCoursesIndex;
	}
	public int getMaxWeekLessons() {
		return maxWeekLessons;
	}
	public void setMaxWeekLessons(int maxWeekLessons) {
		this.maxWeekLessons = maxWeekLessons;
	}
	public void initLessonsCoursesIndex(){
		
	}
	public void resortLessonsCoursesIndex(){
		
	}
	
	public void resetDay(){
		setLessonCount(8);
		setDone(false);
	}
}
