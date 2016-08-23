package com.renke.util.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.renke.exception.ParseHTTPException;

/***
 * 
 * 浏览器需要有url
 * 	功能拆分：从url至request报文转换为字节数组
 * 返回HTML
 *  功能拆分：从返回的字节数组中，解析response头报文和body数组，将body数组转换为文本
 * 此类为入口
 * 
 * 给一个URL给浏览器，浏览器去完成请求，并返回，保持连接N secs，如果没有后续请求，则关闭连接
 * 
 * 
 * 
 * @author renke.zuo@foxmail.com
 * @time 2016-08-15 10:03:22
 */
public class BrowserBackup {
//	private static final Logger logger = LoggerFactory.getLogger(Browser.class);
	
	long responseTime;//单位ms
	long keepAlive = 0;//保持连接时间，<0，不保持，单位ms
	int status = 0;//0初始状态，1，请求暂时结束
	public static void main(String[] args) {
		String url = "www.sina.com.cn";
		try {
			Request request = ParseHTTP.getRequestByUrl(url);
			InetSocketAddress sa = new InetSocketAddress(request.getHost(),request.getPort());
			ByteBuffer requestBuffer = ByteBuffer.wrap(request.getBytes());
			SocketChannel sc = SocketChannel.open(sa);
			sc.write(requestBuffer);
//			DataParam dp = new DataParam();
//			sc.shutdownInput();
//			sc.shutdownOutput();
			requestBuffer = null;
			//结果返回后，设置一个时间，同时设置一个状态，
			ByteBuffer responseBuffer = ByteBuffer.wrap(new byte[2048]);
			//行头索引
//			int beginIndex = 0;
			//读取数据长度
//			int len = 0;
			//空行是否出现
//			boolean hasSpace = false;
//			boolean isEnd = false;
//			int length = 0;
			Response response = new Response();
//			Map<String,String> header = null;
//			byte[] surplus = null;
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//读取response数据
			//首先判断1是否已经解析Header数据，使用hasSpace判断
			//  1否：取header数据
					//具体逻辑：
					//	第一行为http状态信息，
					//	接下来每行以冒号分割为key和value
					//	遇到第一个空行时，分析key和value，解析出需要用到的头信息，交给response
					//	结束header从析，将剩余数据[不包含空行]，保留。
			//  1是：判断2是否分段取数
			//		2是：判断3剩余数据是否存在，完毕后，继续判断4剩余数据是否处理完毕
			//			3是：取长度，当剩余数据不够时，将剩余数据全部取出，同时再次去请求数据[记录相应的数据状态]，其实数据状态才是主要的
			//			4是：
			//		2否：直接取长度，当取完data[长度]后，break;
			//beginIndex，开始索引
			//len，每次read的网络数据长度
			//length，发送数据约定的长度[分段或不分段]
			//hasSpace，判断是否读取完header信息
			//isEnd，判断数据是否全部读取完毕
			//prev，表示上一个字节，一般用来判断换行时使用
			
			//解析response Header信息，返回剩余数据数组
			byte[] surplus = ParseHTTP.parseResponseHeader(sc,response,responseBuffer);
			ParseHTTP.parseResponseData(sc, surplus, response, responseBuffer);
//			//处理剩余数据
//			while((dp.len = sc.read(responseBuffer)) > 0 ){
//				responseBuffer.flip();
//				if(response.isChunked()){
//					//取长度，根据长度取数据
//					//数据取出来，根据编码解码
//					//存在剩余内容
//					if(surplus != null && dp.length == 0){
//						int i = 1;
//						dp.beginIndex = 0;
//						while(i<surplus.length){
//							if(dp.prev == '\r' && surplus[i] == '\n'){
//								dp.prev = surplus[i];
//								dp.length = StringHex.toInt(new String(surplus,dp.beginIndex,i-1), StringHex.TYPE_HEX);
//								logger.info("dp.length:{}",dp.length);
//								dp.beginIndex = i + 1;
//								if(dp.length == 0){
//									//分段数组为0，表示结束
//									dp.isEnd = true;
//									break;
//								}
//								i = i + 1;
//								if(surplus.length - i >= dp.length){
//									baos.write(surplus,i,dp.length);
//									i = i + dp.length + 2 ;
//									if(i >= surplus.length){
//										dp.length = 0;
//										dp.beginIndex = 0;
//										surplus = null;
//										break;
//									}
//									dp.beginIndex = i;
//									//继续读取剩余数组数据
//									continue;
//								}else{
//									baos.write(surplus,i,surplus.length-i);
//									dp.length = dp.length - (surplus.length-i);
//									//停止读取剩余数组数据
//									surplus = null;
//									break;
//								}
//							}
//							dp.prev = surplus[i];
//							i = i + 1;
//						}
//					}
//					if(surplus == null){
//						int i = 0;
//						byte[] chunkData = new byte[responseBuffer.limit() - dp.beginIndex];
//						System.arraycopy(responseBuffer.array(), dp.beginIndex, chunkData,0,chunkData.length);
//						if(dp.length > chunkData.length){
//							dp.length = dp.length - chunkData.length;
//							baos.write(chunkData);
//							//重新获取网络数据
//							continue;
//						}else{
//							baos.write(chunkData,0,dp.length);
//							i = 0 + dp.length ;
//							if(chunkData[i]=='\r'){
//								i = i+1;
//							}
//							if(chunkData[i]=='\n'){
//								i = i+1;
//							}
//							dp.beginIndex = i;
//							dp.length = 0;
//							while(i<chunkData.length){
//								//分段数据，是什么格式？
//								if(dp.prev=='\r' && chunkData[i] == '\n'){
//									dp.prev = chunkData[i];
//									dp.length = StringHex.toInt(new String(chunkData,dp.beginIndex,i-dp.beginIndex-1), StringHex.TYPE_HEX);
//									logger.info("dp.length:{}",dp.length);
//									if(dp.length == 0){
//										dp.isEnd = true;
//										break;
//									}
//									i = i + 1;
//									dp.beginIndex = i;
//									if(chunkData.length - dp.beginIndex > dp.length){
//										baos.write(chunkData,dp.beginIndex,dp.length);
//										i = dp.beginIndex + dp.length + 2;
//										if(i >= chunkData.length){
//											dp.length = 0;
//											dp.beginIndex = i - chunkData.length;
//											break;
//										}
//										dp.beginIndex = i;
//										continue;
//									}else{
//										baos.write(chunkData,dp.beginIndex,chunkData.length-dp.beginIndex);
//										dp.length = dp.length - (chunkData.length-dp.beginIndex);
//										dp.beginIndex = 0;
//										break;
//									}
//								}
//								dp.prev = chunkData[i];
//								i = i + 1;
//							}
//						}
//					}
//				//非分段处理
//				}else{
//					//取长度，根据长度取数据
//					//数据取出来，解码
//					if(dp.length == 0){
//						dp.length = response.getLength();
//					}
//					byte[] data = new byte[responseBuffer.limit()];
//					System.arraycopy(responseBuffer.array(), 0, data, 0, responseBuffer.limit());
//					baos.write(data);
//					dp.length = dp.length - responseBuffer.limit();
//					if(dp.length <=0 ){
//						break;
//					}
//				}
//				if(dp.isEnd){
//					break;
//				}else{
//					continue;
//				}
//			}
//			
			
			
//			while((dp.len = sc.read(responseBuffer)) > 0 ){
//				responseBuffer.flip();
//				if(dp.hasSpace){
//					//处理数据
//					//分段处理
//					if(response.isChunked()){
//						//取长度，根据长度取数据
//						//数据取出来，根据编码解码
//						//存在剩余内容
//						if(surplus != null && dp.length == 0){
//							int i = 1;
//							dp.beginIndex = 0;
//							while(i<surplus.length){
//								if(dp.prev == '\r' && surplus[i] == '\n'){
//									dp.prev = surplus[i];
//									dp.length = StringHex.toInt(new String(surplus,dp.beginIndex,i-1), StringHex.TYPE_HEX);
//									dp.beginIndex = i + 1;
//									if(dp.length == 0){
//										//分段数组为0，表示结束
//										dp.isEnd = true;
//										break;
//									}
//									i = i + 1;
//									if(surplus.length - i >= dp.length){
//										baos.write(surplus,i,dp.length);
//										i = i + dp.length + 2 ;
//										if(i >= surplus.length){
//											dp.length = 0;
//											dp.beginIndex = 0;
//											surplus = null;
//											break;
//										}
//										dp.beginIndex = i;
//										//继续读取剩余数组数据
//										continue;
//									}else{
//										baos.write(surplus,i,surplus.length-i);
//										dp.length = dp.length - (surplus.length-i);
//										//停止读取剩余数组数据
//										surplus = null;
//										break;
//									}
//								}
//								dp.prev = surplus[i];
//								i = i + 1;
//							}
//						}
//						if(surplus == null){
//							int i = 0;
//							byte[] chunkData = new byte[responseBuffer.limit() - dp.beginIndex];
//							System.arraycopy(responseBuffer.array(), dp.beginIndex, chunkData,0,chunkData.length);
//							if(dp.length > chunkData.length){
//								dp.length = dp.length - chunkData.length;
//								baos.write(chunkData);
//								//重新获取网络数据
//								continue;
//							}else{
//								baos.write(chunkData,0,dp.length);
//								i = 0 + dp.length ;
//								if(chunkData[i]=='\r'){
//									i = i+1;
//								}
//								if(chunkData[i]=='\n'){
//									i = i+1;
//								}
//								dp.beginIndex = i;
//								dp.length = 0;
//								while(i<chunkData.length){
//									//分段数据，是什么格式？
//									if(dp.prev=='\r' && chunkData[i] == '\n'){
//										dp.prev = chunkData[i];
//										dp.length = StringHex.toInt(new String(chunkData,dp.beginIndex,i-dp.beginIndex-1), StringHex.TYPE_HEX);
//										logger.info("dp.length:{}",dp.length);
//										if(dp.length == 0){
//											dp.isEnd = true;
//											break;
//										}
//										i = i + 1;
//										dp.beginIndex = i;
//										if(chunkData.length - dp.beginIndex > dp.length){
//											baos.write(chunkData,dp.beginIndex,dp.length);
//											i = dp.beginIndex + dp.length + 2;
//											if(i >= chunkData.length){
//												dp.length = 0;
//												dp.beginIndex = i - chunkData.length;
//												break;
//											}
//											dp.beginIndex = i;
//											continue;
//										}else{
//											baos.write(chunkData,dp.beginIndex,chunkData.length-dp.beginIndex);
//											dp.length = dp.length - (chunkData.length-dp.beginIndex);
//											dp.beginIndex = 0;
//											break;
//										}
//									}
//									dp.prev = chunkData[i];
//									i = i + 1;
//								}
//							}
//						}
//					//非分段处理
//					}else{
//						//取长度，根据长度取数据
//						//数据取出来，解码
//						if(dp.length == 0){
//							dp.length = response.getLength();
//						}
//						byte[] data = new byte[responseBuffer.limit()];
//						System.arraycopy(responseBuffer.array(), 0, data, 0, responseBuffer.limit());
//						baos.write(data);
//						dp.length = dp.length - responseBuffer.limit();
//						if(dp.length <=0 ){
//							break;
//						}
//					}
//					if(dp.isEnd){
//						break;
//					}else{
//						continue;
//					}
//				}
//				
//				//获取request头部数据，将解析剩下的数据返回
//				for(int i=0;i<dp.len;i = i+1){
//					//空行之前，http请求的头信息
//					if(i < dp.len - 1 && buf[i]=='\r' && buf[i+1]=='\n' && !dp.hasSpace){
//						int tmpLen = i-dp.beginIndex;
//						if(tmpLen == 0){
//							dp.hasSpace = true;
//						}else{
//							if(header == null){
//								header = new HashMap<String,String>();
//								response.setHttpStatus(new String(buf,9,3));
//							}else{
//								for(int b = 0;b < tmpLen;b = b + 1){
//									int index = b+dp.beginIndex;
//									if(buf[index]==':'){
//										header.put(new String(buf,dp.beginIndex,b)
//												, new String(buf,index+2,tmpLen- b - 2));
//										break;
//									}
//								}
//								String result = new String(buf,dp.beginIndex,tmpLen);
//								logger.info(result);
//							}
//						}
//						i = i + 1;
//						dp.beginIndex = i+1;
//						//出现空行
//						if(i < dp.len - 2 && buf[i+1] =='\r' && buf[i+2]=='\n'){
//							dp.hasSpace = true;
//							i = i+2;
//							dp.prev = buf[i];
//							dp.beginIndex = i+1;
//							//解析出需要的报文信息
//							//是否分段
//							if(header.get("Transfer-Encoding") != null){
//								response.setChunked("chunked".equals(header.get("Transfer-Encoding")));
//							}
//							//获取传输编码格式
//							String contentType = header.get("Content-Type");
//							String[] tmps = contentType.split("; ");
//							for(int c = 1;c<tmps.length;c++){
//								String[] charset = tmps[c].split("=");
//								if("charset".equals(charset[0])){
//									response.setCharset(charset[1]);
//									break;
//								}
//							}
//							//用于判断是否是gzip格式传输
//							response.setEncoding(header.get("Content-Encoding"));
//							//分段传输时，无长度
//							if(header.get("Content-Length")!=null){
//								response.setLength(Integer.parseInt(header.get("Content-Length").trim()));
//							}
//							response.setHeader(header);
//							
//							//剩余数据，需要交给下一环节处理
//							if(dp.beginIndex < dp.len){
//								surplus = new byte[dp.len-dp.beginIndex];
//								System.arraycopy(buf, dp.beginIndex, surplus, 0, surplus.length);
//							}
//							break;
//						}
//					}
//				}
//				
//			}
//			ByteArrayInputStream bais  = new ByteArrayInputStream(baos.toByteArray());
//			GZIPInputStream gis = new GZIPInputStream(bais);
//			byte[] bufs = new byte[2048];
//			int len = 0;
//			while((len = gis.read(bufs))!=-1 ){
//				logger.info("output:{}",new String(bufs,0,len,"UTF-8"));
//			}
			
			//一个浏览器一个时间，一个状态
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseHTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
