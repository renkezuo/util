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
		//两个线程，boss线程和工作线程
		ExecutorService boss = Executors.newCachedThreadPool(new HaveNameThreadFactory("boss"));
		ExecutorService worker = Executors.newCachedThreadPool(new HaveNameThreadFactory("worker"));
		ChannelFactory factory = new NioServerSocketChannelFactory(boss,worker);
		server.setFactory(factory);
		server.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				//此处打印boss线程名称，handler中打印worker线程名称
				//线程名称由NioWorker注册时命名
				System.out.println(Thread.currentThread().getName());
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("messageHandler", new SimpleHandler());
				return pipeline;
			}
		});
		server.bind(new InetSocketAddress(8080));
		//question
		//每个连接都会开一个链接。这个跟NIO的socket一样吗？
		//创建通道工厂时可设置工作线程的数量
		//默认数量为内核数*2
		
		
	}
}
