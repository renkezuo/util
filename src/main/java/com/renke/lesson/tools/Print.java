package com.renke.lesson.tools;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass3;
import com.renke.lesson.pojo.TeacherBak;
public class Print {
	private static final Logger logger = LoggerFactory.getLogger(Print.class);

	public static void cutLine(){
		System.out.println("---------------------------------------------------------------");
	}
	public static void klass(Klass3[] klasses){
		if(klasses == null) return; 
		for(Klass3 klass : klasses){
			Course[] courses = klass.getCourses();
			for(int index=0;index < courses.length;index ++){
				TeacherBak teacher =  klass.getTeachers()[index];
				logger.info("班级:{},科目:{},老师:{},剩余课时:{}",klass.getName(),courses[index].getName()
						,teacher.getName(),klass.getCourseSurplusCounts()[index]);
			}
		}
	}
	
	public static void teacher(Map<Long, TeacherBak[]> courseTeachers,Course[] courses){
		if(courseTeachers == null || courses == null) return;
		for(long i = 1;i< courses.length+1;i++){
			TeacherBak[] teachers = courseTeachers.get(i);
			for(TeacherBak teacher: teachers){
				logger.info("日期:{},老师:{},科目:{},可绑定班级:{},当日已分配课程数:{}"
						,teacher.getDay(),teacher.getName(),teacher.getCourse().getName()
						,teacher.getSurplusClass(),teacher.getUseLesson());
			}
		}
	}
	
	public static <T,K> void printMapList(Map<K,List<T>> map){
		Iterator<K> it = map.keySet().iterator();
		while(it.hasNext()){
			K key = it.next();
			List<T> list = map.get(key);
			String result = "";
			for(T t : list){
				result += ","+t;
			}
			if(result.length() == 0) result =",";
			logger.info("key:{},size{},value:{}",key,list.size(),"["+result.substring(1)+"]");
		}
	}
	
}
