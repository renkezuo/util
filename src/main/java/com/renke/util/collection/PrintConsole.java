package com.renke.util.collection;
import java.util.Iterator;
import java.util.Map;

public class PrintConsole {
	public static String mapToString(Map<String,?> map){
		StringBuilder result = new StringBuilder();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			result.append("\r\nkey:").append(key).append(";value:").append(map.get(key));
		}
		return result.substring(2).toString();
	}
	
}
