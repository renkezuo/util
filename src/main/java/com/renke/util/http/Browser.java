package com.renke.util.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.renke.exception.ParseHTTPException;

/***
 * 
 * 浏览器需要有url
 * 	功能拆分：从url至request报文转换为字节数组
 * 返回HTML
 *  功能拆分：从返回的字节数组中，解析response头报文和body数组，将body数组转换为文本
 * 此类为入口
 * 
 * 给一个URL给浏览器，浏览器去完成请求，并返回，保持连接N secs，如果没有后续请求，则关闭连接
 * 
 * 
 * 
 * @author renke.zuo@foxmail.com
 * @time 2016-08-15 10:03:22
 */
public class Browser {
//	private static final Logger logger = LoggerFactory.getLogger(Browser.class);
	
	long responseTime;//单位ms
	long keepAlive = 0;//保持连接时间，<0，不保持，单位ms
	int status = 0;//0初始状态，1，请求暂时结束
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
			//结果返回后，设置一个时间，同时设置一个状态，
			ByteBuffer responseBuffer = ByteBuffer.wrap(new byte[2048]);
			Response response = new Response();
			byte[] surplus = ParseHTTP.parseResponseHeader(sc,response,responseBuffer);
			ParseHTTP.parseResponseData(sc, surplus, response, responseBuffer);
			//一个浏览器一个时间，一个状态
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
