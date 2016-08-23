package com.renke.util.Browers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class CloneClient {
	public static HTTP getHTTPHeader(String url,String msg) throws UnknownHostException{
		HTTP http = new HTTP();
		String[] params = url.split("/");
		String host = "";
		String uri = "";
		String port = "80";
		if(params[0].equals("http:")){
			host  = params[2];
			uri = url.substring(("http://"+host).length());
		}else{
			host = params[0];
			uri = url.substring(host.length());
		}
		String[] hosts = host.split(":");
		host = hosts[0];
		if(hosts.length>1) port = hosts[1]; 
		InetAddress inetHost = InetAddress.getByName(host);
		http.setHost(host);
		http.setIp(inetHost.getHostAddress());
		http.setUri(uri);
		http.setPort(Integer.parseInt(port));
		http.setMsg(msg);
		return http;
	}
	
	public static void builderGetMessagePhone(OutputStream os,HTTP http) throws IOException{
		os.write(("GET "+http.getHost()+":"+http.getPort()+http.getUri()+" HTTP/1.1 \r\n").getBytes());
		os.write(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		os.write(("Connection: keep-alive\r\n").getBytes());
		os.write(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n").getBytes());
		os.write(("Accept-Language: zh-cn\r\n").getBytes());
		os.write(("Accept-Encoding: gzip, deflate\r\n").getBytes());
		os.write(("User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13F69 Safari/601.1\r\n").getBytes());
//		os.write((http.getMsg()+"\r\n").getBytes());
	}
	
	public static void builderGetMessagePC(OutputStream os,HTTP http) throws IOException{
		os.write(("GET "+http.getUri()+" HTTP/1.1 \r\n").getBytes());
		os.write(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		os.write(("Connection: keep-alive\r\n").getBytes());
		os.write(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n").getBytes());
		os.write(("Accept-Language: zh-cn,zh;q=0.8\r\n").getBytes());
		os.write(("Accept-Encoding: gzip, deflate\r\n").getBytes());
		os.write(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\r\n").getBytes());
//		os.write((http.getMsg()+"\r\n").getBytes());
	}
	
	public static byte[] getGZIPBytesByInputStream(InputStream is) throws IOException{
//		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		boolean endHeader = false;
		while((line = br.readLine()) != null){
			if(endHeader){
				System.out.println(new String(line.getBytes()));
			}
			if(line.equals("\r\n")) endHeader = true; 
		}
		return null;
	}
	
	/**
	 * TODO:组装Post报文
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-07 11:07:58
	 * @param os
	 */
	public static void builderPostMessage(OutputStream os,String url,String msg){
		
	}
	
	
	public static void main(String[] args) {
		String url = "http://m.biquge.la/booklist/197.html";
//		String msg = "";
//		Socket socket = null;
		try {
			
			URLConnection uc = new URL(url).openConnection();
			
			
//			HTTPHeader http = getHTTPHeader(url,msg);
//			socket = new Socket(http.getIp(),http.getPort());
//			socket = new Socket("127.0.0.1",8080);
//			socket.setKeepAlive(true);
	        
			
			
			
			
			
			
			
//			Socket s = new Socket("m.biquge.la",80);
//			socket = new Socket(http.getHost(),80);
//            OutputStream os = socket.getOutputStream();
//            
//            os.write("GET /booklist/197.html HTTP/1.1\r\n".getBytes());
//	        os.write(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
//	        os.write("Connection: keep-alive\r\n".getBytes());
//	        os.write("Cache-Control: max-age=0\r\n".getBytes());
//	        os.write("Upgrade-Insecure-Requests: 1\r\n".getBytes());
//	        os.write("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\r\n".getBytes());
//	        os.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n".getBytes());
//	        os.write("Accept-Encoding: gzip, deflate, sdch\r\n".getBytes());
//	        os.write("Accept-Language: zh-CN,zh;q=0.8\r\n".getBytes());
//	        os.write("\r\n".getBytes());
//	        socket.shutdownOutput();
//            InputStream is = socket.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line = br.readLine();
//            System.out.println(line);
//			
//			
//			
//			
//			
			
			
			
			
			
			
//	        InputStream is = socket.getInputStream();
//	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//	        socket.shutdownInput();
//	        String line = "";
//	        GZIPInputStream gis = new GZIPInputStream(new );
//	        getGZIPBytesByInputStream(is);
	        /***
	         * 此处如何将inputStream转换为GzipInputStream
	         * 将数据全部拿下来，然后解开
	         */
//	        while((line = br.readLine()) != null){
//	        	
//	        	System.out.println(new String(line.getBytes()));
//	        }
			uc.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		builderGetMessage(null, "127.0.0.1:8080/xdm-admin/admin/admin");
	}
}
