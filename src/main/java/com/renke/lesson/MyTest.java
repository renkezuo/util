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
import com.renke.lesson.pojo.Klass;
import com.renke.lesson.pojo.Teacher;
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
		
		//ÿ��8*7��ʱ��56��ʱ�������ʦ--->(4|5) * 12 + 3 * 2
		//����softʱ�����������ȡ����鿴������Ŀʱ��soft���������ȡ������Ŀ������ǣ������ȡ����ߵ�hard
		//���ƿ�Ŀ���⣬���ܵ����ʣ���Ŀ����ͬ
		//���ÿ������4�������ȡ��������
		
		
		
		//���֣�1����٣�2�����
		
		
		//���㵥��Ŀ���ܵķֲ�ģʽ
		//ÿ�������ٿ�ʱ�����������
		
		
		
		
		//ÿ��һ����ʦ����̬�޸ĺ�����ʦÿ��������Ҫ�����Ŀ�ʱ��
		//��Ҫ������ȡ����Щ��ʦʱ����Щ��ʦ�Ͳ�����ȡ��
		//���ԣ��α�Ӧ�����Ȼ�ȡ��ʦ��ʱ��������ʦ
		//ÿλ��ʦ����ÿ����Ҫ�������ٿ�ʱ��
		//��Ŀ��Ӧ��ʦ�б�
		Map<Long, Teacher[]> courseTeachers = initTeacher();
		//�༶��Ŀ�б�
		Course[] courses = initCourse(courseTeachers);
		//�༶�б�
		Klass[] klasses = initKlass(courses);
		
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		Map<String,Teacher[]> schedule = new HashMap<>();
		for(int i = 0;i<5;i++){
			//ÿ���̴߳���һ���༶
			for(Klass klass : klasses){
				BuilderTask task = new BuilderTask(klass);
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
			logger.debug("init Teacher and class");
			resetTeacher(courseTeachers);
			resetClass(klasses);
		}
		tpe.shutdown();
		printSchedule(schedule,klasses);
		analyseSchedule(schedule,klasses);

		logger.info("end; use {}ms" ,System.currentTimeMillis() - begin);
		
	}
	public static Course[] initCourse(Map<Long, Teacher[]> courseTeachers) {
		Course[] courses = new Course[11];
		for(int i=0;i<courses.length;i++){
			Course course = new Course();
			Long key = i+1L;
			Teacher[] teachers = courseTeachers.get(key);
			for(Teacher teacher:teachers){
				if(teacher.getCourse() == null)
					teacher.setCourse(course);
			}
			course.setId(key);
			course.setTeachers(teachers);
			switch (i) {
				case 0:
					course.setName("����");
					break;
				case 1:
					course.setName("��ѧ");
					break;
				case 2:
					course.setName("����");
					break;
				case 3:
					course.setName("����");
					break;
				case 4:
					course.setName("��ѧ");
					break;
				case 5:
					course.setName("����");
					break;
				case 6:
					course.setName("����");
					break;
				case 7:
					course.setName("��ʷ");
					break;
				case 8:
					course.setName("����");
					break;
				case 9:
					course.setName("����");
					break;
				default:
					course.setName("����");
					break;
			}
			courses[i] = course;
		}
		return courses;
	}
	public static Map<Long, Teacher[]> initTeacher() {
		Map<Long,Teacher[]> courseTeachers = new HashMap<>();
		//��������ʦ��2λ��6
		//��������ʷ�أ��弼��1λ��8
		for(int i = 0 ; i< 14 ; i++){
			Teacher teacher = new Teacher();
			Long courseId = i%11+1L;
			teacher.setId(i + 100L);
			teacher.setCourseId(courseId);
			teacher.setName("teacher"+(i+100));
			teacher.setMaxClass(4);
			teacher.setSurplusClass(4);
			teacher.setMaxLesson(6);
			teacher.setSurplusLesson(6);
			Teacher[] teachers = courseTeachers.get(courseId);
			if(teachers != null){
				Arrays.copyOf(teachers, teachers.length+1);
				teachers[teachers.length -1] = teacher;
			}else{
				teachers = new Teacher[1];
				teachers[0]=teacher;
			}
			courseTeachers.put(courseId, teachers);
		}
		return courseTeachers;
	}
	public static Klass[] initKlass(Course[] courses) {
		Klass[] klasses = new Klass[7];
		for(int i=0;i<7;i++){
			//�༶��Ŀ��ʱ��
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
			Klass klass = new Klass();
			klasses[i] = klass;
			klass.setCourses(courses);
			klass.setCourseCounts(courseCounts);
			klass.setLessonCount(8);
			klass.setName(101+i+"");
			klass.setId(100+i+1L);
		}
		return klasses;
	}
	
	public static boolean isDone(Klass[] klasses) {
		for(int i=0;i<klasses.length;i++){
			if(!klasses[i].isDone()){
				return false;
			}
		}
		return true;
	}
	
	public static void resetTeacher(Map<Long,Teacher[]> map) {
		Iterator<Long> it = map.keySet().iterator();
		while(it.hasNext()){
			Long key = it.next();
			Teacher[] teachers = map.get(key);
			if(teachers!=null && teachers.length >0){
				for(Teacher teacher : teachers){
					if(teacher!=null){
						teacher.resetDay();
					}
				}
			}
		}
		
	}
	
	public static void resetClass(Klass[] klasses) {
		for(int i=0;i<klasses.length;i++){
			klasses[i].resetDay();
		}
	}
	
	public static void cacheSchedule(Klass[] klasses
							,Map<String,Teacher[]> schedule
							,int index) {
		for(Klass klass : klasses){
			schedule.put(klass.getName()+index, klass.getDayTeachers());
		}
	}
	
	
	/**
	 * ��ӡ�α�
	 * @param schedule
	 * @param klasses
	 */
	public static void printSchedule(Map<String,Teacher[]> schedule,Klass[] klasses){
		System.out.println("�༶\t\t����һ\t\t���ڶ�\t\t������\t\t������\t\t������");
		for(Klass klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				Teacher teacher1 = schedule.get(klass.getName()+1)[i];
				Teacher teacher2 = schedule.get(klass.getName()+2)[i];
				Teacher teacher3 = schedule.get(klass.getName()+3)[i];
				Teacher teacher4 = schedule.get(klass.getName()+4)[i];
				Teacher teacher5 = schedule.get(klass.getName()+5)[i];
				System.out.println("��"+(i+1)+"�ڣ�\t"
					+ (teacher1 != null ? teacher1.getName(): null)
					+"\t"+(teacher2 != null ? teacher2.getName(): null)
					+"\t"+(teacher3 != null ? teacher3.getName(): null)
					+"\t"+(teacher4 != null ? teacher4.getName(): null)
					+"\t"+(teacher5 != null ? teacher5.getName(): null));
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
	public static void analyseSchedule(Map<String,Teacher[]> schedule,Klass[] klasses){
		Map<String,Integer> teacherDay = new HashMap<>();
		Map<String,Integer> classCourseWeek = new HashMap<>();
		for(Klass klass : klasses){
			for(int i = 0 ; i < 8 ; i++){
				for(int k=1;k<6;k++){
					Teacher teacher = schedule.get(klass.getName()+k)[i];
					Long courseId = teacher.getCourseId();
					String courseKey = klass.getName()+"-"+courseId;
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
	
	
	public static void printScheduleDay(Map<String,Teacher[]> schedule,Klass[] klasses,int day){
		System.out.println("�༶\t\t");
		for(Klass klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				Teacher teacher = schedule.get(klass.getName()+day)[i];
				System.out.println("��"+(i+1)+"�ڣ�\t"
					+ (teacher != null ? teacher.getName(): null));
			}

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
