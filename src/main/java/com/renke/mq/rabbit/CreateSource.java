package com.renke.mq.rabbit;


import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class CreateSource {
	public static void main(String[] args) {
		Connection connection = RabbitMQConnection.getNewConnection();
		try {
			deleteQueue(connection);
			
			// 第一种，无routeKey的情况
//			noRouteKey(connection);
			
			// 第二种，相同routeKey，不同exchange的情况
//			sameRouteKey(connection);
			
			// 第三种，有routeKey的情况
//			routeKey(connection);
			
			// 第四种，独占模式
//			exclusive(connection);
			
			// 第五种，非自动回复，需要consumer配合
//			routeKey(connection);
			
			// 第六种，一个queue绑定多个exchange
//			routeKey(connection);
//			queueBindMoreExchange(connection);
			
			// 第七种，headers使用
//			routeKey(connection);
//			headers(connection);
//			one(connection);
			
			fanout(connection);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void noRouteKey(Connection connection) throws Exception {
		Channel channel = connection.createChannel();
		for(int i=1;i<=5;i++){
			String fQueueName = "com.renke.q.fanout"+i;
			String tQueueName = "com.renke.q.topic"+i;
			String hQueueName = "com.renke.q.headers"+i;
			String dQueueName = "com.renke.q.direct"+i;
			channel.queueDeclare(fQueueName, false, false, false, null);
			channel.queueDeclare(tQueueName, false, false, false, null);
			channel.queueDeclare(hQueueName, false, false, false, null);
			channel.queueDeclare(dQueueName, false, false, false, null);
			channel.queueBind(fQueueName, "com.renke.e.fanout", "");
			channel.queueBind(tQueueName, "com.renke.e.topic", "");
			channel.queueBind(hQueueName, "com.renke.e.headers", "");
			channel.queueBind(dQueueName, "com.renke.e.direct", "");
		}
		channel.close();
	}
	
	public static void sameRouteKey(Connection connection) throws Exception {
		Channel channel = connection.createChannel();
		for (int i = 1; i <= 5; i++) {
			String dQueueName = "com.renke.q.direct" + i;
			String dRouteKey = "com.renke.r.direct"+i;
			channel.queueDeclare(dQueueName, false, false, false, null);
			channel.queueBind(dQueueName, "com.renke.e.direct", dRouteKey);
//			channel.queueBind(dQueueName, null, dRouteKey);
		}
		channel.close();
	}
	
	public static void routeKey(Connection connection) throws Exception {
		Channel channel = connection.createChannel();
		for (int i = 1; i <= 5; i++) {
			String fQueueName = "com.renke.q.fanout" + i;
			String tQueueName = "com.renke.q.topic" + i;
			String hQueueName = "com.renke.q.headers" + i;
			String dQueueName = "com.renke.q.direct" + i;
			
			String fRouteKey = "com.renke.r.fanout"+i;
			String tRouteKey = "com.renke.r.topic"+i;
			String hRouteKey = "com.renke.r.headers"+i;
			String dRouteKey = "com.renke.r.direct"+i;
			channel.queueDeclare(fQueueName, false, false, false, null);
			channel.queueDeclare(tQueueName, false, false, false, null);
			channel.queueDeclare(hQueueName, false, false, false, null);
			channel.queueDeclare(dQueueName, false, false, false, null);
			
			channel.queueBind(fQueueName, "com.renke.e.fanout", fRouteKey);
			channel.queueBind(tQueueName, "com.renke.e.topic", tRouteKey);
			channel.queueBind(hQueueName, "com.renke.e.headers", hRouteKey);
			channel.queueBind(dQueueName, "com.renke.e.direct", dRouteKey);
		}
		channel.close();
	}
	
	
	public static void queueBindMoreExchange(Connection connection) throws Exception {
		Channel channel = connection.createChannel();
		for(int i=1;i<=5;i++){
			String moreQueueName = "com.renke.q.more"+i;
			
			String fRouteKey = "com.renke.r.fanout"+i;
			String tRouteKey = "com.renke.r.topic"+i;
			String hRouteKey = "com.renke.r.headers"+i;
			String dRouteKey = "com.renke.r.direct"+i;
			channel.queueDeclare(moreQueueName, false, false, false, null);
			
			channel.queueBind(moreQueueName, "com.renke.e.fanout", fRouteKey);
			channel.queueBind(moreQueueName, "com.renke.e.topic", tRouteKey);
			channel.queueBind(moreQueueName, "com.renke.e.headers", hRouteKey);
			channel.queueBind(moreQueueName, "com.renke.e.direct", dRouteKey);
		}
		channel.close();
	}
	
	public static void headers(Connection connection) throws Exception {
		Channel channel = connection.createChannel();

		String oQueueName = "com.renke.q.headers.any";
		String aQueueName = "com.renke.q.headers.all";
		Map<String, Object> headers = new HashMap<>();
		headers.put("x-match", "any");
		headers.put("key1", "123");
		headers.put("key2", "456");
		channel.queueDeclare(oQueueName, false, false, false, null);
		channel.queueBind(oQueueName, "com.renke.e.headers", "", headers);
		
		headers = new HashMap<>();
		headers.put("x-match", "all");
		headers.put("key1", "123");
		headers.put("key2", "456");
		channel.queueDeclare(aQueueName, false, false, false, null);
		channel.queueBind(aQueueName, "com.renke.e.headers", "", headers);
		
		channel.close();
	}
	
	
	public static void exclusive(Connection connection) throws Exception {
		Channel channel = connection.createChannel();

		String fQueueName = "com.renke.q.fanout"+6;
		channel.queueDeclare(fQueueName, false, true, false, null);
		channel.queueBind(fQueueName, "com.renke.e.fanout", "");
		
		// The queue is deleted after 10 seconds
		Thread.sleep(10000);
		channel.close();
	}
	
	public static void one(Connection connection) throws Exception {
		Channel channel = connection.createChannel();

		String fQueueName = "com.renke.queue";
		channel.exchangeDeclare("com.renke.exchange", "fanout");
		channel.queueDeclare(fQueueName, false, false, false, null);
		channel.queueBind(fQueueName, "com.renke.exchange", "");
		
		// The queue is deleted after 10 seconds
		Thread.sleep(10000);
		channel.close();
	}
	
	public static void fanout(Connection connection) throws Exception {
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("com.renke.fanout", "fanout");
		for (int i = 1; i <= 5; i++) {
			String fQueueName = "com.renke.fanout" + i;
			channel.queueDeclare(fQueueName, true, false, false, null);
			channel.queueBind(fQueueName, "com.renke.fanout", "");
		}
		channel.close();
	}
	
	public static void deleteQueue(Connection connection) throws Exception{
		Channel channel = connection.createChannel();
		for (int i = 1; i <= 5; i++) {
//			String fQueueName = "com.renke.q.fanout" + i;
			String fQueueName = "com.renke.fanout" + i;
			String tQueueName = "com.renke.q.topic" + i;
			String hQueueName = "com.renke.q.headers" + i;
			String dQueueName = "com.renke.q.direct" + i;
			String mQueueName = "com.renke.q.more" + i;
			channel.queueDelete(fQueueName);
			channel.queueDelete(tQueueName);
			channel.queueDelete(hQueueName);
			channel.queueDelete(dQueueName);
			channel.queueDelete(mQueueName);
		}
		channel.queueDelete("com.renke.q.headers.all");
		channel.queueDelete("com.renke.q.headers.any");
		channel.close();
	}
}

