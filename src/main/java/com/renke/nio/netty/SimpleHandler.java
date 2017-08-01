package com.renke.nio.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class SimpleHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("messageReceived");
		System.out.println(e.getMessage());
		super.messageReceived(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("channelOpen");
		super.channelOpen(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

	@Override
	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("connectRequested");
		super.connectRequested(ctx, e);
	}

	@Override
	public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.out.println("disconnectRequested");
		super.disconnectRequested(ctx, e);
	}
	
}
