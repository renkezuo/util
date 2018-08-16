package com.renke.concurrent;

public class Array {
	public String name;
	public byte[] bytes;
	public Array(String name ,int buf){
		this.name = name;
		bytes = new byte[buf];
	}
}
