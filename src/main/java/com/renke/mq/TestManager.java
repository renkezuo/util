package com.renke.mq;

import java.util.HashMap;
import java.util.Map;

import com.renke.mq.rabbit.RabbitMQ;

public class TestManager {
	public static void main(String[] args) {
		Manager manager = RabbitMQ.getManager();
		Map<String, Object> properties = new HashMap<>();
		properties.put("createSource", "exchange");
		properties.put("exchangeType", "fanout");
		properties.put("exchangeName", "com.renke.fanout");
		manager.create(properties);
		manager.closeAll();
	}
}
