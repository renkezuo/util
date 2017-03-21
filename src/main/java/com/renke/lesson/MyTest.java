package com.renke.lesson;

import java.util.concurrent.atomic.AtomicInteger;

import com.renke.lesson.pojo.Grade;

public class MyTest {
	static AtomicInteger ai = new AtomicInteger(0);
	
	//限定老师每天极限课时数，
	
	public static void main(String[] args) {
		Grade grade = new Grade();
		grade.setWorkDays(7);
		grade.setDayHours(9);
		grade.setCourses(9);
		for(int i=0;i<grade.getWorkDays();i++){
			//组合老师
			//循环课时，分配课程，该级别课程有多少老师，决定可以上课的并行量 
			for(int k=0 ;k<grade.getDayHours();k++){
				//获取课程列表，循环，填入课程，减少课时
			}
			
			
		}
	}
	
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
	//有可能导致无法生成课表
	
	
}
