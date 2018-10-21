package com.renke.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MsgReceiver extends DefaultConsumer{
	
	int i = 0;
	
	public MsgReceiver(Channel channel) {
		super(channel);
	}
	
	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		i ++;
		String msg = new String(body);
		System.out.println(Thread.currentThread().getName() + " receive ===> " +new String(body));
		if(msg.indexOf("吴兴实验中学") > -1){
			this.getChannel().basicAck(envelope.getDeliveryTag(), true);
			return ;
		}
		
		
//		boolean multiple = false;
//		if(i % 2 > 0){
//			multiple = true;
//		}
//		try{
//			String msg = new String(body);
//			Integer.parseInt(msg);
			this.getChannel().basicAck(envelope.getDeliveryTag(), false);
//		}catch(Exception e){
//			e.printStackTrace();
//			
//		}
//		System.out.println("return fail : " + multiple);
		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
