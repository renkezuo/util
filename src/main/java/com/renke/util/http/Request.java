package com.renke.util.http;

import java.io.Serializable;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private String host,uri,msg,ip;
	private int port;
	transient private byte[] bytes;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "http://"+host+":"+port+uri+";ip="+ip+";msg="+msg;
	}
}
