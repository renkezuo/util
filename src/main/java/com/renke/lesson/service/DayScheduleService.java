package com.renke.lesson.service;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.MyTest;
import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass3;
import com.renke.lesson.pojo.TeacherBak;
import com.renke.lesson.tools.RandomTool;

/***
 * FIXME 如果线程中断，怎么处理？
 * 
 * @author Z.R.K
 * @time 2017-04-11 09:13:05
 */
public class DayScheduleService implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(DayScheduleService.class);
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	//操作对象
	private Klass3 klass ;
	//主线程
	private Thread mainThread;

	public DayScheduleService(Klass3 klass){
		this.klass = klass;
	}
	
	@Override
	public void run() {
		logger.debug("thread-{} run",Thread.currentThread().getId());
		//获取班级科目
		Course[] courses = klass.getCourses();
		
		int[] courseIndexs = new int[courses.length];
		
		//当日课时总数循环
		for(int i=0;i<klass.getLessonCount();i++){
			RandomTool courseRandom = new RandomTool(courseIndexs);
			//取得科目为止
			while(true){
				int courseIndex = courseRandom.nextIndex();
				TeacherBak teacher = klass.getTeachers()[courseIndex];
				//判断是否到达当日老师上限，到了就break，重新取科目
				//计算完毕后，在任务调度处，计算是否符合当日数据[剩余科目老师课时总数<科目老师*6*天数，全部通过，即可，第五天则不用计算]
				
				if(!teacher.useMinLesson()){
					int courseSurplusCount = klass.getCourseSurplusCounts()[courseIndex];
					if(courseSurplusCount > 0){
						if(teacher.hasLesson()){
							klass.getCourseSurplusCounts()[courseIndex] = courseSurplusCount - 1;
							klass.setDayTeachers(i, teacher);
							break;
						}
					}
				}else{
					//判断其他老师是否已经平衡，否则继续
					if(checkTeacher(courses)){
						int courseSurplusCount = klass.getCourseSurplusCounts()[courseIndex];
						if(courseSurplusCount > 0){
							if(teacher.hasLesson()){
								klass.getCourseSurplusCounts()[courseIndex] = courseSurplusCount - 1;
								klass.setDayTeachers(i, teacher);
								break;
							}
						}
					}
				}
			}
			courseRandom = null;
		}
		logger.debug("thread-{} end",Thread.currentThread().getId());
		System.out.println(klass.getName() +"--"+ count.incrementAndGet()+"--" + getDayTeacher());
		MyTest.printCourse(klass);
		//执行完毕后，唤醒主线程
		klass.setDone(true);
		LockSupport.unpark(mainThread);
	}
	
	private boolean checkTeacher(Course[] courses){
		for(Course course  : courses ){
			TeacherBak[] teachers = course.getTeachers();
			for(TeacherBak teacher: teachers){
				if(!teacher.useMinLesson()){
					return false;
				}
			}
		}
		return true;
	}
	
	private String getDayTeacher(){
		String result = "";
		TeacherBak[] teachers = klass.getDayTeachers();
		for(TeacherBak  teacher : teachers){
			result += teacher.getName() + "["+teacher.getCourse().getName() +"]\t";
		}
		return result;
	}
	
	public Thread getMainThread() {
		return mainThread;
	}

	public void setMainThread(Thread mainThread) {
		this.mainThread = mainThread;
	}
	
}
