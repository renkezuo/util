package com.renke.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class MyNioSocketServer {
	
	public static void handler(SelectionKey key,Selector selector) throws IOException{
		ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
	}
	
	public static void read(SelectionKey key) throws IOException{
		SocketChannel sc = (SocketChannel)key.channel();
//		SocketChannel sc = ssc.accept();
//		sc.configureBlocking(false);
//		sc.register(selector, SelectionKey.OP_READ);
		byte[] buf = new byte[2048];
		ByteBuffer bb = ByteBuffer.wrap(buf);
		int k = sc.read(bb);
		System.out.println(k);
		if(k<=0){
			key.cancel();
		}else{
			System.out.println(new String(bb.array(),0,k));
		}
	}
	
	public static void main(String[] args) {
		try {
			//创建服务通道
			ServerSocketChannel ssc = ServerSocketChannel.open();
			
			//为true时报错
			ssc.configureBlocking(false);

			//绑定服务通道套接字监听的ip:port
			InetSocketAddress sa = new InetSocketAddress("127.0.0.1",8080);
			//套接字绑定IP:port
			ssc.socket().bind(sa);
			
			//打开一个选择器selector
			Selector selector = Selector.open();
			
			//注册监听请求事件
			ssc.register(selector,SelectionKey.OP_ACCEPT);
			
			System.out.println("server start!!!");
			while(true){
				selector.select();
				//监听到事件，获取key列表，取出key，删除列表中key
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()){
					SelectionKey key = keys.next();
					keys.remove();
					//如果是连接操作，则将客户端通道绑定到selector中
					if(key.isAcceptable()){
						handler(key,selector);
					}
					//如果是读操作，对应客户端为写入，则执行其他处理器
					if(key.isReadable()){
						read(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
