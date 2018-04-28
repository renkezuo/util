package com.renke.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;

public class MyConsumer {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			final int m = i;
			Connection conn = RabbitMQConnection.getNewConnection();
			try {
				Channel channel = conn.createChannel();
				Consumer consumer = new MsgReceiver(channel);
				try {
					// topic和fanout 时需要多个监听同一个
//					channel.basicConsume("com.renke.q.fanout" + m, false, consumer);
//					channel.basicConsume("com.renke.q.fanout" + m, false, consumer);
//					channel.basicConsume("com.renke.q.fanout" + m, false, consumer);
//					channel.basicConsume("com.renke.q.fanout" + m, true, consumer);
//					channel.basicConsume("com.renke.q.more" + m, true, consumer);
//					channel.basicConsume("com.renke.q.headers" + m, true, consumer);
//					channel.basicConsume("com.renke.q.topic" + m, true, consumer);
//					channel.basicConsume("com.renke.q.direct" + m, true, consumer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// this error
				if (m == 1){
					channel.basicConsume("com.renke.q.headers.all", true, consumer);
					channel.basicConsume("com.renke.q.headers.any", true, consumer);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
