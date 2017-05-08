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
		//准备场地列表，老师列表，排课天数，每天课时数，禁排数据
		for(int i=0;i<12;i++){
			Area area = new Area();
			areas.add(area);
			area.setAreaId(i+1L);
			area.setAreaName(20 + i + 1 + "");
			area.setBuilding("1号楼");
		}
		//老师列表，假设12个班级，各科目需要4名老师，每位老师最多3个科目
		for(int i=0;i<12;i++){
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		
		
	}
}
