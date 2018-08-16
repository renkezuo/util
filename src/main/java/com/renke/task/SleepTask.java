package com.renke.task;

public class SleepTask extends BaseTask{
	
	public Long sleepTime = 1000L;
	
	public SleepTask(long sleepTime){
		this.sleepTime = sleepTime;
	}

	@Override
	public void running() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
