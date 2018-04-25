package com.renke.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class MyPublisher {
	public static void main(String[] args) {
		Connection conn = RabbitMQConnection.getNewConnection();
		try {
			Channel channel = conn.createChannel();
			String msg = "message : 1";
			channel.basicPublish("", "", null, msg.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
