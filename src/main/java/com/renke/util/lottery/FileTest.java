package com.renke.util.lottery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileTest {
	public static void main(String[] args) throws IOException, InterruptedException {
//		OutputStream os = new FileOutputStream(new File("C:/333.txt"));
//		Thread.sleep(5000);
		try{
			Map<String,String> map = new TreeMap<>();
			setMap(map);
			printMap(map);
			Map<String,String> map1 = new HashMap<>();
			setMap(map1);
			printMap(map1);
			
			Map<String,String> map2 = new LinkedHashMap<>();
			setMap(map2);
			printMap(map2);
			List<String> lessonList = new ArrayList<>();
			lessonList.add("1");
			lessonList.add("2");
			lessonList.add("3");
			lessonList.add("4");
			lessonList.add("5");
			
			int number = lessonList.size()/3;
			for(int i=0;i<=number;i++){
				List<String> subList = null;
				if(i==number){
					subList = lessonList.subList(i*3, lessonList.size());
				}else{
					subList = lessonList.subList(i*3, (i+1)*3);
				}
				System.out.println("subList.size:"+subList.get(0));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		os.write("dfsasdfsdfsdafsadfsaddfsdfasd".getBytes());
//		Thread.sleep(1000000);
//		System.out.println();
		System.out.println();
		;
		
		System.out.println("123123".indexOf("."));
	}
	
	public static void setMap(Map<String,String> map ){
		map.put("a", "1");
		map.put("b", "2");
		map.put("bb", "3");
		map.put("vcv", "7");
		map.put("v", "6");
		map.put("d", "5");
		map.put("c", "4");
	}
	
	public static void printMap(Map<String,String> map ){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println("----"+map.get(key));
		}
		
		
	}
}
