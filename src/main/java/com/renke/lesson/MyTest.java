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
		
		//初始化，老师[班级数，课时数，科目]，科目[老师列表]，班级[科目列表]
		//7个班，14个老师，11个科目
		//每天8课时，每位老师最多6课时--->每周40课时
		//语数外：6课时/每周；政史地，理化生：3课时/周；体技：2课时/每周--->合计18+18+4=40
		
		//每科目最少需要多少位老师？	语数外：	42课时/每周；	政史地，理化生：	21课时/周；	体技：	14课时/周
		
		//语数外：	42课时/每周，9课时/日；每位老师最多6课时，需要2位老师，每天工作不少于4课时，不大于6
		//政史地，理化生：21课时/周；5课时/日；每位老师最多6课时，需要1位老师，每天工作不少于4课时，不大于6
		//体技：14课时/周；3课时/日；每位老师最多6课时，需要1位老师，每天工作不少于3课时
		
		//每天8*7课时，56课时，组合老师--->(4|5) * 12 + 3 * 2
		//满足soft时，如果继续获取，则查看其他科目时否soft，如果否，则取其他科目，如果是，则随机取。最高到hard
		//控制科目均衡，不能到最后剩余科目都相同
		//完成每日任务4，随机抽取补充任务
		
		
		
		//两种，1：穷举，2：随机
		
		
		//计算单科目可能的分布模式
		//每天最多多少课时，间隔，天数
		
		
		
		
		//每多一个老师，动态修改后面老师每天至少需要工作的课时数
		//需要处理，当取到哪些老师时，哪些老师就不给获取了
		//所以，课表应该优先获取老师课时不达标的老师
		//每位老师至少每天需要工作多少课时？
		//科目对应老师列表
		Map<Long, Teacher[]> courseTeachers = initTeacher();
		//班级科目列表
		Course[] courses = initCourse(courseTeachers);
		//班级列表
		Klass[] klasses = initKlass(courses);
		
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		Map<String,Teacher[]> schedule = new HashMap<>();
		for(int i = 0;i<5;i++){
			//每个线程处理一个班级
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
			//初始化[老师每日课时，班级课时数]
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
	public static Map<Long, Teacher[]> initTeacher() {
		Map<Long,Teacher[]> courseTeachers = new HashMap<>();
		//语数外老师各2位：6
		//理化生，政史地，体技各1位：8
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
	 * 打印课表
	 * @param schedule
	 * @param klasses
	 */
	public static void printSchedule(Map<String,Teacher[]> schedule,Klass[] klasses){
		System.out.println("班级\t\t星期一\t\t星期二\t\t星期三\t\t星期四\t\t星期五");
		for(Klass klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				Teacher teacher1 = schedule.get(klass.getName()+1)[i];
				Teacher teacher2 = schedule.get(klass.getName()+2)[i];
				Teacher teacher3 = schedule.get(klass.getName()+3)[i];
				Teacher teacher4 = schedule.get(klass.getName()+4)[i];
				Teacher teacher5 = schedule.get(klass.getName()+5)[i];
				System.out.println("第"+(i+1)+"节：\t"
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
	 * 分析课程表
	 * 老师每天上课课时总数
	 * 班级科目总课时数
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
		System.out.println("班级\t\t");
		for(Klass klass : klasses){
			System.out.println("class:"+klass.getId());
			for(int i = 0 ; i< 8 ; i++){
				Teacher teacher = schedule.get(klass.getName()+day)[i];
				System.out.println("第"+(i+1)+"节：\t"
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
	//遗传算法
	//核心：选择，交叉，变异
	//  评分标准制定，选择分数最高
	//  交叉，取出片段中分数最高，尝试组合
	//  变异：
	
	//  老师[科目]	课时数
	//  每天的总课时数	
	//  班级数
	
	
	/**
	 * 背景： 
	 * 每天课时数8，班级数7，课时数：56
	 * 一周5天，合40课时，全年级280课时
	 * 11科目：语数外，政史地，理化生，体技
	 * 		分布：语数外：6课时/科目，合18课时
	 * 			政史地理化生：3课时/科目，合18课时
	 * 			体技：2课时/科目，合4课时
	 * 那么老师需要多少个呢？[通过，周期科目课时数，可计算最少老师数，计算最少单科老师数]
	 * 每个老师一天最多6课时，2个语文老师，2个数学老师，2个英语，1个其他科目老师
	 * 每周，老师科目池，课时数
	 */
	
	/**
	 * 实现：
	 * 数据结构：
	 * 配置常量
	 * 老师：日最多课时数，日最低课时数，最多班级数
	 * 班级：每日课时数，老师
	 * 课时：日索引[第几天]，节索引[第几节]，科目，老师，班级
	 * 总课表：由课时组成
	 * 
	 * id:name:priority:count
	 * 
	 * 老师池[teacherPool]：[{id,name,lessonCount},{id,name,lessonCount}]
	 * 科目池[coursePool]	：[{id,cid,name,tId,priority},{id,cid,name,tId,priority}]
	 * 班级池[classPool]	：[{id,name,courses}]  --->courseid不同，cid不可相同
	 * 
	 * 
	 * 变量：
	 * 		每天班级科目
	 * 
	 * 随机取每天科目，随机取科目池，
	 * 
	 */
	
	
	/***
	 * 每天的变量：老师课时数/班级科目课时数上限
	 * 每天的常量：班级当日最大课时数，班级最大课时数
	 * 
	 * 每周变量：班级科目课时数
	 * 每周常量：班级科目对应老师
	 * 
	 * 学年变量：班级科目数，每日课时数
	 * 
	 */
	
	
	
	
	
	//可控课表
	//加载数据
	//提取班级，提取[学科+教师]顺序为语数外，政历地，理化生，技。
	//循环日期
	
	//单日，学科+班级组合方法，返回可能列表
	//条件，
	//1、学科当日不超过N个学时
	//2、学科本周可用学时数，
	//3、学科本周可用天数[当天数最后一天时，分配全部学时，如果出现冲突，则标记分配异常]
	//班级处理[可控，不可控]
	//---可控：等级单位处理数据
	//每个等级一个线程，根据老师等级，均分
	
	
	//---不可控：班级数量的线程，每天争抢同等级教师资源
//	@Test
	public void testRandom(){
		Random random = new Random();
		for(int i=0;i<100;i++)
			System.out.println(random.nextInt(10));
		
		System.out.println("星期一\t\t星期二");
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
