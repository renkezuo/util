package com.renke.util.https;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.SSLSocketFactory;

public class Test {
	public static void main(String[] args) throws IOException, KeyStoreException {
		Socket socket = SSLSocketFactory.getDefault().createSocket("10.10.3.164",18443);
		SocketChannel sc = SocketChannel.open();
		SocketAddress sa = new InetSocketAddress("10.10.3.164",18443);
		sc.connect(sa);
		
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put(("GET /nexus HTTP/1.1\r\n").getBytes());
		bb.put(("Host: 10.10.3.164:18443\r\n").getBytes());
		bb.put(("Connection: keep-alive\r\n").getBytes());
		bb.put(("Cache-Control: max-age=0\r\n").getBytes());
		bb.put(("Upgrade-Insecure-Requests: 1\r\n").getBytes());
		bb.put(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36\r\n").getBytes());
		bb.put(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n").getBytes());
		bb.put(("Accept-Encoding: gzip, deflate, sdch\r\n").getBytes());
		bb.put(("Accept-Language: zh-CN,zh;q=0.8\r\n").getBytes());
		bb.put("\r\n".getBytes());
		sc.write(ByteBuffer.wrap(bb.array(),0,bb.position()));
		bb.clear();
		int len = 0;
		byte[] buf = new byte[1024];
		len = sc.read(bb);
		while((len =sc.read(bb))!=-1){
			System.arraycopy(bb.array(), 0, buf, 0, len);
			bb.clear();
			System.out.println(new String(buf,0,len));
		};
//		KeyStore keystore = KeyStore.getInstance("");
		
		
	}
}
