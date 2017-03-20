package com.renke.jdk8.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renke.jdk8.Filter;

public class Sample {
	// ��ӡ����֮��ĳ�Ա
	public static void filterPrint(List<String> lists, Filter filter) {
		lists.stream().filter((o) -> filter.filter(o)).forEach(System.out::println);
	}

	public static void main(String[] args) {
		List<String> lists = Arrays.asList("C", "C++", "JAVA", "python", "C#", "delphi", "JAVAscript","JAVA");
		filterPrint(lists, (str) -> str.equals("C"));
		lists.sort(StringCompare :: compare);
		filterPrint(lists, (str) -> true);
		//���� map��filterһ���壬Ȼ��forEach����map��filter����list.length��
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("hello", "world");
		map.put("nihao", "shijie");
		map.put("123", "456");
		map.put("110", "119");
		
		map.forEach((v,t) -> System.out.println("key:"+v+"--->value:"+t));
		
	}
}
