package com.renke.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.renke.mq.Receiver;

public class RabbitConsumer implements Receiver{
	
	Channel channel = null;
	String routingKey = "";
	
	public RabbitConsumer(String routingKey){
		channel = RabbitChannel.getInstance();
		this.routingKey = routingKey;
	}

	@Override
	public void listener(String... properties) {
		Consumer consumer = new MsgReceiver(channel);
		try {
			boolean autoOk = true;
			if(properties != null){
				autoOk = Boolean.parseBoolean(properties[1]);
			}
			channel.basicConsume(routingKey, autoOk, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		Connection conn = channel.getConnection();
		try{
			if(channel != null) {
				channel.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}