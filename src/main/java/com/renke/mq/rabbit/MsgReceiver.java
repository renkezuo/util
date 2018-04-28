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
		System.out.println(Thread.currentThread().getName() + " receive ===> " +new String(body));
		
//		boolean multiple = false;
//		if(i % 2 > 0){
//			multiple = true;
//		}
//		this.getChannel().basicAck(envelope.getDeliveryTag(), multiple);
//		System.out.println("return fail : " + multiple);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
