package com.renke.lesson.pojo;

import java.util.List;

/***
 * 班级
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

	//科目列表
	private Course[] courses;
	//周科目课时数
	private int[] weekNeedCourseLessons;
	//单日科目最大课时数
	private int[] dayMaxCourseLessons;
	//科目最多天数
	private int[] courseMaxDays;
	//科目可能的组合
	//第一种：1*[0]+2*[1]+3*[2]+4*[3]集合 1*x+2*y+3*z+4*n
	//第二种：[indexN]:[0-4] 0 2 3 1 0
	private List<List<int[]>> courseCombi;
	//科目对应老师
	private TeacherBak[] teachers;
	private int maxWeekLessons;
	//每天，上午课时数和下午课时数
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

	//变量
	//是否完成课表
	private volatile boolean isDone;
	//当日老师
	private TeacherBak[] dayTeachers;
	//科目剩余总课时数
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
