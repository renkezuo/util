package com.renke.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 定义一个Object数组
 * 
 * @param <K>
 * @param <V>
 * @author renke.zuo@foxmail.com
 * @time 2016-08-12 14:05:36
 */
public class ZMap{
	private Object[] objs;
	private int size = 0;
	private int increment;
	private int initcapacity;
	public ZMap(int initcapacity,int increment){
		this.initcapacity = initcapacity;
		this.increment = increment;
		objs = new Object[initcapacity];
	}
	
	public ZMap(){
		this(16, 16);
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size < 1;
	}
	
	public Object get(int key) {
		return objs[key];
	}
	
	public Object put(int key, Object value) {
		// TODO Auto-generated method stub
		if(initcapacity <= key){
			extend();
		}
		objs[key] = value;
		size = size + 1;
		return value;
	}

	
	public Object remove(int key) {
		return objs[key];
	}

	
	public void clear() {
		size = 0;
	}

	private void extend(){
		Object[] tmp = new Object[initcapacity+increment];
		System.arraycopy(objs, 0, tmp, 0, initcapacity);
		objs = tmp;
		initcapacity = initcapacity + increment;
	}
	
}
