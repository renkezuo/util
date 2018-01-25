package com.renke.util.thread;

public class JoinAndFork {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new JoinThread());
		Thread t2 = new Thread(new JoinThread());
		Thread t3 = new Thread(new JoinThread());
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println("the end");
	}
}

class JoinThread implements Runnable {
	@Override
	public void run() {
		int i = 1;
		while (i <= 10) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "join times : " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
