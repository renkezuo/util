package com.renke.util.channel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelClient {
	
	class SocketThread implements Runnable{
		private int port = 0;
		public SocketThread(int port){
			this.port = port;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				SocketChannel sc = SocketChannel.open();
				sc.connect(new InetSocketAddress(port));
				String msg = "I say : I'm not a bad guy ";
//				使用socket处理
				OutputStream os = sc.socket().getOutputStream();
				os.write(msg.getBytes());
				sc.socket().shutdownOutput();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	static class BufferThread implements Runnable{
		
		private int port = 0;
		public BufferThread(int port){
			this.port = port;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				SocketChannel sc;
				sc = SocketChannel.open();
				sc.connect(new InetSocketAddress(port));
				String msg = "I'm not a bad guy " + port;
				ByteBuffer buf = ByteBuffer.wrap(msg.getBytes());
				Thread.sleep(3000);
				//彻底脱离socket
				sc.write(buf);
				sc.shutdownOutput();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		int i=0;
//		for(i=0;i<3;i++){
			Thread t = new Thread( new BufferThread(8018+i));
			t.start();
//		}
	}
}
