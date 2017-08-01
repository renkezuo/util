package com.renke.ebook;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.ebook.pojo.HTTP;

public class ConnectManager {
final static Logger logger = LoggerFactory.getLogger(ConnectManager.class);
	
	private SocketChannel socketChannel;
	
	private HTTP http;
	
	public ConnectManager(String url){
		try {
			this.http = getHTTP(url);
			reset(this.http.getIp(),this.http.getPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public ConnectManager(String ip,int port){
		reset(ip,port);
		this.http = new HTTP();
		http.setIp(ip);
		http.setPort(port);
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
	public HTTP getHTTP(String url)
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
		if(this.http == null){
			this.http = new HTTP();
			InetAddress inetHost = InetAddress.getByName(host);
			this.http.setIp(inetHost.getHostAddress());
			this.http.setPort(Integer.parseInt(port));
		}
		http.setIp(this.http.getIp());
		http.setPort(this.http.getPort());
		http.setHost(host);
		http.setUri(uri);
		return http;
	}
	
	public HTTP getHTTP(){
		return http;
	}
	
	public void reset(String ip,int port){
		try {
			if(socketChannel != null){
				socketChannel.close();
			}
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress(ip,port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset(String url){
		try {
			HTTP http = getHTTP(url);
			reset(http.getIp(),http.getPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void readData(HTTP http) throws Exception{
		Long begin = System.currentTimeMillis();
		logger.info("start {} {}",http,new Date());
		//发送HTTP请求
		HTTPParser.sendGetMessage(socketChannel, http);
		//解析响应，返回结果保存到HTTP对象
		HTTPParser.parseResponse(socketChannel,  http);
		logger.info("end {} ms ",System.currentTimeMillis()-begin,http);
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
