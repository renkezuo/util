package com.renke.util.Browers;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.renke.util.StringHex;
import com.renke.util.http.Response;

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
		http.setResponseMap(responseMap);
		parseEncoding(http);
	}
	

	/**
	 * 解析编码格式
	 * @param http
	 */
	public static void parseEncoding(HTTP http){
		String encoding = DEFAULT_ENCODING;
		String content_type = http.getResponseMap().get(CONTENT_TYPE);
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
		http.getResponseMap().put(ENCODING_KEY, encoding);
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
		os.write(("GET "+http.getUri()+http.getMsg()+" HTTP/1.1\r\n").getBytes());
		os.write(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		os.write(("Connection: keep-alive\r\n").getBytes());
		os.write(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n").getBytes());
		os.write(("Accept-Language: zh-cn,zh;q=0.8\r\n").getBytes());
		if(hasGzip) os.write(("Accept-Encoding: gzip, deflate, sdch\r\n").getBytes());
		os.write(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\r\n").getBytes());
		os.write("\r\n".getBytes());
	}
	
	
	public static void sendGetMessage(SocketChannel socketChannel,HTTP http) throws IOException{
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put(("GET "+http.getUri()+http.getMsg()+" HTTP/1.1\r\n").getBytes());
		bb.put(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		bb.put(("Connection: keep-alive\r\n").getBytes());
		bb.put(("Cache-Control: max-age=0\r\n").getBytes());
		bb.put(("Upgrade-Insecure-Requests: 1\r\n").getBytes());
		bb.put(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36\r\n").getBytes());
		bb.put(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n").getBytes());
		bb.put(("Accept-Encoding: gzip, deflate, sdch\r\n").getBytes());
		bb.put(("Accept-Language: zh-CN,zh;q=0.8\r\n").getBytes());
		bb.put("\r\n".getBytes());
		socketChannel.write(ByteBuffer.wrap(bb.array(),0,bb.position()));
	}
	
	public static void parseResponse(SocketChannel socketChannel,HTTP http) throws Exception{
		ByteBuffer responseBuf = ByteBuffer.allocate(4096);
		final Response response = new Response();
		http.setResponse(response);
		byte[] tmp = new byte[4096];
		ByteBuffer lenByte = ByteBuffer.allocate(4096);
		//总长度
		int length = -1;
		//读取长度
		int readLength = 0;
		//行头索引
		int beginIndex = 0;
		//读取数据长度
		int len = 0;
		//空行是否出现
		boolean hasSpace = false;
		//头数据是否组装完毕
		boolean resolveHead = false;
		//临时存放数据队列
//		List<byte[]> tmpList = new ArrayList<byte[]>();
		byte[] tmpByte = null;
		Map<String,String> result = new HashMap<String,String>();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//将所有数据装入到ByteBuffer中
		while((len = socketChannel.read(responseBuf)) > 0){
			responseBuf.clear();
			System.arraycopy(responseBuf.array(),0, tmp,beginIndex,len);
			//剩余总长度
			int countLen = beginIndex + len;
			beginIndex = 0;
			if(!hasSpace){
				//readLine方法，空行时，标记空行索引
				for(int i=0;i<countLen;i++){
					//空行之前，http请求的头信息
					if(i < countLen - 1 && tmp[i]=='\r' && tmp[i+1]=='\n'){
						tmpByte = new byte[i-beginIndex];
						if(tmpByte.length == 0){
							hasSpace = true;
						}else{
							System.arraycopy(tmp, beginIndex, tmpByte, 0, tmpByte.length);
						}
						parseHeader(tmpByte,result);
						++i;
						beginIndex = i+1;
						if(i < countLen - 2 && tmp[i+1] =='\r' && tmp[i+2]=='\n'){
							resolveHead = true;
							hasSpace = true;
							++i;++i;
							beginIndex = i+1;
							tmpByte = new byte[countLen - beginIndex];
							System.arraycopy(tmp, beginIndex, tmpByte, 0, countLen - beginIndex);
							beginIndex = 0;
							break;
						}
					}
				}
			}
			//头信息按行存储
			//剩余数据保留到数组头部
			if(beginIndex < countLen && !hasSpace) {
				System.arraycopy(tmp, beginIndex, tmp,0,countLen-beginIndex);
			}
			if(hasSpace){
				if(resolveHead){
					if(tmpByte == null){
						tmpByte = new byte[len];
						System.arraycopy(tmp,0, tmpByte,0, len);
					}
					if(tmpByte.length <= 0){
						tmpByte = null;
						continue;
					}
					if(response.getHeader()==null){
						response.setChunked(CHUNKED.equals(result.get(CHUNKED_KEY)));
						response.setHeader(result);
						response.setGzip("gzip".equals(result.get(CONTENT_ENCODING)));
						response.setLength(result.get(CONTENT_LENGTH) == null ? -1:Integer.parseInt(result.get(CONTENT_LENGTH)));
						System.out.println(response);
					}
					if(response.getLength() == -1 && !response.isChunked()){
						return;
					}else if(response.isChunked()){
						int i = 0;
						while(i<tmpByte.length){
							if(length == -1){
								for(;i<tmpByte.length;i++){
									byte b = tmpByte[i];
									if(b != 13){
										lenByte.put(b);
									}else{
										try{
											StringHex.toInt(new String(lenByte.array(),0,lenByte.position()), StringHex.TYPE_HEX);
										}catch(Exception e){
											i++;
											lenByte.clear();
											continue;
										}
										length = StringHex.toInt(new String(lenByte.array(),0,lenByte.position()), StringHex.TYPE_HEX);
										lenByte.clear();
										i++;i++;
										break;
									}
								}
							}
							if(length >0){
								if(length > tmpByte.length - i){
									baos.write(tmpByte,i,tmpByte.length - i);
									length = length - tmpByte.length + i;
									i = tmpByte.length;
								}else if (length == tmpByte.length - i){
									baos.write(tmpByte,i,tmpByte.length - i);
									length = -1;
									i = tmpByte.length;
								}else{
									baos.write(tmpByte,i,length);
									i = i + length + 2;
									length = -1;
								}
							}else if(length == 0){
								response.setBytes(baos.toByteArray());
								tmp = null;
								tmpByte = null;
								socketChannel.shutdownInput();
								if("gzip".equals(response.getHeader().get(CONTENT_ENCODING))){
									http.setBytes(decompressGzip(response.getBytes()));
								}else{
									http.setBytes(response.getBytes());
								}
								return;
							}
						}
						tmpByte = null;
					}else{
						if(length == -1)
							length = response.getLength();
						if(tmpByte != null && tmpByte.length > 0){
							if(tmpByte.length + readLength <= length){
								baos.write(tmpByte, 0,tmpByte.length);
//								System.arraycopy(tmpByte, 0, response.getBytes(), readLength, tmpByte.length);
								readLength = tmpByte.length + readLength;
							}else{
								if(length - readLength > 0){
									baos.write(tmpByte, 0,length - readLength);
//									System.arraycopy(tmpByte, 0, response.getBytes(), readLength,length - readLength);
									readLength = length ;
								}
							}
						}
						if(readLength == length){
							response.setBytes(baos.toByteArray());
							tmp = null;
							tmpByte = null;
							socketChannel.shutdownInput();
							if("gzip".equals(response.getHeader().get(CONTENT_ENCODING))){
								http.setBytes(decompressGzip(response.getBytes()));
							}else{
								http.setBytes(response.getBytes());
							}
							return ;
						}
						tmpByte = null;
					}
				}
			}
			
		}
	}
	
	public static void parseHeader(byte[] tmpb,Map<String,String> result){
		for(int index = 0; index<tmpb.length ; index++){
			byte b = tmpb[index] ;
			if(b==':'){
				index ++;
				result.put(new String(tmpb,0,index - 1).trim().toUpperCase()
						, new String(tmpb,index,tmpb.length - index).trim());
				break;
			}
			if(index == tmpb.length - 1){
				result.put(FIRST_HTTP_LINE, new String(tmpb));
			}
		}
	}
	
	
	public static byte[] decompressGzip(byte[] gzipBytes){
		try {
			GZIPInputStream gzip = new GZIPInputStream(
				new ByteArrayInputStream(gzipBytes));
			byte[] bytes = new byte[2048];
			byte[] buf = new byte[2048];
			int len = 0;
			int count = 0;
			while((len = gzip.read(buf)) != -1){
				bytes = Arrays.copyOf(bytes, count + len);
				System.arraycopy(buf, 0, bytes, count, len);
				count = count + len;
			}
			gzip.close();
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
