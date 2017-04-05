package com.renke.study;

public class ThreadActuator extends Thread{
	private boolean isStop;
	private String downPath;
	private String filePath;
	ThreadActuator(String downPath,String filePath){
		this.downPath = downPath;
		this.filePath = filePath;
	}
	public void run() {
		isStop = false;
		while(!isStop){
			ThreadController tc = new ThreadController();
			tc.downloadFile(downPath, filePath);
			try {
				sleep(1000);
			} catch ( InterruptedException x ){
				System.out.println(this.getName());
				Thread.currentThread().interrupt();
			}
			stopT();
		}
	}
	
	public void stopT(){
		System.out.println(this.getName());
		isStop = true;
	}
}
