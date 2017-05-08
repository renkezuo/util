package com.renke.lesson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.lesson.pojo.Course;
import com.renke.lesson.pojo.Klass3;
import com.renke.lesson.pojo.TeacherBak;
import com.renke.lesson.service.DayScheduleService;
import com.renke.lesson.service.Monitor;
import com.renke.lesson.tools.InitData;
import com.renke.test.TestTask;

public class MyTest {
	
	private final static Logger logger = LoggerFactory.getLogger(MyTest.class);
	public static void main(String[] args) throws Exception {
		logger.info("start");
		Long begin = System.currentTimeMillis();
		
		//��ʼ������ʦ[�༶������ʱ������Ŀ]����Ŀ[��ʦ�б�]���༶[��Ŀ�б�]
		//7���࣬14����ʦ��11����Ŀ
		//ÿ��8��ʱ��ÿλ��ʦ���6��ʱ--->ÿ��40��ʱ
		//�����⣺6��ʱ/ÿ�ܣ���ʷ�أ�������3��ʱ/�ܣ��弼��2��ʱ/ÿ��--->�ϼ�18+18+4=40
		//ÿ��Ŀ������Ҫ����λ��ʦ��	�����⣺	42��ʱ/ÿ�ܣ�	��ʷ�أ�������	21��ʱ/�ܣ�	�弼��	14��ʱ/��
		//�����⣺	42��ʱ/ÿ�ܣ�9��ʱ/�գ�ÿλ��ʦ���6��ʱ����Ҫ2λ��ʦ��ÿ�칤��������4��ʱ��������6
		//��ʷ�أ�������21��ʱ/�ܣ�5��ʱ/�գ�ÿλ��ʦ���6��ʱ����Ҫ1λ��ʦ��ÿ�칤��������4��ʱ��������6
		//�弼��14��ʱ/�ܣ�3��ʱ/�գ�ÿλ��ʦ���6��ʱ����Ҫ1λ��ʦ��ÿ�칤��������3��ʱ
		
		//ʵ�ַ�ʽ
		//A���������а༶��Ŀ�Ŀ��ֲܷ�����ϣ������ϣ����α�
		//B��ÿ�������ȡ�α����գ��ж�[ʣ����ʦ��Ŀ����<�տ�ʱ����*����]�����һ�첻��Ҫ�ſ�
		//ÿ��8*7��ʱ��56��ʱ�������ʦ--->(4|5) * 12 + 3 * 2
		//����minʱ�����������ȡ����鿴������ʦ�Ƿ�ﵽmin���������ȡ������Ŀ������ǣ������ȡ����ߵ�max
		//���ÿ������4�������ȡ��������
		//���֣�1����٣�2�����
		//���㵥��Ŀ���ܵķֲ�ģʽ
		//ÿ�������ٿ�ʱ�����������
		//ÿ��һ����ʦ����̬�޸ĺ�����ʦÿ��������Ҫ�����Ŀ�ʱ��
		//��Ҫ������ȡ����Щ��ʦʱ����Щ��ʦ�Ͳ�����ȡ��
		//���ԣ��α�Ӧ�����Ȼ�ȡ��ʦ��ʱ��������ʦ
		//ÿλ��ʦ����ÿ����Ҫ�������ٿ�ʱ��
		
		
		//
		
		
		
		
		//��Ŀ��Ӧ��ʦ�б�
		Map<Long, TeacherBak[]> courseTeachers = InitData.teachers();
		//�༶��Ŀ�б�
		Course[] courses = InitData.courses(courseTeachers);
		//�༶�б�
		Klass3[] klasses = InitData.klasses(courses);
		
		Monitor monitor = new Monitor();
		monitor.listener(courseTeachers,courses,klasses);
		Thread thread = new Thread(monitor,"monitor");
		thread.start();
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		Map<String,TeacherBak[]> schedule = new HashMap<>();
				
		for(int i = 0;i<5;i++){
			//ÿ���̴߳���һ���༶
			for(Klass3 klass : klasses){
				DayScheduleService task = new DayScheduleService(klass);
				task.setMainThread(Thread.currentThread());
				tpe.execute(task);
			}
			while(!isDone(klasses)){
				logger.debug("block main");
				LockSupport.park();
				logger.debug("release main");
			}
			logger.debug("cache Schedule");
			cacheSchedule(klasses, schedule, i+1);
//			printScheduleDay(schedule,klasses,i+1);
			//��ʼ��[��ʦÿ�տ�ʱ���༶��ʱ��]
			logger.debug("reset Teacher and class");
			resetTeacher(courseTeachers,i+2);
			resetClass(klasses);
			for(Klass3 klass : klasses){
				printCourse(klass);
			}
			
		}
		tpe.shutdown();
		printSchedule(schedule,klasses);
		analyseSchedule(schedule,klasses);

		logger.info("end; use {}ms" ,System.currentTimeMillis() - begin);
		
	}
	public static boolean isDone(Klass3[] klasses) {
		for(int i=0;i<klasses.length;i++){
			if(!klasses[i].isDone()){
				return false;
			}
		}
		return true;
	}
	
	public static void resetTeacher(Map<Long,TeacherBak[]> map,int day) {
		Iterator<Long> it = map.keySet().iterator();
		while(it.hasNext()){
			Long key = it.next();
			TeacherBak[] teachers = map.get(key);
			if(teachers!=null && teachers.length >0){
				for(TeacherBak teacher : teachers){
					if(teacher!=null){
						teacher.resetDay(day);
					}
				}
			}
		}
	}
	
	public static void resetClass(Klass3[] klasses) {
		for(int i=0;i<klasses.length;i++){
			klasses[i].resetDay();
		}
	}
	
	public static void cacheSchedule(Klass3[] klasses
							,Map<String,TeacherBak[]> schedule
							,int index) {
		for(Klass3 klass : klasses){
			schedule.put(klass.getName()+index, klass.getDayTeachers());
		}
	}
	
	
	/**
	 * ��ӡ�α�
	 * @param schedule
	 * @param klasses
	 */
	public static void printSchedule(Map<String,TeacherBak[]> schedule,Klass3[] klasses){
		System.out.println("�༶\t\t����һ\t\t���ڶ�\t\t������\t\t������\t\t������");
		for(Klass3 klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				TeacherBak teacher1 = schedule.get(klass.getName()+1)[i];
				TeacherBak teacher2 = schedule.get(klass.getName()+2)[i];
				TeacherBak teacher3 = schedule.get(klass.getName()+3)[i];
				TeacherBak teacher4 = schedule.get(klass.getName()+4)[i];
				TeacherBak teacher5 = schedule.get(klass.getName()+5)[i];
				System.out.println("��"+(i+1)+"�ڣ�\t"
					+ (teacher1 != null ? teacher1.getName()+teacher1.getCourse().getName(): null)
					+"\t"+(teacher2 != null ? teacher2.getName()+teacher2.getCourse().getName(): null)
					+"\t"+(teacher3 != null ? teacher3.getName()+teacher3.getCourse().getName(): null)
					+"\t"+(teacher4 != null ? teacher4.getName()+teacher4.getCourse().getName(): null)
					+"\t"+(teacher5 != null ? teacher5.getName()+teacher5.getCourse().getName(): null));
			}
		}
	}
	
	
	/**
	 * 
	 * �����γ̱�
	 * ��ʦÿ���Ͽο�ʱ����
	 * �༶��Ŀ�ܿ�ʱ��
	 * 
	 */
	public static void analyseSchedule(Map<String,TeacherBak[]> schedule,Klass3[] klasses){
		Map<String,Integer> teacherDay = new HashMap<>();
		Map<String,Integer> classCourseWeek = new HashMap<>();
		for(Klass3 klass : klasses){
			for(int i = 0 ; i < 8 ; i++){
				for(int k=1;k<6;k++){
					TeacherBak teacher = schedule.get(klass.getName()+k)[i];
					String courseKey = klass.getName()+"-"+teacher.getCourse().getName();
					String teacherKey = teacher.getName()+"-"+k;
					Integer teacherCount = teacherDay.get(teacherKey);
					Integer courseCount = classCourseWeek.get(courseKey);
					if(teacherCount==null){
						teacherCount = 0;
					}
					if(courseCount==null){
						courseCount = 0;
					}
					teacherCount ++;
					courseCount ++;
					teacherDay.put(teacherKey, teacherCount);
					classCourseWeek.put(courseKey, courseCount);
				}
			}
		}
		
//		printMap(teacherDay);
		printMap(classCourseWeek);
	}
	
	
	public static void printScheduleDay(Map<String,TeacherBak[]> schedule,Klass3[] klasses,int day){
		System.out.println("�༶\t\t");
		for(Klass3 klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				TeacherBak teacher = schedule.get(klass.getName()+day)[i];
				System.out.println("��"+(i+1)+"�ڣ�\t"
					+ (teacher != null ? teacher.getName(): null));
			}

		}
	}
	
	public static void printCourseTeacher(Map<Long,TeacherBak[]> courseTeachers){
		for(long i = 1;i<12;i++){
			TeacherBak[] teachers = courseTeachers.get(i);
			for(TeacherBak teacher: teachers){
				System.out.println("course:"+teacher.getCourse().getName()+",teacher:"+teacher.getName()
				+",class:"+teacher.getSurplusClass());
			}
		}
		
	}
	
	public static void printCourse(Klass3 klass){
		Course[] courses = klass.getCourses();
		for(int index = 0 ; index < courses.length;index ++ ){
			Course course = courses[index];
			System.out.println(klass.getName()+"-->"+course.getName()+":"+klass.getCourseSurplusCounts()[index]+"["
						+(klass.getTeachers()[index] == null ? null : klass.getTeachers()[index].getName())+"]");
		}
	}
	
	
	public static void printMap(Map<String,? extends Object> map){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+":"+map.get(key));
		}
	}
	//�Ŵ��㷨
	//���ģ�ѡ�񣬽��棬����
	//  ���ֱ�׼�ƶ���ѡ��������
	//  ���棬ȡ��Ƭ���з�����ߣ��������
	//  ���죺
	
	//  ��ʦ[��Ŀ]	��ʱ��
	//  ÿ����ܿ�ʱ��	
	//  �༶��
	
	
	/**
	 * ������ 
	 * ÿ���ʱ��8���༶��7����ʱ����56
	 * һ��5�죬��40��ʱ��ȫ�꼶280��ʱ
	 * 11��Ŀ�������⣬��ʷ�أ��������弼
	 * 		�ֲ��������⣺6��ʱ/��Ŀ����18��ʱ
	 * 			��ʷ��������3��ʱ/��Ŀ����18��ʱ
	 * 			�弼��2��ʱ/��Ŀ����4��ʱ
	 * ��ô��ʦ��Ҫ���ٸ��أ�[ͨ�������ڿ�Ŀ��ʱ�����ɼ���������ʦ�����������ٵ�����ʦ��]
	 * ÿ����ʦһ�����6��ʱ��2��������ʦ��2����ѧ��ʦ��2��Ӣ�1��������Ŀ��ʦ
	 * ÿ�ܣ���ʦ��Ŀ�أ���ʱ��
	 */
	
	/**
	 * ʵ�֣�
	 * ���ݽṹ��
	 * ���ó���
	 * ��ʦ��������ʱ��������Ϳ�ʱ�������༶��
	 * �༶��ÿ�տ�ʱ������ʦ
	 * ��ʱ��������[�ڼ���]��������[�ڼ���]����Ŀ����ʦ���༶
	 * �ܿα��ɿ�ʱ���
	 * 
	 * id:name:priority:count
	 * 
	 * ��ʦ��[teacherPool]��[{id,name,lessonCount},{id,name,lessonCount}]
	 * ��Ŀ��[coursePool]	��[{id,cid,name,tId,priority},{id,cid,name,tId,priority}]
	 * �༶��[classPool]	��[{id,name,courses}]  --->courseid��ͬ��cid������ͬ
	 * 
	 * 
	 * ������
	 * 		ÿ��༶��Ŀ
	 * 
	 * ���ȡÿ���Ŀ�����ȡ��Ŀ�أ�
	 * 
	 */
	
	
	/***
	 * ÿ��ı�������ʦ��ʱ��/�༶��Ŀ��ʱ������
	 * ÿ��ĳ������༶��������ʱ�����༶����ʱ��
	 * 
	 * ÿ�ܱ������༶��Ŀ��ʱ��
	 * ÿ�ܳ������༶��Ŀ��Ӧ��ʦ
	 * 
	 * ѧ��������༶��Ŀ����ÿ�տ�ʱ��
	 * 
	 */
	
	
	
	
	
	//�ɿؿα�
	//��������
	//��ȡ�༶����ȡ[ѧ��+��ʦ]˳��Ϊ�����⣬�����أ�����������
	//ѭ������
	
	//���գ�ѧ��+�༶��Ϸ��������ؿ����б�
	//������
	//1��ѧ�Ƶ��ղ�����N��ѧʱ
	//2��ѧ�Ʊ��ܿ���ѧʱ����
	//3��ѧ�Ʊ��ܿ�������[���������һ��ʱ������ȫ��ѧʱ��������ֳ�ͻ�����Ƿ����쳣]
	//�༶����[�ɿأ����ɿ�]
	//---�ɿأ��ȼ���λ��������
	//ÿ���ȼ�һ���̣߳�������ʦ�ȼ�������
	
	
	//---���ɿأ��༶�������̣߳�ÿ������ͬ�ȼ���ʦ��Դ
//	@Test
	public void testRandom(){
		Random random = new Random();
		for(int i=0;i<100;i++)
			System.out.println(random.nextInt(10));
		
		System.out.println("����һ\t\t���ڶ�");
	}
	
//	@Test
	public void testThread() throws Exception{
		TestTask task = new TestTask();
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		for(int i=0;i<10;i++)
			tpe.execute(task);
		while(tpe.getActiveCount() > 0){
			Thread.sleep(3000);
		}
		tpe.shutdown();
	}
//	@Test
	public void testCopyArray(){
		String[] tests = {"123","456","789"};
		Arrays.copyOf(tests, 4);
		for(String str : tests){
			System.out.println(str);
		}
	}
	
	@Test
	public void testSet(){
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("d");
		set.add("e");
		set.add("c");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
}
