package com.renke.mq.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {
	public static Connection getNewConnection(){
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(RootConfig.HOST);
			factory.setPort(RootConfig.PORT);
			factory.setUsername(RootConfig.USERNAME);
			factory.setPassword(RootConfig.PASSWORD);
			return factory.newConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class RootConfig{
	public final static String HOST = "112.124.38.39";
	public final static int PORT = 5672;
	public final static String USERNAME = "renke";
	public final static String PASSWORD = "renke@@@";
}