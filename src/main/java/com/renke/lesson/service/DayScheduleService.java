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
 * FIXME ����߳��жϣ���ô����
 * 
 * @author Z.R.K
 * @time 2017-04-11 09:13:05
 */
public class DayScheduleService implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(DayScheduleService.class);
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	//��������
	private Klass3 klass ;
	//���߳�
	private Thread mainThread;

	public DayScheduleService(Klass3 klass){
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
				TeacherBak teacher = klass.getTeachers()[courseIndex];
				//�ж��Ƿ񵽴ﵱ����ʦ���ޣ����˾�break������ȡ��Ŀ
				//������Ϻ���������ȴ��������Ƿ���ϵ�������[ʣ���Ŀ��ʦ��ʱ����<��Ŀ��ʦ*6*������ȫ��ͨ�������ɣ����������ü���]
				
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
					//�ж�������ʦ�Ƿ��Ѿ�ƽ�⣬�������
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
		//ִ����Ϻ󣬻������߳�
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
