package com.renke.http;
import java.io.Serializable;
import java.util.Map;

public class HTTP implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String host,uri,msg,ip;
	private int port;
	private Response response;
	transient private Map<String,String> responseMap;
	transient private Map<String,String> requestMap;
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
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Map<String, String> getResponseMap() {
		return responseMap;
	}
	public void setResponseMap(Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}
	public Map<String, String> getRequestMap() {
		return requestMap;
	}
	public void setRequestMap(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	@Override
	public String toString() {
		return "http://"+host+":"+port+uri+";ip="+ip+";msg="+msg;
	}
}
