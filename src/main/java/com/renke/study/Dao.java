package com.renke.study;

import java.util.HashMap;
import java.util.Map;

public class Dao{
	public String getNullException(){
		return null;
	}
	
	/**
	 * 去重复
	 * @param strs1
	 * @return
	 */
	public String[][] reArray(String[][] strs1){
		String[][] strs2 = new String[strs1.length][];
		int reStrsIndex = 0;
		for(int i=0;i<strs1.length;i++){
			String[] ss1 = strs1[i];
			for(String[] ss2 : strs2){
				for(String s1 : ss1){
					
				}
			}
			strs2[reStrsIndex] = ss1; 
			reStrsIndex++;
		}
		return strs2;
	}
	
	public String getException(){
		return "hello";
	}
	
	
	public void throwNullException(){
		getNullException().substring(0,1);
	}
	
	public void tryCatchException () throws StudyException{
		try{
			throwNullException();
		}catch(Exception e){
			System.out.println("tryCatchException");
			throw new NullPointerException();
		}
	}
	
	public void fun1() {
		boolean bl = false;
		try{
			bl = 1/0>0;
		}catch(Exception e){
			System.out.println(bl);
		}
		System.out.println(bl);
	}

	public void fun2(){
		System.out.println(2);
	}
	
	public void changeFlag(boolean flag){
		flag = true;
	}
	public boolean getFlag(){
		boolean flag = false;
		changeFlag(flag);
		return flag;
	}
	public static void main(String[] args) {
		Dao dao = new Dao();
		System.out.println(dao.getFlag());
		try{
//			dao.getNullException().substring(0,8);
//			int i = 10/0;
			dao.tryCatchException();
		}catch(ArithmeticException e){
			System.out.println("ArithmeticException");
		}catch(StudyException e){
			System.out.println("NullPointerException");
			throw new NullPointerException();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception");
		}
		Map map = new HashMap();
		map.put(null, "123");
		System.out.println(map.get(null));
//		String b = null;
//		dao.fun1();
//		Object obj = true;
//		System.out.println(obj.toString());
//		String a = null;
//		if(a!=null&&"".equals(a.trim())){
//			System.out.println(123);
//		}
//		File file = new File("E:/test2");
//		File file2 = new File(file+"/test");
//		System.out.println(file2.isDirectory());
//		System.out.println("E:\\test"+"E:\\test".length());
//		System.out.println("E:/test"+"E:/test".length());
//		System.out.println("E:\test"+"E:\test".length());
//		System.out.println("xxxx\r\n yyyy");
//		System.out.println("123123123123".replaceFirst("2", ""));
		String string = "《你好么？？？？？《sdfsdf》》123123《12321lkjlsdkafjlakfjd》";
		System.out.println(string.substring(string.indexOf("《")+1));
		int begin_index = string.indexOf("《");
		string = string.substring(begin_index+1);
		
		System.out.println(string.indexOf("《"));
//		for(String str:strs){
//			Map map = new HashMap();
//			if(str.indexOf("》")>-1){
//				if(str.indexOf("》")==str.lastIndexOf("》")){
//					map.put("fileName", str.substring(0,str.indexOf("》")));
//				}else{
//					
//				}
//			}
//		}
		
		System.out.println("《你好么？？？？？《sdfsdf》》123123《12321lkjlsdkafjlakfjd》");
//				.replaceAll("《", "《<a href='#' onclick='downFile(this);'>").replaceAll("》", "</a>》"));
		System.out.println("99900020110065029990029990005_99900300001_0001_999".length());
//		System.out.println(",123,12322,".indexOf(",123,"));
	}
}
