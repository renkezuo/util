package com.renke.study;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringHelper {
	/**
	 * 截取str中		以begin开头	end结束的	字符串
	 * 返回字符串列表
	 * @param str			原字符串
	 * @param begin			开始字符
	 * @param end			结束字符
	 * @return List<String>	返回字符串列表
	 */
	public List<String> getStringListInDoubleChar(String str,char begin,char end){
		List<String> list = new ArrayList();
		if(str==null){
			return list;
		}
		char[] chars = str.toCharArray();
		int begin_index = 0;
		int end_index = 0;
		int begin_cnt = 0;
		int end_cnt = 0;
		for(int i=0;i<chars.length;i++){
			if(begin_cnt == end_cnt && begin_cnt!=0){
				list.add(str.substring(begin_index,end_index));
				begin_index = 0;
				begin_cnt = 0;
				end_cnt = 0;
			}
			if(chars[i]==begin){
				if(begin_index==0){
					begin_index = i+1;
				}
				begin_cnt++;
			}
			if(chars[i]==end){
				end_index = i;
				end_cnt++;
			}
		}
		return list;
	}
	
	public String notEmpty(Object value){
		if(value==null){
			value = "";
		}
		return String.valueOf(value);
	}
	
	/***
	 * 取bytes大小的字符串
	 * 中文缩进
	 * eg:subStringB("中国",3);return 中;
	 * @param str
	 * @param bytes
	 * @return
	 */
	
	public String subStringByByte(String str,int bytes){
		if(str.getBytes().length<bytes) return str;
		String reStr = str.replaceAll("[\\x00-xff]", "a")
					.replaceAll("[^\\x00-xff]", "xx")
					.substring(0,bytes).replaceAll("xx", "b")
					.replaceAll("x","");
		return str.substring(0,reStr.length());
	}
	public static void main(String[] args) {
//		String str = "/123/123/123123/123.rar";
//		String[] strs = str.split("/");
//		for(int i=0;i<strs.length;i++){
//			System.out.println(strs[i]);
//		}
//		System.out.println(strs.length);
//		System.out.println(str.substring(0,str.lastIndexOf("/")==-1?0:str.lastIndexOf("/")));
//		System.out.println(str.substring(str.lastIndexOf("/")));
		
//		long a = System.currentTimeMillis();
//		String str = "OOOOO《AAAAA《BBBBB《CCCCC》DD《D》DD《EEEEE》FFFFF》GGGGG》123123《1232《CCCC》afjlakfjd》FFFF";
////		String str = "\r\n\t中国人-一个加几个？？??    ";
//		StringHelper sh = new StringHelper();
//		List<String> list = sh.getStringListInDoubleChar(str,'《', '》');
//		for(String fileName:list){
//			System.out.println(fileName);
//		}
//		long b = System.currentTimeMillis();
//		System.out.println(b-a);
//		System.out.println("99900020110065".substring(0,6));
//		System.out.println("20120619".substring(0,4));
//		System.out.println("20120619".substring(4,6));
//		System.out.println("20120619".substring(6,8));
//		StringBuilder sb = new StringBuilder(16);
//		sb.append("1234567890123456789");
//		System.out.println(sb.toString());
//		String str2 = "jdbc:db2://158.222.31.134:50000/acms";
//		System.out.println(str2);
//		for(int i=0 ; i<str2.length();i++){
//			int j = str2.charAt(i);
//			System.out.println(j);
//		}
		
//		String sql = "insert into t_bb_plan_pro_2012 "
//+ " select ':1',  B_BRANCH_NO,  BUSTYPE_NO,  SUB_NO,  PRO_NO,  PRO_CONTENT,  PRO_WRONG_COUNT,  PRO_CHECK_COUNT,  PRO_CHECK_RATE,  PRO_SCORE, ':2' "
//+ " from  t_bb_plan_pro_2012 "
//+ "where plan_no=':3';";
//		for(int i = 700 ;i<900 ;i++){
////			if(i<10){
////				System.out.println(sql.replace(":1", "8960002012000"+i).replace(":2", "20120812").replace(":3", "8960002012000"+(i%2+1)));
////			}else{
//				System.out.println(sql.replace(":1", "89600020120"+i).replace(":2", "20120812").replace(":3", "89600020120001"));
////			}
//		}
		System.out.println("99900020120001".substring(6,10));
		System.out.println("99900020120001".substring(0,14));
		
	}
}
