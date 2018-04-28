package com.renke.mq;

import com.renke.mq.rabbit.RabbitMQ;

public class TestSender {
	
	public static void main(String[] args) {
		String[] properties = new String[]{"com.renke.fonout",""};
		Sender sender = RabbitMQ.getSender(properties);
		sender.send(Thread.currentThread().getName() + " msg : ", new String[0]);
		sender.close();
//		Executor exe = Executors.newCachedThreadPool();
//		for(int i=0;i<1;i++){
//			Sender sender = RabbitMQ.getSender(properties);
//			exe.execute(new TestSender().new SendMore(sender, 1));
//		}
	}

	class SendMore implements Runnable {
		Sender sender;
		int size;
		
		public SendMore(Sender sender, int size){
			this.sender = sender;
			this.size = size;
		}
		
		@Override
		public void run() {
			for(int i=0;i<size;i++){
				try {
					Thread.sleep(10);
					sender.send(Thread.currentThread().getName() + " msg : " + i, new String[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			sender.close();
		}
	}
}
