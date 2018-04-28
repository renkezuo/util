package com.renke.mq.rabbit.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.renke.mq.rabbit.MsgReceiver;
import com.renke.mq.rabbit.RabbitMQConnection;

public class Consumer5 {
	public static void main(String[] args) {
		Connection conn = RabbitMQConnection.getNewConnection();
		try {
			Channel channel = conn.createChannel();
			Consumer consumer = new MsgReceiver(channel);
			channel.basicConsume("com.renke.d.queue.fanout", true, consumer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
