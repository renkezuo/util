package com.renke.mq.rabbit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.Channel;
import com.renke.mq.Sender;

public class RabbitProducer implements Sender{
	Channel channel = null;
	String exchange = "";
	String routingKey = "";
	
	public RabbitProducer(String exchange, String routingKey){
		channel = RabbitChannel.getInstance(0);
		this.exchange = exchange;
		this.routingKey = routingKey;
	}

	@Override
	public void send(String message, String... properties){
		// 发布消息，exchange/queueName/参数/消息字节
		try {
			channel.basicPublish(exchange, routingKey, null, message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
//		Connection conn = channel.getConnection();
		try{
			if(channel != null) {
				channel.close();
			}
//			if(conn != null){
//				conn.close();
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}