package com.renke.lesson.tools;

public class ArrayBuffer<T> {
	private int start = 0;
	private T[] ts ;
	
	public ArrayBuffer(T[] ts){
		this.ts = ts;
	}
	
	public T getOne(int index){
		if(ts == null){
			throw new ArrayIndexOutOfBoundsException();
		}
		return ts[(index + start)%ts.length];
	}
	
	public void setOne(int index, T t){
		ts[index] = t;
	}
	
	public void set(T[] ts){
		this.ts = ts;
		start = 0;
	}
	
	public void move(int offset){
		start = start + offset;
		if(start<0){
			start = ts.length - 1;
		}
	}

	public void reset(){
		start = 0;
	}
}
