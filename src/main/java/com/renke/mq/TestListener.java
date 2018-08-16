package com.renke.mq;

import com.renke.mq.rabbit.RabbitMQ;

public class TestListener {
	public static void main(String[] args) {
//		Executor exe = Executors.newCachedThreadPool();
//		for(int i=0;i<20;i++){
//			exe.execute(new TestListener().new ListenerMore());
//		}
		String[] properties = new String[]{"com.renke.fanout1","true"};
		Receiver receiver = RabbitMQ.getReceiver(properties);
		receiver.listener(properties);
		
		
		String[] properties2 = new String[]{"com.renke.fanout2","true"};
		Receiver receiver2 = RabbitMQ.getReceiver(properties2);
		receiver2.listener(properties2);
		
		
		String[] properties3 = new String[]{"com.renke.fanout3","true"};
		Receiver receiver3 = RabbitMQ.getReceiver(properties3);
		receiver3.listener(properties3);
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
