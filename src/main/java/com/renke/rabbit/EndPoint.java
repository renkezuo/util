package com.renke.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * ���ܸ�Ҫ�� EndPoint���͵Ķ���
 * 
 * @author linbingwen
 * @since 2016��1��11��
 */
public abstract class EndPoint {

	protected Channel channel;
	protected Connection connection;
	protected String endPointName;

	public EndPoint(String endpointName) throws IOException, TimeoutException {
		this.endPointName = endpointName;

		// Create a connection factory
		ConnectionFactory factory = new ConnectionFactory();

		// hostname of your rabbitmq server
		factory.setHost("192.168.20.21");
		factory.setPort(5672);
		factory.setUsername("leke");
		factory.setPassword("leke@@@");

		// getting a connection
		connection = factory.newConnection();

		// creating a channel
		channel = connection.createChannel();

		// declaring a queue for this channel. If queue does not exist,
		// it will be created on the server.
		channel.queueDeclare(endpointName, false, false, false, null);
	}

	/**
	 * �ر�channel��connection�����Ǳ��룬��Ϊ�������Զ����õġ�
	 * 
	 * @throws IOException
	 * @throws TimeoutException 
	 */
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}
}