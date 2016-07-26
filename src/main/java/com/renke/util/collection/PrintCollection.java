package com.renke.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;


public class PrintCollection {
	private static Logger logger = Logger.getLogger(PrintCollection.class); 
	public static void printCollection(Collection<?> coll){
		Iterator<?> it = coll.iterator();
		while(it.hasNext()){
			Object obj = it.next();
			if(obj!=null){
				logger.info(obj.toString());
			}
		}
	}
	
	public static String mapToString(Map<String,?> map){
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String obj = it.next();
			if(obj!=null){
				sb.append("\r\nkey : ").append(obj).append(" , value : ").append(map.get(obj));
			}
		}
		return sb.toString().substring(2);
	}
}
