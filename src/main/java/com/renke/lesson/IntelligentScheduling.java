package com.renke.lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renke.lesson.pojo.Area;
import com.renke.lesson.pojo.Teacher;

public class IntelligentScheduling {
	public static List<Area> areas = new ArrayList<>();
	public static Map<Long,List<Teacher>> teachers = new HashMap<>();
	static{
		init();
	}
	
	public static void init(){
		//׼�������б���ʦ�б��ſ�������ÿ���ʱ������������
		for(int i=0;i<12;i++){
			Area area = new Area();
			areas.add(area);
			area.setAreaId(i+1L);
			area.setAreaName(20 + i + 1 + "");
			area.setBuilding("1��¥");
		}
		//��ʦ�б�����12���༶������Ŀ��Ҫ4����ʦ��ÿλ��ʦ���3����Ŀ
		for(int i=0;i<12;i++){
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		
	}
}
