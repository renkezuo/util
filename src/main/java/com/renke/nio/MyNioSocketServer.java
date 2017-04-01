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
			//��������ͨ��
			ServerSocketChannel ssc = ServerSocketChannel.open();
			
			//Ϊtrueʱ����
			ssc.configureBlocking(false);

			//�󶨷���ͨ���׽��ּ�����ip:port
			InetSocketAddress sa = new InetSocketAddress("127.0.0.1",8080);
			//�׽��ְ�IP:port
			ssc.socket().bind(sa);
			
			//��һ��ѡ����selector
			Selector selector = Selector.open();
			
			//ע����������¼�
			ssc.register(selector,SelectionKey.OP_ACCEPT);
			
			System.out.println("server start!!!");
			while(true){
				selector.select();
				//�������¼�����ȡkey�б�ȡ��key��ɾ���б���key
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				while(keys.hasNext()){
					SelectionKey key = keys.next();
					keys.remove();
					//��������Ӳ������򽫿ͻ���ͨ���󶨵�selector��
					if(key.isAcceptable()){
						handler(key,selector);
					}
					//����Ƕ���������Ӧ�ͻ���Ϊд�룬��ִ������������
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
