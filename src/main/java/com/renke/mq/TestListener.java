package com.renke.mq;

import com.renke.mq.rabbit.RabbitMQ;

public class TestListener {
	public static void main(String[] args) {
//		Executor exe = Executors.newCachedThreadPool();
//		for(int i=0;i<20;i++){
//			exe.execute(new TestListener().new ListenerMore());
//		}
		String[] properties = new String[]{"com.renke.test","true"};
		Receiver receiver = RabbitMQ.getReceiver(properties);
		receiver.listener(properties);
		
		
	}
	
	class ListenerMore implements Runnable {
		String[] properties = new String[]{"com.renke.test","true"};
		@Override
		public void run() {
			Receiver receiver = RabbitMQ.getReceiver(properties);
			receiver.listener(properties);
		}
	}
	
}
