package com.renke.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;

public class MyConsumer {
	public static void main(String[] args) {
		Connection conn = RabbitMQConnection.getNewConnection();
		try {
			Channel channel = conn.createChannel();
			Consumer consumer = new MsgReceiver(channel);
			channel.basicConsume("com.renke.d.queue.fanout1", true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
