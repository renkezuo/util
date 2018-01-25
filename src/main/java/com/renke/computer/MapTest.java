package com.renke.computer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.collections4.map.LinkedMap;

public class MapTest {
	// TreeMap/HashMap/LinkedHashMap
	
	public static void initMap(Map<String, Integer> mapping){
		mapping.put("1111111", 1);
		mapping.put("4444444", 4);
		mapping.put("3333333", 3);
		mapping.put("5555555", 5);
		mapping.put("2222222", 2);
	}
	
	public static void showMap(Map<String, Integer> mapping){
		Iterator<Entry<String,Integer>> it = mapping.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,Integer> entry = it.next();
			System.out.println("key:"+entry.getKey()+";value:"+entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		Map<String, Integer> hashMap = new HashMap<>();
		Map<String, Integer> treeMap = new TreeMap<>();
		Map<String, Integer> linkedMap = new LinkedMap<>();
		initMap(hashMap);
		initMap(treeMap);
		initMap(linkedMap);
		System.out.println("HashMap----------");
		showMap(hashMap);
		System.out.println("TreeMap----------");
		showMap(treeMap);
		System.out.println("LinkedMap----------");
		showMap(linkedMap);
	}
}
