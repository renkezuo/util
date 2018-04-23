package com.renke.mq.rabbit;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.renke.mq.Manager;

public class RabbitManager implements Manager{
	Channel channel = null;
	
	public RabbitManager(){
		channel = RabbitChannel.getInstance();
	}
	
	public RabbitManager(Channel channel){
		this.channel = channel;
	}
	
	@Override
	public void create(Map<String, Object> properties) {
		try {
			// 队列申明[队列key，是否持久化，是否独占，是否自动删除，其它参数]
			channel.queueDeclare(properties.get("routingKey").toString()
								, Boolean.parseBoolean(properties.get("durable").toString())
								, Boolean.parseBoolean(properties.get("exclusive").toString())
								, Boolean.parseBoolean(properties.get("autoDelete").toString())
								, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		Connection conn = channel.getConnection();
		try{
			if(channel != null) {
				channel.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int msgCount() {
		return 0;
	}
	
}
