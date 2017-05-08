package com.renke.lesson.tools;

import java.util.ArrayList;
import java.util.List;

public class ListTool {
	public static void main(String[] args) {
		List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		list.add(3L);
		list.add(4L);
		list.add(5L);
		list.add(6L);
		

		List<Long> list2 = new ArrayList<>();
//		list2.add(4L);
//		list2.add(5L);
		list2.add(6L);
		list2.add(7L);
		list2.add(8L);
		list2.add(9L);
		//建议list+map组合使用
		System.out.println(list.removeAll(list2));
		
		list.forEach(System.out::println);
	}
}
