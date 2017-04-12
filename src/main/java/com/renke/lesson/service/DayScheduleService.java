package com.renke.lesson.service;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.MyTest;
import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;
import com.renke.lesson.tools.RandomTool;

/***
 * FIXME ����߳��жϣ���ô����
 * 
 * @author Z.R.K
 * @time 2017-04-11 09:13:05
 */
public class DayScheduleService implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(DayScheduleService.class);
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	//��������
	private Klass klass ;
	//���߳�
	private Thread mainThread;

	public DayScheduleService(Klass klass){
		this.klass = klass;
	}
	
	@Override
	public void run() {
		logger.debug("thread-{} run",Thread.currentThread().getId());
		//��ȡ�༶��Ŀ
		Course[] courses = klass.getCourses();
		
		int[] courseIndexs = new int[courses.length];
		
		//���տ�ʱ����ѭ��
		for(int i=0;i<klass.getLessonCount();i++){
			RandomTool courseRandom = new RandomTool(courseIndexs);
			//ȡ�ÿ�ĿΪֹ
			while(true){
				int courseIndex = courseRandom.nextIndex();
				Course course = courses[courseIndex];
				Teacher teacher = klass.getTeachers()[courseIndex];
				if(teacher == null){
					teacher = course.getTeacher();
					klass.getTeachers()[courseIndex] = teacher;
				}
				//�ж��Ƿ񵽴ﵱ����ʦ���ޣ����˾�break������ȡ��Ŀ
				//������Ϻ���������ȴ��������Ƿ���ϵ�������[ʣ���Ŀ��ʦ��ʱ����<��Ŀ��ʦ*6*������ȫ��ͨ�������ɣ����������ü���]
				
				if(!teacher.useMinLesson()){
					int courseCount = klass.getCourseCounts()[courseIndex];
					if(courseCount > 0){
						if(teacher.hasLesson()){
							klass.getCourseCounts()[courseIndex] = courseCount - 1;
							klass.setDayTeachers(i, teacher);
							break;
						}
					}
				}else{
					//�ж�������ʦ�Ƿ��Ѿ�ƽ�⣬�������
					if(checkTeacher(courses)){
						int courseCount = klass.getCourseCounts()[courseIndex];
						if(courseCount > 0){
							if(teacher.hasLesson()){
								klass.getCourseCounts()[courseIndex] = courseCount - 1;
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
		//ִ����Ϻ󣬻������߳�
		klass.setDone(true);
		LockSupport.unpark(mainThread);
	}
	
	/**
	 * ��ȡindex�ķ�ʽ
	 * ����Ȩ�����
	 * 
	 * 
	 * @param courses
	 * @return
	 */
	private int getCourseIndex(Course[] courses,int courseIndex){
		if(courseIndex > 0){
			courseIndex --;
		}else{
			courseIndex = courses.length - 1;
		}
		return courseIndex;
	}

	
	private boolean checkTeacher(Course[] courses){
		for(Course course  : courses ){
			Teacher[] teachers = course.getTeachers();
			for(Teacher teacher: teachers){
				if(!teacher.useMinLesson()){
					return false;
				}
			}
		}
		return true;
	}
	
	private String getDayTeacher(){
		String result = "";
		Teacher[] teachers = klass.getDayTeachers();
		for(Teacher  teacher : teachers){
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
