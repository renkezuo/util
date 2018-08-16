package com.renke.mq.rabbit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class MyPublisher {
	public static void main(String[] args) {
		Connection conn = RabbitMQConnection.getNewConnection();
		try {
			Channel channel = conn.createChannel();
			// 当routeKey为空时
			for(int i=0;i<10;i++){
				String msg = "xxx"+i;
				channel.basicPublish("com.renke.exchange", "", null, msg.getBytes("UTF-8"));
//				channel.basicPublish("com.renke.e.topic", "com.renke.r.topic1", null, msg.getBytes("UTF-8"));
//				msg = "xxx"+i;
//				channel.basicPublish("com.renke.e.topic", "com.renke.r.topic1", null, msg.getBytes("UTF-8"));
//				channel.basicPublish("com.renke.e.fanout", "", null, msg.getBytes("UTF-8"));
//				channel.basicPublish("com.renke.e.direct", "", null, msg.getBytes("UTF-8"));
			}
			// routeKey非空
//			for (int i = 0; i < 10; i++) {
//				String msg = "message : " + (i + 1);
//				int k = i/2 + 1;
//				String fRouteKey = "com.renke.r.fanout" + k;
//				String tRouteKey = "com.renke.r.topic" + k;
//				String dRouteKey = "com.renke.r.direct" + k;
//				channel.basicPublish("com.renke.e.topic", tRouteKey, null, msg.getBytes("UTF-8"));
//				channel.basicPublish("com.renke.e.fanout", fRouteKey, null, msg.getBytes("UTF-8"));
//				channel.basicPublish("com.renke.e.direct", dRouteKey, null, msg.getBytes("UTF-8"));
//			}
			// header
//			for (int i = 0; i < 10; i++) {
//				String msg = "message : " + (i + 1);
//				Builder properties = new BasicProperties.Builder();
//				Map<String, Object> headers = new HashMap<>();
//				headers.put("key1", "123");
//				headers.put("key2", "456");
//				properties.headers(headers);
//				channel.basicPublish("com.renke.e.headers", "", properties.build(), msg.getBytes("UTF-8"));
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
