package com.renke.http;

import java.util.ArrayList;

public class SyncArrayList<T> extends ArrayList<T>{

	/**
	 * serialVersionUID
	 * @author renke.zuo@foxmail.com
	 * @time 2016-09-12 17:03:34 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public synchronized T remove(int index) {
		// TODO Auto-generated method stub
		return super.remove(index);
	}
	@Override
	public synchronized boolean add(T e) {
		// TODO Auto-generated method stub
		return super.add(e);
	}
}
