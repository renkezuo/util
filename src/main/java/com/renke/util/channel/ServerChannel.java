package com.renke.util.channel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerChannel {
	final static Logger logger = LoggerFactory.getLogger(ServerChannel.class);
	public static String response(String msg,String encode) throws UnsupportedEncodingException{
		StringBuilder sb = new StringBuilder();
		sb.append("HTTP/1.1 200 OK\r\n")
		.append("Date: Sat, 31 Dec 2005 23:59:59 GMT\r\n")
		.append("Content-Type: text/html;charset=").append(encode).append("\r\n")
		.append("Content-Length:").append(msg.getBytes(encode).length * 2).append("\r\n")
		.append("\r\n")
		.append(msg);
		return sb.toString();
	}
	
	public static void doSth(ServerSocketChannel ssc,String msg,String encode) throws IOException{
		SocketChannel sc = ssc.accept();
		logger.info("localAddress:{}",sc.getLocalAddress());
		logger.info("remoteAddress:{}",sc.getRemoteAddress());
		ByteBuffer bb = ByteBuffer.allocate(2048);
		int len = 0;
		while((len = sc.read(bb)) > 0){
			//��ȡ��byteBuffer�У��Ὣposition����Ϊlen+1
			//���ԣ������ȡ��������Ҫ����ByteBuffer�еļ�������
			//��limit����Ϊposition����position����Ϊ0����mark����Ϊ-1
			//���ڶ�ȡ������ǣ���0��ʼ����ȡlimit������
			bb.flip();
			byte[] buf = bb.array();
			String result = new String(buf,0,len);
//			logger.info("request:\r\n{}",result);
			if(result.endsWith("\r\n")){
				sc.shutdownInput();
				sc.write(ByteBuffer.wrap(response(msg,encode).getBytes(encode)));
				sc.write(ByteBuffer.wrap(msg.getBytes(encode)));
//				sc.shutdownOutput();
				sc.close();
				break;
			}
		}
		logger.info("---over---");
		if(sc.isOpen()) sc.close();
	}
	
	
	
	public static void startSimpleServer()throws IOException, InterruptedException{
		String msg = "hello world ! ��ã����磡I'm Simple";
		String encode = "UTF-8";
		ServerSocketChannel ssc = ServerSocketChannel.open();
		//����˴���������while(true)������CPU��
//		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress(8017));
		while (true) {
			logger.info("Simple:I'm  wating ? ");
			doSth(ssc, msg, encode);
		}
	}
	
	/***
	 * �ظ���ѭ��
	 * accept/remove
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-21 17:19:45
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void startSelectorServer()throws IOException, InterruptedException{
		String msg = "hello world ! ��ã����磡I'm Selector";
		String encode = "UTF-8";
		Selector sel = Selector.open();
		for(int i=0;i<3;i++){
			ServerSocketChannel ssc = ServerSocketChannel.open();
			//������������selector�ˡ�
			ssc.configureBlocking(false);
			ssc.bind(new InetSocketAddress(8018+i));
			ssc.register(sel, SelectionKey.OP_ACCEPT);
		}
		//�ȸ�����selector�Ǹ�������ð�
		//��ĿǰΪֹ��SocketChannel��ServerSocketChannel֮�䵽����ʲô��ϵ
		//ServerSocketChannel����ͨ��SocketChannel
		//ͨ�����г�Buffer
		//����������Byte[]
		while (true) {
			logger.info("Selector:I'm  wating ? ");
			int selected = sel.select();
			if(selected>0){
				Iterator<SelectionKey> it = sel.selectedKeys().iterator();
				while(it.hasNext()){
					SelectionKey key = it.next();
					ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
					doSth(serverChannel,msg,encode);
//					logger.info("I don't accept");
//					SocketChannel sc = serverChannel.accept();
//					logger.info("I  accept");
//					ByteBuffer bf = ByteBuffer.allocate(2048);
//					int len  = sc.read(bf);
//					logger.info("client say : {}",new String (bf.array(),0,len));
//					key.isReadable();
//					logger.info("I'm {}",key);
					//���д���ȥ���Ժ󣬻ᵼ��sel.select������
					//����������������
					//1�����û��96-102�д��룬�ᵼ������ѭ��
					//2�����it.remove()���д���ɾ������Ҳ�ᵼ������ѭ��
					//���ȣ��Ҳ²�����Ϊread��ԭ��ע�͵�read�������Ƿ��ѭ��
					//�²������ô������ע�ͣ���sc=serverChannel.accept();�о���������
					//OK��ʼ����ѭ���ˡ�
					//���ˣ�ȡ�������ע�ͣ�ע�͵�it.remove()������ִ�����λᷢ��ʲô��
					//1������ѭ����selected = 1��һֱѭ����it.remove��Ч�����������accept����������һֱִ�С�
					//2��Ҳ������ѭ����selected = 0��������remove������selected��0������remove����������ʹ����Ϊ0�ˡ�[����Ϊʲô����]
					//selector ��ά����һ��selectedKeys
					//��һ�ν���������selectedKeys�в����ڵľ��������ҵ��ˣ�����1
					//�ڶ��ν���������selectedKeys�в����ڵľ�������û�ҵ�����Ϊû���ͷţ�����0��Ϊʲô���ȴ��أ�
					it.remove();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerChannel.startSelectorServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerChannel.startSimpleServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}
