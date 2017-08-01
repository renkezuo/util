package com.renke.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TraverseCollection {
	private static Logger logger = LoggerFactory.getLogger(TraverseCollection.class); 
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
				sb.append("\r\nkey:").append(obj).append(",value:").append(map.get(obj));
			}
		}
		return sb.toString().substring(2);
	}
	
	public static String arrayToString(Object[] objs){
		if(objs == null) return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< objs.length ; i = i+1){
			sb.append(";").append(objs[i]);
		}
		return sb.toString().substring(1);
	}
	
}
