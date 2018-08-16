package com.renke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTask implements Runnable{
	
	public void begin(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "  :  " + Thread.currentThread().getName() + " begin ...");
	}
	
	@Override
	public void run() {
		begin();
		running();
		end();
	}
	
	public void end(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "  :  " + Thread.currentThread().getName() + " end ...");
	}
	
	public abstract void running();
}
