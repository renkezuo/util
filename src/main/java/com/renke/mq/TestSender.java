package com.renke.mq;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.renke.mq.rabbit.RabbitMQ;

public class TestSender {
	
	public static void main(String[] args) {
		String[] properties = new String[]{"","com.renke.test"};
		
		Executor exe = Executors.newCachedThreadPool();
		for(int i=0;i<1000;i++){
			Sender sender = RabbitMQ.getSender(properties);
			exe.execute(new TestSender().new SendMore(sender));
		}
	}

	class SendMore implements Runnable {
		Sender sender;
		
		public SendMore(Sender sender){
			this.sender = sender;
		}
		
		@Override
		public void run() {
			for(int i=0;i<10000;i++){
				try {
					Thread.sleep(10);
					sender.send(Thread.currentThread().getName() + " msg : " + i, new String[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
