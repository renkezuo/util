package com.renke.mq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class CreateSource {
	public static void main(String[] args) {
		Connection connection = RabbitMQConnection.getNewConnection();
		try {
			Channel channel = connection.createChannel();
			channel.exchangeDeclare("com.renke.fanout", BuiltinExchangeType.FANOUT);
			channel.exchangeDeclare("com.renke.topic", BuiltinExchangeType.TOPIC);
			channel.exchangeDeclare("com.renke.headers", BuiltinExchangeType.HEADERS);
			channel.exchangeDeclare("com.renke.direct", BuiltinExchangeType.DIRECT);

			channel.exchangeDeclare("com.renke.d.fanout", BuiltinExchangeType.FANOUT, true, false, null);
			channel.exchangeDeclare("com.renke.d.topic", BuiltinExchangeType.TOPIC, true, false, null);
			channel.exchangeDeclare("com.renke.d.headers", BuiltinExchangeType.HEADERS, true, false, null);
			channel.exchangeDeclare("com.renke.d.direct", BuiltinExchangeType.DIRECT, true, false, null);
			
			channel.exchangeDeclare("com.renke.d.ad.fanout", BuiltinExchangeType.FANOUT, true, true, null);
			channel.exchangeDeclare("com.renke.d.ad.topic", BuiltinExchangeType.TOPIC, true, true, null);
			channel.exchangeDeclare("com.renke.d.ad.headers", BuiltinExchangeType.HEADERS, true, true, null);
			channel.exchangeDeclare("com.renke.d.ad.direct", BuiltinExchangeType.DIRECT, true, true, null);
			
			channel.queueDeclare("com.renke.queue.fanout", false, false, false, null);
			channel.queueDeclare("com.renke.queue.fanout1", false, false, false, null);
			channel.queueDeclare("com.renke.queue.topic", false, false, false, null);
			channel.queueDeclare("com.renke.queue.headers", false, false, false, null);
			channel.queueDeclare("com.renke.queue.direct", false, false, false, null);

			channel.queueDeclare("com.renke.d.queue.fanout", true, false, false, null);
			channel.queueDeclare("com.renke.d.queue.fanout1", true, false, false, null);
			channel.queueDeclare("com.renke.d.queue.topic", true, false, false, null);
			channel.queueDeclare("com.renke.d.queue.headers", true, false, false, null);
			channel.queueDeclare("com.renke.d.queue.direct", true, false, false, null);
			
			channel.queueBind("com.renke.d.queue.fanout", "com.renke.d.fanout", "");
			channel.queueBind("com.renke.d.queue.fanout1", "com.renke.d.fanout", "fanout");
			channel.queueBind("com.renke.queue.fanout", "com.renke.fanout", "fanout");
			channel.queueBind("com.renke.queue.fanout1", "com.renke.fanout", "");
//			channel.exchangeBind(destination, source, routingKey, arguments);
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}

