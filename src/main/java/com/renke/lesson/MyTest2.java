package com.renke.lesson;

import java.util.ArrayList;
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
import com.renke.lesson.service.Monitor;
import com.renke.lesson.tools.InitData;

public class MyTest2 {

	public static void main(String[] args) {
		//[��Ŀ][��ʦ]
		//��ȷ��ʦ�󶨰༶���󶨿�Ŀ
		//��Ŀ��Ӧ��ʦ�б�
		Map<Long, Teacher2[]> courseTeachers = InitData.teachers2();
		//�༶��Ŀ�б�
		Course2[] courses = InitData.courses2(courseTeachers);
		//�༶�б�
		Klass2[] klasses = InitData.klasses2(courses);
		
//		monitorTask(courseTeachers, courses, klasses);
		
		for(Klass2 klass : klasses){
			//��ϣ�
//			Course2[] klassCourses = klass.getCourses();
			Day[] days = klass.getDays();
			for(int index = 0 ;index < days.length; index ++){
				Day day = days[index];
				LessonUnit[] lessons = day.getLessonUnit();
				for(LessonUnit lesson:lessons){
					//��ʱ���ÿ�Ŀ
//					lesson.setCourse(klassCourses[0]);
					
					
				}
			}
//				klass.getCourseCombi().add(possibleCourse(klass.getTeachers()[i].getMaxLesson(),klass.getDayMaxCourseLessons()[i],klass.getCourseMaxDays()[i]));
			
			
			//ȡ�༶��ʱ��-periods
			//һ���࣬һ����ʱ
			//ע���̰�ƽ��Ϳ�Ŀ��Ҫ�̶��ǳ�ͻ��[���Ƚ̰�ƽ��]
			
			//�Ӱ༶��ʼ���Ǵ���ʦ��ʼ��
			
		}
		
		//����ƣ���֪��Ŀ������֪�༶��ʱ��
		//��֪/δ֪��ʦ����ʱ�����༶��ʦ�Զ��󶨹���
//		int[][] courses = new int[11][2];
//		int[] choose = new int[courses.length];
//		for (int i = 0; i < courses.length; i++) {
//			courses[i][0] = 1;
//			if (i < 3) {
//				courses[i][1] = 1;
//			}
//		}
//		// ��ٿ�����
//		possibleCell(0,courses,choose);
//		List<int[]> list = possibleCourse(6,3,5);
//		for(int[] vars : list){
//			System.out.println(arrayToString(vars));
//		}
		// System.out.println(count);
	}
	
	
	public static void monitorTask(Map<Long, TeacherBak[]> courseTeachers,Course[] courses ,Klass3[] klasses){
		Monitor monitor = new Monitor();
		monitor.listener(courseTeachers,courses,klasses);
		Thread thread = new Thread(monitor,"monitor");
		thread.start();
	}
	
	/***
	 * ��¼�α�
	 * @param x
	 * @param courses
	 * @param choose
	 */
	public static void possibleCell(int x, int[][] courses, int[] choose) {
		if (x >= courses.length) {
			int[] probable = new int[choose.length];
			System.arraycopy(choose, 0, probable, 0, choose.length);
			System.out.println("doSth.....");
			for (int i = 0; i < probable.length; i++) {
				 System.out.println(probable[i]);
			}
		} else {
			for (int i = 0; i < courses[x].length; i++) {
				if (courses[x][i] != 0) {
					choose[x] = i;
					possibleCell(x + 1, courses, choose);
				}
			}
		}
	}

	/***
	 * �����Ŀÿ�ܿ�ʱ�ֲ�
	 * �༶ÿ����ͬ��Ŀ���4��ʱ
	 * һ��ѧУ��ʱʱ������40-50����
	 * �������ʱ��������7-10��
	 * �������4��ʱ������
	 * @param weekMaxLesson	��Ŀ������ʱ��
	 * @param dayMaxLesson	��Ŀ��������ʱ��
	 * @param maxDay		�������
	 */
	public static List<int[]> possibleCourse(int weekMaxLesson,int dayMaxLesson,int maxDay) {
		List<int[]> list = new ArrayList<>();
		// 1*x + 2*y + 3*z + 4*n = weekMaxLesson
		// x+y+z+n <= maxDay
		switch (dayMaxLesson) {
		case 1:
			for (int x = 0; x < maxDay; x++) {
				if (1 * x == weekMaxLesson && x <= maxDay) {
					list.add(new int[]{x,0,0,0});
				}
			}
			break;
		case 2:
			for (int x = 0; x < maxDay; x++) {
				for (int y = 0; y < maxDay; y++) {
					if(x+y>maxDay) break;
					if (1 * x + 2 * y == weekMaxLesson && x + y <= maxDay) {
						list.add(new int[]{x,y,0,0});
					}
				}
			}
			break;
		case 3:
			for (int x = 0; x < maxDay; x++) {
				for (int y = 0; y < maxDay; y++) {
					if(x+y>maxDay) break;
					for (int z = 0; z < maxDay; z++) {
						if(x+y+z>maxDay) break;
						if (1 * x + 2 * y + 3 * z  == weekMaxLesson && x + y + z<= maxDay) {
							list.add(new int[]{x,y,z,0});
						}
					}
				}
			}
			break;
		default:
			for (int x = 0; x < maxDay; x++) {
				for (int y = 0; y < maxDay; y++) {
					if(x+y>maxDay) break;
					for (int z = 0; z < maxDay; z++) {
						if(x+y+z>maxDay) break;
						for (int n = 0; n < maxDay; n++) {
							if(x+y+z+n>maxDay) break;
							if (1 * x + 2 * y + 3 * z + 4 * n == weekMaxLesson && x + y + z + n <= maxDay) {
								list.add(new int[]{x,y,z,n});
							}
						}
					}
				}
			}
			break;
		}
		return list;
	}
	
	public static String arrayToString(int[] vars){
		String str = "";
		for(int i=0;i<vars.length;i++){
			str+= (i+1)+"*"+vars[i]+"+";
		}
		return str.substring(0, str.length()-1);
	}
}
