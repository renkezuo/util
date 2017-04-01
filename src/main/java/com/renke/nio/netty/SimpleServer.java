package com.renke.nio.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class SimpleServer {
	public static void main(String[] args) {
		ServerBootstrap server = new ServerBootstrap();
		//�����̣߳�boss�̺߳͹����߳�
		ExecutorService boss = Executors.newCachedThreadPool(new HaveNameThreadFactory("boss"));
		ExecutorService worker = Executors.newCachedThreadPool(new HaveNameThreadFactory("worker"));
		ChannelFactory factory = new NioServerSocketChannelFactory(boss,worker);
		server.setFactory(factory);
		server.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				//�˴���ӡboss�߳����ƣ�handler�д�ӡworker�߳�����
				//�߳�������NioWorkerע��ʱ����
				System.out.println(Thread.currentThread().getName());
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("messageHandler", new SimpleHandler());
				return pipeline;
			}
		});
		server.bind(new InetSocketAddress(8080));
		//question
		//ÿ�����Ӷ��Ὺһ�����ӡ������NIO��socketһ����
		//����ͨ������ʱ�����ù����̵߳�����
		//Ĭ������Ϊ�ں���*2
		
		
	}
}
