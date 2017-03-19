package com.renke.http;

import java.io.IOException;
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
}
