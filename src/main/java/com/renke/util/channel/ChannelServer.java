package com.renke.util.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class ChannelServer{
	public static void main(String[] args) throws IOException {
		
		SocketChannel channel = SocketChannel.open();
		channel.bind(new InetSocketAddress(8080));
		
		Selector selector = Selector.open();
		//是否阻塞
		channel.configureBlocking(false);
		channel.register(selector, (SelectionKey.OP_READ | SelectionKey.OP_WRITE));
		
		while(true){
			int readyChannels = selector.select();
			if(readyChannels == 0) continue;
			Set<SelectionKey> sks = selector.selectedKeys();
			Iterator<SelectionKey> iter = sks.iterator();
			while(iter.hasNext()){
				SelectionKey skey = iter.next();
				if(skey.isAcceptable()){
					System.out.println("this is accept selectionKey");
				}else if(skey.isWritable()){
					System.out.println("this is write selectionKey");
				}else if(skey.isReadable()){
					System.out.println("this is read selectionKey");
				}else if(skey.isConnectable()){
					System.out.println("this is connection selectionKey");
				}else{
					System.out.println("I don't know it is!");
				}
				iter.remove();
			}
		}
	}
}
