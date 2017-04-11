package com.renke.lesson;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;

/***
 * FIXME 如果线程中断，怎么处理？
 * 
 * @author Z.R.K
 * @time 2017-04-11 09:13:05
 */
public class BuilderTask implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(BuilderTask.class);
	
	//操作对象
	private Klass klass ;
	//主线程
	private Thread mainThread;

	public BuilderTask(Klass klass){
		this.klass = klass;
	}
	
	@Override
	public void run() {
		logger.debug("thread-{} run",Thread.currentThread().getId());
		//获取班级科目
		Course[] courses = klass.getCourses();
		for(int i=0;i<klass.getLessonCount();i++){
			setRandomTeacher(courses, i);
		}
		
		logger.debug("thread-{} end",Thread.currentThread().getId());
		//执行完毕后，唤醒主线程
		klass.setDone(true);
		LockSupport.unpark(mainThread);
	}

	
	//设置课堂
	private boolean setRandomTeacher(Course[] courses, int i) {
		int index = Klass.getRandomInt(courses.length - 1);
		//随机取科目
		Course course = courses[index];
		int courseId = course.getId().intValue();
		//判断科目是否有剩余[线程变量]，故不用考虑安全问题
		//问题，当剩余科目不够，剩余老师不够怎么处理？
		//均衡老师上课
		int courseCount = klass.getCourseCounts()[courseId - 1];
		if(courseCount <= 0){
			return setRandomTeacher(courses,i);
		}
		//获取科目对应老师
		Teacher teacher = klass.getTeachers()[index];
		if(teacher == null){
			teacher = course.getTeacher();
			klass.getTeachers()[index] = teacher;
		}
		//当日老师课时数少1
		if(teacher.hasLesson()){
			klass.getCourseCounts()[courseId - 1] = courseCount - 1;
			klass.setDayTeachers(i, teacher);
			return true;
		}else{
			return setRandomTeacher(courses,i);
		}
	}

	public Thread getMainThread() {
		return mainThread;
	}

	public void setMainThread(Thread mainThread) {
		this.mainThread = mainThread;
	}
	
}
