package com.renke.util.Browers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class SocketClient {
	
	private Socket socket;
	
	private HTTP http;
	
	public Socket getSocket(){
		return socket;
	}
	public HTTP getHttp(){
		return http;
	}
	
	public SocketClient(String url,String msg){
		try {
			initHTTP(url, msg);
			socket = new Socket(http.getHost(), http.getPort());
			socket.setKeepAlive(true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public void initHTTP(String url, String msg)
			throws UnknownHostException {
		http = new HTTP();
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
	}
	
	
	public void reset(String url,String msg){
		try {
			socket.close();
			initHTTP(url, msg);
			socket = new Socket(http.getHost(), http.getPort());
			socket.setKeepAlive(true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 读取目录，根据目录读取数据
	 * @param pb
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void writeControl(ParseBook pb) {
		try {
			readData(pb.getCatalogPath(), "");
			List<Map<String,String>> list = pb.readCatalog(http.getBytes());
			cycleReadList(list,pb);
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		} 
	}
	
	/**
	 * 周一，把log4j和多线程加进来
	 * @param list
	 * @param pb
	 */
	public void cycleReadList(List<Map<String,String>> list,ParseBook pb){
		List<Map<String,String>> errorList = new ArrayList<Map<String,String>>();
		int i = 0;
		while(list.size()>0){
			Map<String,String> m = list.remove(0);
			try {
//				if(m.get("href").equals("6026759")){
					readData(pb.getStartPath()+m.get("href"),null);
					i = pb.writeBookByBytes(http.getBytes(), m.get("title"), pb.getIndex(m.get("href")));
//				}
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				i = -1;
			}
			if(i<0) {
				System.out.println(new Date()+"------reset-----------");
				reset(pb.getStartPath()+m.get("href"),null);
				errorList.add(m);
				errorList.addAll(list);
				break;
			}
		}
		if(errorList.size()>0){
			cycleReadList(errorList,pb);
		}
	}
	
	public void readData(String url,String msg) throws Exception{
		initHTTP(url, msg);
		//发送HTTP请求
		System.out.println(url + " start at " + new Date());
		ParseHTTP.sendGetMessage(socket.getOutputStream(), http);
		//解析响应，返回结果保存到HTTP对象
		ParseHTTP.parseResponse(socket.getInputStream(),  http);
		System.out.println(url + " end at " + new Date());
	}
	
	public void searchFile(){
		
	}
	
	public void showResponse(String url,String msg) throws Exception{
		initHTTP(url, msg);
		ParseHTTP.sendGetMessage(socket.getOutputStream(), http,false);
		ParseHTTP.parseResponse(socket.getInputStream(),  http);
	}
	
	public static void main(String[] args) {
		String url = "http://m.biquge.la/booklist/197.html";
		url = "http://www.shuqi6.com/2486/";
		url = "http://www.zhihu.com/question/20598040/answer/95422757";

//		String searchUrl = "http://zhannei.baidu.com/cse/search?s=1243419093590560530&q=";
		
		String bookName = "奥术神座";
		// url = "http://xdmadmin.alpha.joinclub.cn/xdm-admin/admin/login";
		String msg = "";
		ParseBook pb = new ParseBookShuqi6(bookName,url);
		try {
			long b = System.currentTimeMillis();
			//获取连接
			SocketClient sc = new SocketClient(url, msg);
			System.out.println("connect time : " + (System.currentTimeMillis() - b) + "ms");
			//发送HTTP请求
			b = System.currentTimeMillis();//1849917//1856892
			sc.readData(url, msg);
			sc.showResponse(url, msg);
			ParseHTTP.printAll(new ByteArrayInputStream(sc.getHttp().getBytes()));
			sc.writeControl(pb);
//			ParseHTTP.sendGetMessage(sc.getSocket().getOutputStream(), sc.getHttp());
//			//解析响应，返回结果保存到HTTP对象
//			ParseHTTP.parseResponse(sc.getSocket().getInputStream(),  sc.getHttp());
			System.out.println("parse time : " + (System.currentTimeMillis() - b) + "ms");
			//使用Reader读取字节数据，并做规则匹配，筛选出有效数据
//			String html = new String(sc.getHttp().getBytes()
//					, sc.getHttp().getResponse().get(ParseHTTP.ENCODING_KEY));
//			System.out.println(html);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
