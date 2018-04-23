package com.renke.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.renke.mq.Receiver;
import com.renke.mq.Sender;

// 消息队列，异步开发
public class RabbitMQ {
	
	public static Sender getSender(String... properties){
		RabbitProducer producer = new RabbitProducer(properties[0],properties[1]);
		return producer;
	}
	
	public static Receiver getReceiver(String... properties){
		RabbitConsumer consumer = new RabbitConsumer(properties[0]);
		return consumer;
	}
	
//	public static void main(String[] args) throws Exception {
//		producer.sendMsg("hello world");
//		producer.close();
//		
//		MyConsumer consumer = new MyConsumer("com.renke.test");
//		consumer.queueDeclare("com.renke.test", false, false, false, null);
//		consumer.startListener();
//	}
}

class Config{
	public final static String HOST = "192.168.20.21";
	public final static int PORT = 5672;
	public final static String USERNAME = "leke";
	public final static String PASSWORD = "leke@@@";
}

class RabbitChannel{
	public static Channel getInstance(){
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(Config.HOST);
			factory.setPort(Config.PORT);
			factory.setUsername(Config.USERNAME);
			factory.setPassword(Config.PASSWORD);
			Connection connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}
}







