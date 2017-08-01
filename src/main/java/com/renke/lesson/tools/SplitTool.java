package com.renke.lesson.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplitTool {
	public static void main(String[] args) {
		printClass(assemble());
//		MyTestFunInterface<String,String> mt = (msg)->{
//			System.out.println(msg);
//			return "hello";
//		};
//		String index = "12355";
//		System.out.println(mt.print(index));
	}
	
	public static Map<String,String[][]> assemble(){
		//取得，学校gradeName，年级
		//select * from ls_choice_exam_task where task_id=''
		
		//select * from ls_limit_condition;
		//splitCnt , level1 ,level2 ,level3,level4,subName
		
		//选课学生数 choiceCnt
		// select count(*) from ls_choice_exam_task_student where choiceSubs  like '%,x,%' and taskId=''
		
		//年级学生IDs list
		// select distinct userId from ls_klass a join ls_klass_student b on a.classId=b.classId
		//  where a.gradeId='12' and a.type='1' and a.schoolId = ''
		
		//学生总数allCnt
		//allCnt = list.size();
		
		//学考学生数baseCnt
		//baseCnt = allCnt - choiceCnt;
		
		//查询学生信息，导出学生基础信息
		//userBaseContext.findUserBaseByUserId(list.toArray(new Long[list.size()]));
		int baseCnt = 208;
		int splitCnt = 50;
		int level1 = 90;
		int level2 = 90;
		int level3 = 90;
		int level4 = 32;
		String gradeName = "高一";
		String subName = "物理";
		Map<String,String[][]> map = new HashMap<>();
		map.put("level1",splitClass(level1,splitCnt,gradeName,subName,"选考"));
		map.put("level2",splitClass(level2,splitCnt,gradeName,subName,"选考"));
		map.put("level3",splitClass(level3,splitCnt,gradeName,subName,"选考"));
		map.put("level4",splitClass(level4,splitCnt,gradeName,subName,"选考"));
		map.put("level5",splitClass(baseCnt,splitCnt,gradeName,subName,"学考"));
		return map;
	}
	
	public static String[][] splitClass(int levelCnt,int splitCnt,String gradeName,String subName,String pre_fix){
		if(levelCnt > 0 ){
			int last = levelCnt % splitCnt > 0 ? 1 : 0;
			String[][] classes = new String[levelCnt/splitCnt + last][2];
			for(int i = 0;i<classes.length;i++){
				classes[i][0] = pre_fix + gradeName + subName + (i+1) +"班";
				int surplus = levelCnt - splitCnt * i ;
				classes[i][1] = ""+(surplus > splitCnt ? splitCnt : surplus);
			}
			return classes;
		}
		return null;
	}
	
	public static void printClass(Map<String,String[][]> map){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String[][] classes = map.get(key);
			for(int i=0;i<classes.length;i++){
				System.out.println(key+"{"+"班级名称:"+classes[i][0]+",学生人数:"+classes[i][1]+"}");
			}
		}
	}
}
