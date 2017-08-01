package com.renke.jdk8;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.renke.jdk8.model.Item;
public class CycleTest {
	public static void main(String[] args) {
		filterList();
		modifyItem();
		reduceList();
	}
	
	public static void filterList(){
		List<String> list = Arrays.asList("Java","Php","C++","C");
		List<String> java = list.stream().filter((item) -> item.equals("Java")).collect(Collectors.toList());
		java.forEach(System.out::println);
	}
	
	public static void modifyItem(){
		//forEach可以操作元素
		List<Item> list = Arrays.asList(new Item(),new Item(),new Item());
		list.forEach(item -> item.setId(1));
		list.forEach(item -> System.out.println("list1--->" + item.getId()));
		//流中不可操作元素
		list = Arrays.asList(new Item(),new Item(),new Item());
		list.stream().filter(item -> {item.setId(1);return false;});
		list.forEach(item -> System.out.println("list2--->" + item.getId()));
	}
	
	public static void reduceList(){
		List<Integer> list = Arrays.asList(100,200,300,400,500);
		System.out.println(list.stream().reduce((sum,cost) -> sum + cost).get());;
	}
}
