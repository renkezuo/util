package com.renke.lesson.pojo;

public class CourseTeacher {
	private Course course;
	private TeacherBak[] teachers;
	private int weekCourseLessonUnitCount;
	private int dayMaxCourseLessonUnitCount;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public TeacherBak[] getTeachers() {
		return teachers;
	}
	public void setTeachers(TeacherBak[] teachers) {
		this.teachers = teachers;
	}
}
