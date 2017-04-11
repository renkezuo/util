package com.renke.lesson;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;

/***
 * FIXME ����߳��жϣ���ô����
 * 
 * @author Z.R.K
 * @time 2017-04-11 09:13:05
 */
public class BuilderTask implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(BuilderTask.class);
	
	//��������
	private Klass klass ;
	//���߳�
	private Thread mainThread;

	public BuilderTask(Klass klass){
		this.klass = klass;
	}
	
	@Override
	public void run() {
		logger.debug("thread-{} run",Thread.currentThread().getId());
		//��ȡ�༶��Ŀ
		Course[] courses = klass.getCourses();
		for(int i=0;i<klass.getLessonCount();i++){
			setRandomTeacher(courses, i);
		}
		
		logger.debug("thread-{} end",Thread.currentThread().getId());
		//ִ����Ϻ󣬻������߳�
		klass.setDone(true);
		LockSupport.unpark(mainThread);
	}

	
	//���ÿ���
	private boolean setRandomTeacher(Course[] courses, int i) {
		int index = Klass.getRandomInt(courses.length - 1);
		//���ȡ��Ŀ
		Course course = courses[index];
		int courseId = course.getId().intValue();
		//�жϿ�Ŀ�Ƿ���ʣ��[�̱߳���]���ʲ��ÿ��ǰ�ȫ����
		//���⣬��ʣ���Ŀ������ʣ����ʦ������ô����
		//������ʦ�Ͽ�
		int courseCount = klass.getCourseCounts()[courseId - 1];
		if(courseCount <= 0){
			return setRandomTeacher(courses,i);
		}
		//��ȡ��Ŀ��Ӧ��ʦ
		Teacher teacher = klass.getTeachers()[index];
		if(teacher == null){
			teacher = course.getTeacher();
			klass.getTeachers()[index] = teacher;
		}
		//������ʦ��ʱ����1
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
