package com.renke.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NetNioSocketServer {
	private Selector selector;

	/**
	 * ���һ��ServerSocketͨ�������Ը�ͨ����һЩ��ʼ���Ĺ���
	 * 
	 * @param port
	 *            �󶨵Ķ˿ں�
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException {
		// ���һ��ServerSocketͨ��
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// ����ͨ��Ϊ������
		serverChannel.configureBlocking(false);
		SocketAddress sa = new InetSocketAddress(port);
		
		// ����ͨ����Ӧ��ServerSocket�󶨵�port�˿�
		serverChannel.socket().bind(sa);
		// ���һ��ͨ��������
		this.selector = Selector.open();
		// ��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_ACCEPT�¼�,ע����¼���
		// �����¼�����ʱ��selector.select()�᷵�أ�������¼�û����selector.select()��һֱ������
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
	 * 
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("����������ɹ���");
		// ��ѯ����selector
		while (true) {
			// ��ע����¼�����ʱ���������أ�����,�÷�����һֱ����
			selector.select();
			// ���selector��ѡ�е���ĵ�������ѡ�е���Ϊע����¼�
			Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				// ɾ����ѡ��key,�Է��ظ�����
				ite.remove();
				// �ͻ������������¼�
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// ��úͿͻ������ӵ�ͨ��
					SocketChannel channel = server.accept();
					// ���óɷ�����
					channel.configureBlocking(false);

					// ��������Ը��ͻ��˷�����ϢŶ
					channel.write(ByteBuffer.wrap(new String("��ͻ��˷�����һ����Ϣ").getBytes()));
					// �ںͿͻ������ӳɹ�֮��Ϊ�˿��Խ��յ��ͻ��˵���Ϣ����Ҫ��ͨ�����ö���Ȩ�ޡ�
					channel.register(this.selector, SelectionKey.OP_READ);

					// ����˿ɶ����¼�
				} else if (key.isReadable()) {
					read(key);
				}
			}
		}
	}

	/**
	 * �����ȡ�ͻ��˷�������Ϣ ���¼�
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// �������ɶ�ȡ��Ϣ:�õ��¼�������Socketͨ��
		SocketChannel channel = (SocketChannel) key.channel();
		// ������ȡ�Ļ�����
		ByteBuffer buffer = ByteBuffer.allocate(10);
		int length = channel.read(buffer);
		if (length > 0) {
			byte[] data = buffer.array();
			String msg = new String(data).trim();
			System.out.println(Thread.currentThread().getName() + "������յ���Ϣ��" + msg);
			ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
			channel.write(outBuffer);// ����Ϣ���͸��ͻ���
		} else {
			System.out.println("�ͻ��˹ر���");
			key.cancel();
		}
	}

	/**
	 * ��������˲���
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NetNioSocketServer server = new NetNioSocketServer();
		server.initServer(8000);
		server.listen();
	}
}
