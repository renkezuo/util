package com.renke.ebook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.ebook.pojo.HTTP;
import com.renke.ebook.pojo.HTTPResponse;
import com.renke.util.StringHex;

public class HTTPParser {
	final static Logger logger = LoggerFactory.getLogger(HTTPParser.class);
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
	
	public static void sendGetMessage(SocketChannel socketChannel,HTTP http) throws IOException{
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.put(("GET "+http.getUri()+" HTTP/1.1\r\n").getBytes());
		bb.put(("Host: "+http.getHost()+":"+http.getPort()+"\r\n").getBytes());
		bb.put(("Connection: keep-alive\r\n").getBytes());
		bb.put(("Cache-Control: max-age=0\r\n").getBytes());
		bb.put(("Upgrade-Insecure-Requests: 1\r\n").getBytes());
		bb.put(("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36\r\n").getBytes());
		bb.put(("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n").getBytes());
		bb.put(("Accept-Encoding: gzip, deflate, sdch\r\n").getBytes());
		bb.put(("Accept-Language: zh-CN,zh;q=0.8\r\n").getBytes());
		bb.put("\r\n".getBytes());
		logger.debug(new String(bb.array(),0,bb.position()));
		socketChannel.write(ByteBuffer.wrap(bb.array(),0,bb.position()));
	}
	
	public static void parseResponse(SocketChannel socketChannel,final HTTP http) throws Exception{
		ByteBuffer responseBuf = ByteBuffer.allocate(4096);
		final HTTPResponse response = new HTTPResponse();
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
						logger.debug("{}",response);
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
								readLength = tmpByte.length + readLength;
							}else{
								if(length - readLength > 0){
									baos.write(tmpByte, 0,length - readLength);
									readLength = length ;
								}
							}
						}
						if(readLength == length){
							response.setBytes(baos.toByteArray());
							tmp = null;
							tmpByte = null;
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
	
	private static void parseHeader(byte[] tmpb,Map<String,String> result){
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
			e.printStackTrace();
			return null;
		}
	}
}
