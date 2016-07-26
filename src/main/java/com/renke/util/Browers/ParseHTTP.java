package com.renke.util.Browers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.renke.util.StringHex;

public class ParseHTTP {
	public final static String CHUNKED_KEY = "TRANSFER-ENCODING";
	public final static String CONTENT_LENGTH = "CONTENT-LENGTH";
	public final static String CONTENT_TYPE = "CONTENT-TYPE";
	public final static String CONTENT_ENCODING = "CONTENT-ENCODING";
	public final static String ACCEPT_ENCODING = "ACCEPT-ENCODING";
	public final static String ENCODING_KEY = "ENCODING";
	public final static String DEFAULT_ENCODING = "GBK";
	public final static String CHUNKED = "chunked";
	public final static String HEADER_FLAG = "END";
	public final static String FIRST_HTTP_LINE = "HTTP";
	
	/**
	 * 解析数据
	 * FIXME [可以考虑，将bytes替换成baos]
	 * @param is
	 * @param http
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void parseResponse(InputStream is,HTTP http) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String,String> responseMap = new HashMap<String,String>();
		//首先读取协议头信息
		//每次读取一个字节，判断字节\r，且协议头文件未结束。
		//如果连续两个字节为\r\n，则保存到头map中
		//如果后两个字节依然是\r\n，则协议头结束
		parseHeaderData(is,responseMap);
		if(CHUNKED.equals(responseMap.get(CHUNKED_KEY))){
			int size = parseChunkedData(is,baos);
			while(size > 0 ){
				is.read();
				is.read();
				size = parseChunkedData(is,baos);
			}
		}else{
			int size = Integer.parseInt(responseMap.get(CONTENT_LENGTH));
			//读取is流中size个字节，写入到baos中
			baos.write(readDataToBytes(size,is));
		}
		String zip_ = responseMap.get(CONTENT_ENCODING);
		if (zip_ != null && zip_.toUpperCase().indexOf("GZIP") != -1) {
			// 使用GZIP流装载字节数组，此时已经解码
			// GZIP中的字节数组长度和bytes字节数组长度不同了
			// 输出字符串
			GZIPInputStream gzip = new GZIPInputStream(
					new ByteArrayInputStream(baos.toByteArray()));
			http.setBytes(readDataToBytes(gzip));
			gzip.close();
		}else{
			http.setBytes(baos.toByteArray());
		}
		baos.close();
		http.setResponse(responseMap);
		parseEncoding(http);
	}
	

	/**
	 * 解析编码格式
	 * @param http
	 */
	public static void parseEncoding(HTTP http){
		String encoding = DEFAULT_ENCODING;
		String content_type = http.getResponse().get(CONTENT_TYPE);
//		String accept_encoding = http.getResponse().get(ACCEPT_ENCODING);
		if (content_type != null) {
			String[] types = content_type.split(";");
			for (String type : types) {
				String[] props = type.split("=");
				if (props.length > 1 && "charset".equals(props[0])) {
					encoding = props[1];
				}
			}
		}
		http.getResponse().put(ENCODING_KEY, encoding);
	}
	
	/**
	 * 解析HTTP头信息
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static void parseHeaderData(InputStream is,Map<String,String> responseMap) throws Exception{
		int index = 0;
		byte[] result = new byte[2048];
		int b = is.read();
		boolean endHeader = false;
		while(!endHeader){
			result[index++] = (byte)b;
			if(b==13){
				b = is.read();
				result[index++] = (byte)b;
				if(b==10){
					//显示HTTP头信息
//					System.out.println(new String(result,0,index-2));
					b=is.read();
					String header = new String(result,0,index-2);
					System.out.println("header:"+header);
					index = 0;
					int position = header.indexOf(":");
					if(position > 1)
						responseMap.put(header.substring(0,position).toUpperCase(),header.substring(position+1).trim());
					else
						responseMap.put(FIRST_HTTP_LINE, header);
					if(b==13){
						b = is.read();
						endHeader = true;
						//显示Map信息
//						System.out.println(PrintConsole.mapToString(responseMap));
					}
				}
			}
			if(index != 0 ) b = is.read();
		}
	}
	
	/***
	 * 解析分段数据
	 * @param is
	 * @param os
	 * @return
	 * @throws IOException
	 */
	public static int parseChunkedData(InputStream is , OutputStream os) throws IOException{
		int index = 0;
		int b = 0 ;
		byte[] result = new byte[256];
		while((b = is.read())!= 13){
			result[index++] = (byte)b;
		}
		b = is.read();
		if(index == 0) return 0;
		int size = StringHex.toInt(new String(result,0,index), StringHex.TYPE_HEX);
		os.write(readDataToBytes(size,is));
		return size;
	}
	
	/***
	 * 读取InputStream中size字节的数据，返回字节数组
	 * @param size
	 * @param is
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] readDataToBytes(int size,InputStream is) throws IOException{
		byte[] bytes = new byte[size];
		int read = size;
		int position = 0;
		while(read > 0){
			read = is.read(bytes, position, read);
			position += read;
			read = size - position;
		}
		return bytes;
	}
	
	/**
	 * 返回读取到的所有字节
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static byte[] readDataToBytes(InputStream is) throws IOException{
		byte[] bytes = new byte[2048];
		byte[] buf = new byte[2048];
		int len = 0;
		int count = 0;
		while((len = is.read(buf)) != -1){
			bytes = Arrays.copyOf(bytes, count + len);
			System.arraycopy(buf, 0, bytes, count, len);
			count = count + len;
		}
		return bytes;
	}
	
	
	/***
	 * 读取InputStream中size字节的数据，写入OutputStream中
	 * @param size
	 * @param is
	 * @throws IOException
	 */
	public static void readDataToOutStream(int size,InputStream is,OutputStream os) throws IOException{
		byte[] bytes = new byte[size];
		int len = 0;
		int position = 0;
		while(position < size){
			len = is.read(bytes);
			os.write(bytes,0,len);
			position += len;
			bytes  = new byte[size-position];
		}
//		return bytes;
	}
	
	/**
	 * 将所有读取到的字节写入OutputStream中，并返回
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static void readDataToOutputStream(InputStream is,OutputStream os) throws IOException{
		byte[] buf = new byte[2048];
		int len = 0;
		while((len = is.read(buf)) != -1){
			os.write(buf,0,len);
		}
//		return bytes;
	}
	
	public static void sendGetMessage(OutputStream os,HTTP http) throws IOException{
		sendGetMessage(os,http,true);
	}
	
	/**
	 * 组装一个GET报文
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-07 15:17:58
	 * @param os
	 * @param http
	 * @throws IOException
	 */
	public static void sendGetMessage(OutputStream os,HTTP http,boolean hasGzip) throws IOException{
		os.write(("GET "+http.getUri()+http.getMsg()+" HTTP/1.1 \r\n").getBytes());
		os.write(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		os.write(("Connection: keep-alive\r\n").getBytes());
		os.write(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n").getBytes());
		os.write(("Accept-Language: zh-cn,zh;q=0.8\r\n").getBytes());
		if(hasGzip) os.write(("Accept-Encoding: gzip, deflate, sdch\r\n").getBytes());
		os.write(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\r\n").getBytes());
		os.write("\r\n".getBytes());
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
	
	
	public static void printAll(InputStream is) throws IOException{
		byte[] buf = new byte[2048];
		int len = 0;
		while((len = is.read(buf))!=-1){
			System.out.println(new String(buf,0,len,"UTF-8"));
		}
	}
	
	public static void arrayExtend(int[] array, int new_length){
		int[] extend = new int[new_length];
		System.arraycopy(array, 0, extend, 0, Math.min(array.length,new_length));
		array = extend;
	}
	public static void main(String[] args) {
		int[] array = {1,2,3}; 
		arrayExtend(array,6);
		for (int i : array) {
			System.out.println(i);
		}
		System.out.println(array.length);
	}
}
