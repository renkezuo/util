package com.renke.reference;

public class Million {
	public byte[] m;
	public String name ;
	public Million(String name){
		this.name = name;
		m = new byte[1024 * 1024];
	}
	public Million(String name,int i){
		this.name = name;
		m = new byte[1024 * 1024 * i];
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println(Thread.currentThread().getName()+"---"+this + " is gc");
	}
}
