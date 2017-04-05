package com.renke.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCls {
	private String[] w;
	static Reflect rec = new Reflect();
	public static void print2(){
		print3();
		System.out.println("print 2 :"+rec.getLine());
	}
	
	public static void print3(){
		System.out.println("print 3 :"+rec.getLine());
	}
	
	public static void print4(){
		System.out.println("print 4 : " +rec.getLine());
	}
	
	public static void main(String[] args) {
		/*String fileNames = "¡·";
		String branch_no = "";
		List<Map> list = new ArrayList();
		Map map = new HashMap();
		list.add(map);
		map.put("fileName", "file1");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file3");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0001");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file1");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file1");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file3");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file1");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file4");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file5");
		map.put("branch_no", "0002");
		map = new HashMap();
		list.add(map);
		map.put("fileName", "file2");
		map.put("branch_no", "0002");
		List<Map> reList = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map file = list.get(i);
			String fileName = file.get("fileName").toString().trim();
			String branch = file.get("branch_no").toString().trim();
			if(i==0){
				fileNames += fileName+"¡·";
				branch_no = branch;
				reList.add(file);
			}else{
				if(branch_no.equals(branch)){
					if(fileNames.indexOf("¡·"+fileName+"¡·")<0){
						fileNames += fileName+"¡·";
						reList.add(file);
					}
				}else{
					branch_no = branch;
					fileNames = "¡·" + fileName+"¡·";
					reList.add(file);
				}
			}
			file.put("fileName", "text");
		}
		for(int i=0;i<reList.size();i++){
			Map file = reList.get(i);
			System.out.println(file.get("fileName"));
			System.out.println(file.get("branch_no"));
		}
		System.out.println("123,123,12323".indexOf(",123,"));
		System.out.println("99900020110065029990029990005_99900300001_0005_9990006.pdf".substring(42));
//		try {
//			Object obj = Class.forName("study.Dao").newInstance();
//			Method method[] = obj.getClass().getMethods();
//			for(int i =0;i<method.length;i++){
//				System.out.println(method[i].getName());
//			}
//			System.out.println("99900020110004019990019990005".substring(6,10));
//			System.out.println("99900020110004019990019990005".substring(14,16));
//			System.out.println("99900020110004019990019990005".substring(16,22));
//			System.out.println("123213.txt".substring("123213.txt".lastIndexOf(".")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}*/
		print2();
	}
}
