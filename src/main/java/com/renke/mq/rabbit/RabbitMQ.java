package com.renke.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.renke.mq.Manager;
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
	
	public static Manager getManager(){
		Manager manager = new RabbitManager();
		return manager;
	}
	
}

class RabbitChannel{
	static Connection sendConnection = null;
	static Connection receConnection = null;
	static Connection managerConnection = null;
	static {
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.20.75");
			factory.setPort(5672);
			factory.setUsername("leke");
			factory.setPassword("leke@@@");
			sendConnection = factory.newConnection();
			receConnection = factory.newConnection();
			managerConnection = factory.newConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Channel getInstance(int type){
		Channel channel = null;
		try {
			if(type == 0)
				channel = sendConnection.createChannel();
			else if(type == 1)
				channel = receConnection.createChannel();
			else if(type == 2)
				channel = managerConnection.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}
}