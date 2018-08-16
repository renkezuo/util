package com.renke.mq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.renke.mq.rabbit.RabbitMQ;
import com.renke.mq.rabbit.RabbitMQConnection;
import com.renke.mq.rabbit.consumer.CourseSingleRemote;

public class TestSender {
	
	public static void main(String[] args) throws IOException {
		String[] properties = new String[]{"com.renke.fanout",""};
//		Connection conn = RabbitMQConnection.getConnection("192.168.20.75", 5672, "leke", "leke@@@");
//		Channel channel = conn.createChannel();
//		CourseSingleRemote csr = new CourseSingleRemote();
		
//		channel.basicPublish("e.beike.coursesingleended", "q.beike.coursesingleended", null, );
		Sender sender = RabbitMQ.getSender(properties);
		
		sender.send(Thread.currentThread().getName() + " msg : ", new String[]{"0"});
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
