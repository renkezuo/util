package com.renke.util.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.renke.exception.ParseHTTPException;

/***
 * 
 * �������Ҫ��url
 * 	���ܲ�֣���url��request����ת��Ϊ�ֽ�����
 * ����HTML
 *  ���ܲ�֣��ӷ��ص��ֽ������У�����responseͷ���ĺ�body���飬��body����ת��Ϊ�ı�
 * ����Ϊ���
 * 
 * ��һ��URL��������������ȥ������󣬲����أ���������N secs�����û�к���������ر�����
 * 
 * 
 * 
 * @author renke.zuo@foxmail.com
 * @time 2016-08-15 10:03:22
 */
public class Browser {
//	private static final Logger logger = LoggerFactory.getLogger(Browser.class);
	
	long responseTime;//��λms
	long keepAlive = 0;//��������ʱ�䣬<0�������֣���λms
	int status = 0;//0��ʼ״̬��1��������ʱ����
	public static void main(String[] args) {
		String url = "www.sina.com.cn";
		printHTML(url);
		
	}
	
	public static void printHTML(String url){
		try {
			Request request = ParseHTTP.getRequestByUrl(url);
			InetSocketAddress sa = new InetSocketAddress(request.getHost(),request.getPort());
			ByteBuffer requestBuffer = ByteBuffer.wrap(request.getBytes());
			SocketChannel sc = SocketChannel.open(sa);
			sc.write(requestBuffer);
			requestBuffer = null;
			//������غ�����һ��ʱ�䣬ͬʱ����һ��״̬��
			ByteBuffer responseBuffer = ByteBuffer.wrap(new byte[2048]);
			Response response = new Response();
			byte[] surplus = ParseHTTP.parseResponseHeader(sc,response,responseBuffer);
			ParseHTTP.parseResponseData(sc, surplus, response, responseBuffer);
			//һ�������һ��ʱ�䣬һ��״̬
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseHTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
