package com.renke.lesson;

import java.util.concurrent.atomic.AtomicInteger;

import com.renke.lesson.pojo.Grade;

public class MyTest {
	static AtomicInteger ai = new AtomicInteger(0);
	
	//�޶���ʦÿ�켫�޿�ʱ����
	
	public static void main(String[] args) {
		Grade grade = new Grade();
		grade.setWorkDays(7);
		grade.setDayHours(9);
		grade.setCourses(9);
		for(int i=0;i<grade.getWorkDays();i++){
			//�����ʦ
			//ѭ����ʱ������γ̣��ü���γ��ж�����ʦ�����������ϿεĲ����� 
			for(int k=0 ;k<grade.getDayHours();k++){
				//��ȡ�γ��б�ѭ��������γ̣����ٿ�ʱ
			}
			
			
		}
	}
	
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
	//�п��ܵ����޷����ɿα�
	
	
}
