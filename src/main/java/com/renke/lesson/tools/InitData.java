package com.renke.lesson.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Course2;
import com.renke.lesson.pojo.Day;
import com.renke.lesson.pojo.Klass3;
import com.renke.lesson.pojo.Klass2;
import com.renke.lesson.pojo.LessonUnit;
import com.renke.lesson.pojo.TeacherBak;
import com.renke.lesson.pojo.Teacher2;

public class InitData {
	
	public static Course[] courses(Map<Long, TeacherBak[]> courseTeachers) {
		Course[] courses = new Course[11];
		for(int i=0;i<courses.length;i++){
			Course course = new Course();
			Long key = i+1L;
			TeacherBak[] teachers = courseTeachers.get(key);
			for(TeacherBak teacher:teachers){
				if(teacher.getCourse() == null)
					teacher.setCourse(course);
			}
			course.setId(key);
			course.setTeachers(teachers);
			switch (i) {
				case 0:
					course.setName("语文");
					break;
				case 1:
					course.setName("数学");
					break;
				case 2:
					course.setName("外语");
					break;
				case 3:
					course.setName("物理");
					break;
				case 4:
					course.setName("化学");
					break;
				case 5:
					course.setName("生物");
					break;
				case 6:
					course.setName("政治");
					break;
				case 7:
					course.setName("历史");
					break;
				case 8:
					course.setName("地理");
					break;
				case 9:
					course.setName("体育");
					break;
				default:
					course.setName("技术");
					break;
			}
			courses[i] = course;
		}
		return courses;
	}
	
	public static Map<Long, TeacherBak[]> teachers() {
		Map<Long,TeacherBak[]> courseTeachers = new HashMap<>();
		//语数外老师各2位：6
		//理化生，政史地，体技各1位：8
		for(int i = 0 ; i< 14 ; i++){
			TeacherBak teacher = new TeacherBak();
			Long courseId = i%11+1L;
			teacher.setId(i + 100L);
			teacher.setName("teacher"+(i+100));
			teacher.setDay(1);
			if(i%11 < 3){
				teacher.setMaxClass(4);
				teacher.setSurplusClass(4);
			}else{
				teacher.setMaxClass(7);
				teacher.setSurplusClass(7);
			}
			teacher.setMinLesson(4);
			if(i%11>8){
				teacher.setMinLesson(3);
			}
			teacher.setMaxLesson(6);
			teacher.setUseLesson(0);
			TeacherBak[] newTeachers = null;
			TeacherBak[] oldTeachers = courseTeachers.get(courseId);
			if(oldTeachers != null){
				newTeachers = Arrays.copyOf(oldTeachers, oldTeachers.length+1);
				newTeachers[newTeachers.length - 1] = teacher;
			}else{
				newTeachers = new TeacherBak[1];
				newTeachers[0]=teacher;
			}
			oldTeachers = null;
			courseTeachers.put(courseId, newTeachers);
		}
		return courseTeachers;
	}
	
	public static Klass3[] klasses(Course[] courses) {
		Klass3[] klasses = new Klass3[7];
		for(int i=0;i<7;i++){
			//班级科目课时数
			int[] courseCounts = new int[11];
			for(int c=0;c<11;c++){
				if(c<3){
					courseCounts[c] = 6;
				}else if(c<9){
					courseCounts[c] = 3;
				}else{
					courseCounts[c] = 2;
				}
			}
			Klass3 klass = new Klass3();
			klasses[i] = klass;
			klass.setCourses(courses);
			klass.setCourseSurplusCounts(courseCounts);
			klass.setCourseCombi(new ArrayList<List<int[]>>());
			klass.setLessonCount(8);
			
			klass.setMaxWeekLessons(6);
			klass.setCourseMaxDays(new int[courses.length]);
			klass.setDayMaxCourseLessons(new int[courses.length]);
			klass.setWeekNeedCourseLessons(new int[courses.length]);
			klass.setTeachers(new TeacherBak[courses.length]);
			for(int index=0;index<courses.length;index++){
				//语数外
				if(index < 3){
					klass.getCourseMaxDays()[index] = 5;
					klass.getDayMaxCourseLessons()[index] = 3;
					klass.getWeekNeedCourseLessons()[index] = 6;
				//政史地，理化生	
				}else if (index < 9){
					klass.getCourseMaxDays()[index] = 5;
					klass.getDayMaxCourseLessons()[index] = 1;
					klass.getWeekNeedCourseLessons()[index] = 3;
				}else{
					klass.getCourseMaxDays()[index] = 5;
					klass.getDayMaxCourseLessons()[index] = 1;
					klass.getWeekNeedCourseLessons()[index] = 2;
				}
				klass.getTeachers()[index] = courses[index].getTeacher();
			}
			klass.setDayAMLessons(new int[5]);
			klass.setDayPMLessons(new int[5]);
			klass.getDayAMLessons()[0] = 5;
			klass.getDayAMLessons()[1] = 5;
			klass.getDayAMLessons()[2] = 5;
			klass.getDayAMLessons()[3] = 5;
			klass.getDayAMLessons()[4] = 5;
			klass.getDayAMLessons()[0] = 3;
			klass.getDayAMLessons()[1] = 3;
			klass.getDayAMLessons()[2] = 3;
			klass.getDayAMLessons()[3] = 3;
			klass.getDayAMLessons()[4] = 3;
			
			klass.setName(101+i+"");
			klass.setId(100+i+1L);
		}
		return klasses;
	}
	
	public static LessonUnit[] dayLessonUnit(){
		LessonUnit[] units = new LessonUnit[8];
		for(int i=0;i<8;i++){
			units[i] = new LessonUnit();
			units[i].setIndex(i);
			if(i<4){
				units[i].setLevel(2);
			}else if(i<7){
				units[i].setLevel(1);
			}else{
				units[i].setLevel(0);
			}
		}

		for(int i=0;i<8;i++){
			if(i != 3 && i!=7){
				units[i].setContinuous(units[i+1]);
			}
		}
		return units;
	}
	
	public static Day[] days(){
		Day[] days = new Day[5];
		for(int i=0;i<5;i++){
			days[i] = new Day();
			days[i].setIndex(i);
			days[i].setLessonUnit(dayLessonUnit());;
		}
		return days;
	}
	

	public static Map<Long, Teacher2[]> teachers2() {
		Map<Long,Teacher2[]> courseTeachers = new HashMap<>();
		//语数外老师各2位：6
		//理化生，政史地，体技各1位：8
		for(int i = 0 ; i< 14 ; i++){
			Teacher2 teacher2 = new Teacher2();
			Long courseKey = i%11+1L;
			teacher2.setId(i + 100L);
			teacher2.setName("teacher"+(i+100));
			teacher2.setMaxLessonUnit(6);
			teacher2.setUseLessonUnit(0);
			Teacher2[] newTeachers = null;
			Teacher2[] oldTeachers = courseTeachers.get(courseKey);
			if(oldTeachers != null){
				newTeachers = Arrays.copyOf(oldTeachers, oldTeachers.length+1);
				newTeachers[newTeachers.length - 1] = teacher2;
			}else{
				newTeachers = new Teacher2[1];
				newTeachers[0]=teacher2;
			}
			oldTeachers = null;
			courseTeachers.put(courseKey, newTeachers);
		}
		return courseTeachers;
	}
	
	public static Course2[] courses2(Map<Long, Teacher2[]> courseTeachers) {
		Course2[] courses = new Course2[11];
		for(int i=0;i<courses.length;i++){
			Course2 course = new Course2();
			Long key = i+1L;
			Teacher2[] teachers = courseTeachers.get(key);
			for(Teacher2 teacher:teachers){
				if(teacher.getCourse() == null)
					teacher.setCourse(course);
			}
			course.setId(key);
			course.setTeachers(teachers);
			switch (i) {
				case 0:
					course.setName("语文");
					course.setLevel(2);
					break;
				case 1:
					course.setName("数学");
					course.setLevel(2);
					break;
				case 2:
					course.setName("外语");
					course.setLevel(2);
					break;
				case 3:
					course.setName("物理");
					course.setLevel(1);
					break;
				case 4:
					course.setName("化学");
					course.setLevel(1);
					break;
				case 5:
					course.setName("生物");
					course.setLevel(1);
					break;
				case 6:
					course.setName("政治");
					course.setLevel(1);
					break;
				case 7:
					course.setName("历史");
					course.setLevel(1);
					break;
				case 8:
					course.setName("地理");
					course.setLevel(1);
					break;
				case 9:
					course.setName("体育");
					course.setLevel(0);
					break;
				default:
					course.setName("技术");
					course.setLevel(1);
					break;
			}
			courses[i] = course;
		}
		return courses;
	}
	

	public static Klass2[] klasses2(Course2[] courses) {
		Klass2[] klasses = new Klass2[7];
		for(int i=0;i<7;i++){
			//班级科目课时数
			int[] courseCounts = new int[11];
			for(int c=0;c<11;c++){
				if(c<3){
					courseCounts[c] = 6;
				}else if(c<9){
					courseCounts[c] = 3;
				}else{
					courseCounts[c] = 2;
				}
			}
			Klass2 klass = new Klass2();
			klasses[i] = klass;
//			klass.setCourses(courses);
			klass.setDays(days());
//			klass.setWeekCourseLessonUnitCount(new int[courses.length]);
//			klass.setDayMaxCourseLessonUnitCount(new int[courses.length]);
//			klass.setTeachers(new ArrayList<Teacher2[]>());
			for(int index=0;index<courses.length;index++){
				//语数外
				if(index < 3){
//					klass.getDayMaxCourseLessonUnitCount()[index] = 3;
//					klass.getWeekCourseLessonUnitCount()[index] = 6;
				//政史地，理化生	
				}else if (index < 9){
//					klass.getDayMaxCourseLessonUnitCount()[index] = 1;
//					klass.getWeekCourseLessonUnitCount()[index] = 3;
				}else{
//					klass.getDayMaxCourseLessonUnitCount()[index] = 1;
//					klass.getWeekCourseLessonUnitCount()[index] = 2;
				}
				List<Teacher2[]> teachers = new ArrayList<Teacher2[]>();
				Teacher2[] teacher2 = new Teacher2[1]; 
				teachers.add(teacher2);
				teacher2[0] = courses[index].getTeacher();
			}
			
			klass.setName(101+i+"");
			klass.setId(100+i+1L);
		}
		return klasses;
	}
}
