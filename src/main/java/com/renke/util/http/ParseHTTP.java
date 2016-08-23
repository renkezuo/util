package com.renke.util.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.exception.ParseHTTPException;
import com.renke.util.StringHex;
/**
 * �������URL����request����
 * ���ݷ����������response����
 * @author renke.zuo@foxmail.com
 * @time 2016-08-15 10:07:27
 */
public class ParseHTTP {
	private static final Logger logger = LoggerFactory.getLogger(ParseHTTP.class);
	private static Request initRequest(String url) throws ParseHTTPException {
		if(url == null) throw new ParseHTTPException(" url is null");
		Request request = new Request();
		String[] parts = url.split("\\?");
		String[] eles = parts[0].toLowerCase().split("/");
		String params = null;
		if(parts.length > 1){
			params = "?"+parts[1];
		}
		String host = "";
		String uri = "/";
		String port = "80";
		if (eles[0].equals("http:")) {
			host = eles[2];
			port = "80";
		} else if(eles[0].equals("https:")){
			host = eles[2];
			port = "443";
		}else {
			host = eles[0];
		}
		if(eles.length > 3 && !eles[3].equals("")){
			uri = eles[3];
		}
		String[] hosts = host.split(":");
		host = hosts[0];
		if (hosts.length > 1)
			port = hosts[1];
		InetAddress inetHost;
		try {
			inetHost = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			throw new ParseHTTPException(e);
		}
		request.setHost(host);
		request.setIp(inetHost.getHostAddress());
		request.setUri(uri);
		request.setPort(Integer.parseInt(port));
		request.setMsg(params == null ? "" : params);
		return request;
	}
	
	/**
	 * ����url��װrequest����
	 * @param url
	 * @return
	 * @throws ParseHTTPException
	 * @author renke.zuo@foxmail.com
	 * @time 2016-08-22 17:13:17
	 */
	public static Request getRequestByUrl(String url) throws ParseHTTPException{
		StringBuilder sb = new StringBuilder();
		Request request = initRequest(url);
		sb.append("GET ").append(request.getUri()).append(request.getMsg()).append(" HTTP/1.1\r\n");
		sb.append("Host: ").append(request.getHost()).append("\r\n");
		sb.append("Cache-Control: max-age=0\r\n");
		sb.append("Upgrade-Insecure-Requests: 1\r\n");
		sb.append("Connection: keep-alive\r\n");
		sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36\r\n");
		sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
		sb.append("Accept-Encoding: gzip, deflate, sdch\r\n");
		sb.append("Accept-Language: zh-cn,zh;q=0.8\r\n");
		sb.append("\r\n");
		request.setBytes(sb.toString().getBytes());
		logger.info("request:{}",request);
		logger.info("http request :{}",sb.toString());
		return request;
	}
	
	
	
	/**
	 * ������Ӧͷ
	 * @param sc
	 * @param response
	 * @param defaultBuf
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 * @author renke.zuo@foxmail.com
	 * @time 2016-08-22 17:12:14
	 */
	public static byte[] parseResponseHeader(SocketChannel sc,Response response,ByteBuffer defaultBuf) throws NumberFormatException, IOException{
		if(defaultBuf == null){
			defaultBuf = ByteBuffer.wrap(new byte[2048]);
		}
		byte[] buf = defaultBuf.array();
		int len = 0;
		int beginIndex = 0;
		boolean hasSpace = false;
		Map<String,String> header = null;
		byte[] surplus = null;
		while((len = sc.read(defaultBuf)) > 0 ){
			defaultBuf.flip();
			//��ȡrequestͷ�����ݣ�������ʣ�µ����ݷ���
			for(int i=0;i< len;i = i+1){
				//����֮ǰ��http�����ͷ��Ϣ
				if(i < len - 1 && buf[i]=='\r' && buf[i+1]=='\n' && !hasSpace){
					int tmpLen = i- beginIndex;
					if(tmpLen == 0){
						hasSpace = true;
					}else{
						if(header == null){
							header = new HashMap<String,String>();
							response.setHeader(header);
							header.put("HTTP-Status", new String(buf,9,3));
						}else{
							for(int b = 0;b < tmpLen;b = b + 1){
								int index = b+ beginIndex;
								if(buf[index]==':'){
									header.put(new String(buf, beginIndex,b), new String(buf,index+2,tmpLen- b - 2));
									break;
								}
							}
						}
					}
					i = i + 1;
					beginIndex = i+1;
					//���ֿ���
					if(i < len - 2 && buf[i+1] =='\r' && buf[i+2]=='\n'){
						hasSpace = true;
						i = i+2;
						beginIndex = i+1;
						setResponse(response);
						//ʣ�����ݣ���Ҫ������һ���ڴ���
						if(beginIndex < len){
							surplus = new byte[len-beginIndex];
							System.arraycopy(buf, beginIndex, surplus, 0, surplus.length);
						}
						logger.debug("response:{}",response);
						return surplus;
					}
				}
			}
		}
		return surplus;
	}
	
	
	/***
	 * ������Ӧ���ݣ���ӡ
	 * @param sc
	 * @param surplus
	 * @param response
	 * @param defaultBuf
	 * @author renke.zuo@foxmail.com
	 * @throws IOException 
	 * @time 2016-08-22 17:11:55
	 */
	public static void parseResponseData(SocketChannel sc,byte[] surplus,Response response,ByteBuffer defaultBuf) throws IOException{
		if(defaultBuf == null){
			defaultBuf = ByteBuffer.wrap(new byte[2048]);
		}
		int length = 0;
		int beginIndex = 0;
		byte prev = 0;
		boolean isEnd = false;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while(sc.read(defaultBuf) > 0 ){
			defaultBuf.flip();
			if(response.isChunked()){
				//ȡ���ȣ����ݳ���ȡ����
				//����ȡ���������ݱ������
				//����ʣ������
				if(surplus != null && length == 0){
					int i = 1;
					beginIndex = 0;
					while(i<surplus.length){
						if(prev == '\r' && surplus[i] == '\n'){
							prev = surplus[i];
							length = StringHex.toInt(new String(surplus,beginIndex,i-1), StringHex.TYPE_HEX);
							beginIndex = i + 1;
							if(length == 0){
								//�ֶ�����Ϊ0����ʾ����
								isEnd = true;
								break;
							}
							i = i + 1;
							if(surplus.length - i >= length){
								//�˴����һ����
								baos.write(surplus,i,length);
								writeData(baos, response.isGzip(),response.getCharset());
								baos.reset();
								
								i = i + length + 2 ;
								if(i >= surplus.length){
									length = 0;
									beginIndex = 0;
									surplus = null;
									break;
								}
								beginIndex = i;
								//������ȡʣ����������
								continue;
							}else{
								baos.write(surplus,i,surplus.length-i);
								length = length - (surplus.length-i);
								//ֹͣ��ȡʣ����������
								surplus = null;
								break;
							}
						}
						prev = surplus[i];
						i = i + 1;
					}
				}
				if(surplus == null){
					int i = 0;
					byte[] chunkData = new byte[defaultBuf.limit() - beginIndex];
					System.arraycopy(defaultBuf.array(), beginIndex, chunkData,0,chunkData.length);
					if(length > chunkData.length){
						length = length - chunkData.length;
						baos.write(chunkData);
						//���»�ȡ��������
						continue;
					}else if (length <= chunkData.length && length >= chunkData.length - 2){
						baos.write(chunkData,0,length);
						writeData(baos, response.isGzip(),response.getCharset());
						baos.reset();
						break;
					}else{
						if(length != 0){
							baos.write(chunkData,0,length);
							writeData(baos, response.isGzip(),response.getCharset());
							baos.reset();
						}
						i = length ;
						if(chunkData[i]=='\r'){
							i = i+1;
						}
						if(chunkData[i]=='\n'){
							i = i+1;
						}
						beginIndex = i;
						length = 0;
						while(i<chunkData.length){
							//�ֶ����ݣ���ʲô��ʽ��
							if(prev=='\r' && chunkData[i] == '\n'){
								prev = chunkData[i];
								length = StringHex.toInt(new String(chunkData,beginIndex,i-beginIndex-1), StringHex.TYPE_HEX);
								if(length == 0){
									isEnd = true;
									break;
								}
								i = i + 1;
								beginIndex = i;
								if(chunkData.length - beginIndex > length){
									//�˴����һ����
									baos.write(chunkData,beginIndex,length);
									writeData(baos, response.isGzip(),response.getCharset());
									baos.reset();
									
									i = beginIndex + length + 2;
									if(i >= chunkData.length){
										length = 0;
										beginIndex = i - chunkData.length;
										break;
									}
									beginIndex = i;
									continue;
								}else{
									baos.write(chunkData,beginIndex,chunkData.length-beginIndex);
									length = length - (chunkData.length-beginIndex);
									beginIndex = 0;
									break;
								}
							}
							prev = chunkData[i];
							i = i + 1;
						}
					}
				}
			//�Ƿֶδ���
			}else{
				//ȡ���ȣ����ݳ���ȡ����
				//����ȡ����������
				if(length == 0){
					length = response.getLength();
				}
				byte[] data = new byte[defaultBuf.limit()];
				System.arraycopy(defaultBuf.array(), 0, data, 0, defaultBuf.limit());
				baos.write(data);
				length = length - defaultBuf.limit();
				if(length <=0 ){
					break;
				}
			}
			if(isEnd){
				break;
			}else{
				continue;
			}
		}
		if(baos.size() > 0){ 
			writeData(baos,response.isGzip(),response.getCharset());
		}
		baos.close();
	}
	
	/**
	 * ��response.header���ݣ�һһ���õ�response��Ӧ�ĳ�Ա��
	 * @param response
	 * @author renke.zuo@foxmail.com
	 * @time 2016-08-22 17:01:31
	 */
	private static void setResponse(Response response){
		
		response.setHttpStatus(response.getHeader().get("HTTP-Status"));
		
		//��������Ҫ�ı�����Ϣ
		//�Ƿ�ֶ�
		if(response.getHeader().get("Transfer-Encoding") != null){
			response.setChunked("chunked".equals(response.getHeader().get("Transfer-Encoding")));
		}
		//��ȡ��������ʽ
		String contentType = response.getHeader().get("Content-Type");
		String[] tmps = contentType.split("; ");
		for(int c = 1;c<tmps.length;c++){
			String[] charset = tmps[c].split("=");
			if("charset".equals(charset[0])){
				response.setCharset(charset[1]);
				break;
			}
		}
		//�����ж��Ƿ���gzip��ʽ����
		response.setEncoding(response.getHeader().get("Content-Encoding"));
		response.setGzip("gzip".equals(response.getEncoding()));
		
		//�ֶδ���ʱ���޳���
		if(response.getHeader().get("Content-Length")!=null){
			response.setLength(Integer.parseInt(response.getHeader().get("Content-Length").trim()));
		}
	}
	
	private static void writeData(ByteArrayOutputStream baos,boolean isGzip,String charset) throws IOException{
		if(isGzip){
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			GZIPInputStream gis = new GZIPInputStream(bais);
			int len = 0;
			byte[] buf = new byte[4096];
			while( (len = gis.read(buf) ) != -1){
				if(charset!=null){
					System.out.println(new String(buf,0,len,charset));
				}else{
					System.out.println(new String(buf,0,len));
				}
			}
		}else{
			if(charset!=null){
				System.out.println(new String(baos.toByteArray(),charset));
			}else{
				System.out.println(new String(baos.toByteArray()));
			}
		}
	}
	
	
}
