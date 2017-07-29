package com.renke.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {
	final static Logger logger = LoggerFactory.getLogger(Controller.class);
	
	private SocketChannel socketChannel;
	
	public Controller(String url,String msg){
		try {
			HTTP http = getHTTP(url, msg);
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress(http.getIp(),http.getPort()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 根据URL解析host,ip,port,uri，同时将msg封装到HTTPHeader中
	 * 
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-07 15:18:52
	 * @param url
	 * @param msg
	 * @return
	 * @throws UnknownHostException
	 */
	public HTTP getHTTP(String url, String msg)
			throws UnknownHostException {
		HTTP http = new HTTP();
		String[] params = url.split("/");
		String host = "";
		String uri = "";
		String port = "80";
		if (params[0].equals("http:")) {
			host = params[2];
			uri = url.substring(("http://" + host).length());
		} else {
			host = params[0];
			uri = url.substring(host.length());
		}
		if (uri.equals(""))
			uri = "/";
		String[] hosts = host.split(":");
		host = hosts[0];
		if (hosts.length > 1)
			port = hosts[1];
		InetAddress inetHost = InetAddress.getByName(host);
		http.setHost(host);
		http.setIp(inetHost.getHostAddress());
		http.setUri(uri);
		http.setPort(Integer.parseInt(port));
		http.setMsg(msg);
		return http;
	}
	
	public void reset(String url,String msg){
		try {
			HTTP http = getHTTP(url, msg);
			socketChannel.close();
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress(http.getIp(),http.getPort()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HTTP readData(String url,String msg) throws Exception{
		HTTP http = getHTTP(url, msg);
		logger.info("{}  start at {}",url,new Date());
		//发送HTTP请求
		ParseHTTPChannel.sendGetMessage(socketChannel, http);
		//解析响应，返回结果保存到HTTP对象
		ParseHTTPChannel.parseResponse(socketChannel,  http);
		logger.info("{}  end at {}",url,new Date());
		return http;
	}
	
	public void close(){
		try {
			socketChannel.shutdownInput();
			socketChannel.shutdownOutput();
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//测试文件上传和下载，在断开servlet的情况下，会出现什么问题。
	public static void main(String[] args) throws Exception {
		String request = "GET /mytest/myTest.htm HTTP/1.1\r\n"
				+ "Host: 127.0.0.1:9090\r\n"
//				+ "Connection: keep-alive\r\n"
				+ "Connection: close\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Encoding: gzip, deflate, sdch\r\n"
				+ "Accept-Language: zh-CN,zh;q=0.8\r\n";
		Controller controller = new Controller("http://127.0.0.1:9090/mytest/mytest.htm",null);
		ParseHTTPChannel.sendGetMessage(controller.socketChannel, request);
		HTTP http = controller.getHTTP("127.0.0.1:9090",null);
		System.out.println(http);
//		ParseHTTPChannel.parseResponse(controller.socketChannel, http);
//		BufferedReader in = new BufferedReader(new InputStreamReader(
//		new ByteArrayInputStream(http.getBytes()),"UTF-8"));
//		String line = "";
//		while( (line = in.readLine()) !=null){
//			System.out.println(line);
//		}
		controller.socketChannel.socket().getInputStream().close();
//		controller.socketChannel.socket().getOutputStream().close();
		controller.socketChannel.socket().close();
	}
}
