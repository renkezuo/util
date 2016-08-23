package com.renke.util.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.renke.exception.ParseHTTPException;

/***
 * 
 * �������Ҫ��url
 * 	���ܲ�֣���url��request����ת��Ϊ�ֽ�����
 * ����HTML
 *  ���ܲ�֣��ӷ��ص��ֽ������У�����responseͷ���ĺ�body���飬��body����ת��Ϊ�ı�
 * ����Ϊ���
 * 
 * ��һ��URL��������������ȥ������󣬲����أ���������N secs�����û�к���������ر�����
 * 
 * 
 * 
 * @author renke.zuo@foxmail.com
 * @time 2016-08-15 10:03:22
 */
public class BrowserBackup {
//	private static final Logger logger = LoggerFactory.getLogger(Browser.class);
	
	long responseTime;//��λms
	long keepAlive = 0;//��������ʱ�䣬<0�������֣���λms
	int status = 0;//0��ʼ״̬��1��������ʱ����
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
			//������غ�����һ��ʱ�䣬ͬʱ����һ��״̬��
			ByteBuffer responseBuffer = ByteBuffer.wrap(new byte[2048]);
			//��ͷ����
//			int beginIndex = 0;
			//��ȡ���ݳ���
//			int len = 0;
			//�����Ƿ����
//			boolean hasSpace = false;
//			boolean isEnd = false;
//			int length = 0;
			Response response = new Response();
//			Map<String,String> header = null;
//			byte[] surplus = null;
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//��ȡresponse����
			//�����ж�1�Ƿ��Ѿ�����Header���ݣ�ʹ��hasSpace�ж�
			//  1��ȡheader����
					//�����߼���
					//	��һ��Ϊhttp״̬��Ϣ��
					//	������ÿ����ð�ŷָ�Ϊkey��value
					//	������һ������ʱ������key��value����������Ҫ�õ���ͷ��Ϣ������response
					//	����header��������ʣ������[����������]��������
			//  1�ǣ��ж�2�Ƿ�ֶ�ȡ��
			//		2�ǣ��ж�3ʣ�������Ƿ���ڣ���Ϻ󣬼����ж�4ʣ�������Ƿ������
			//			3�ǣ�ȡ���ȣ���ʣ�����ݲ���ʱ����ʣ������ȫ��ȡ����ͬʱ�ٴ�ȥ��������[��¼��Ӧ������״̬]����ʵ����״̬������Ҫ��
			//			4�ǣ�
			//		2��ֱ��ȡ���ȣ���ȡ��data[����]��break;
			//beginIndex����ʼ����
			//len��ÿ��read���������ݳ���
			//length����������Լ���ĳ���[�ֶλ򲻷ֶ�]
			//hasSpace���ж��Ƿ��ȡ��header��Ϣ
			//isEnd���ж������Ƿ�ȫ����ȡ���
			//prev����ʾ��һ���ֽڣ�һ�������жϻ���ʱʹ��
			
			//����response Header��Ϣ������ʣ����������
			byte[] surplus = ParseHTTP.parseResponseHeader(sc,response,responseBuffer);
			ParseHTTP.parseResponseData(sc, surplus, response, responseBuffer);
//			//����ʣ������
//			while((dp.len = sc.read(responseBuffer)) > 0 ){
//				responseBuffer.flip();
//				if(response.isChunked()){
//					//ȡ���ȣ����ݳ���ȡ����
//					//����ȡ���������ݱ������
//					//����ʣ������
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
//									//�ֶ�����Ϊ0����ʾ����
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
//									//������ȡʣ����������
//									continue;
//								}else{
//									baos.write(surplus,i,surplus.length-i);
//									dp.length = dp.length - (surplus.length-i);
//									//ֹͣ��ȡʣ����������
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
//							//���»�ȡ��������
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
//								//�ֶ����ݣ���ʲô��ʽ��
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
//				//�Ƿֶδ���
//				}else{
//					//ȡ���ȣ����ݳ���ȡ����
//					//����ȡ����������
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
//					//��������
//					//�ֶδ���
//					if(response.isChunked()){
//						//ȡ���ȣ����ݳ���ȡ����
//						//����ȡ���������ݱ������
//						//����ʣ������
//						if(surplus != null && dp.length == 0){
//							int i = 1;
//							dp.beginIndex = 0;
//							while(i<surplus.length){
//								if(dp.prev == '\r' && surplus[i] == '\n'){
//									dp.prev = surplus[i];
//									dp.length = StringHex.toInt(new String(surplus,dp.beginIndex,i-1), StringHex.TYPE_HEX);
//									dp.beginIndex = i + 1;
//									if(dp.length == 0){
//										//�ֶ�����Ϊ0����ʾ����
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
//										//������ȡʣ����������
//										continue;
//									}else{
//										baos.write(surplus,i,surplus.length-i);
//										dp.length = dp.length - (surplus.length-i);
//										//ֹͣ��ȡʣ����������
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
//								//���»�ȡ��������
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
//									//�ֶ����ݣ���ʲô��ʽ��
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
//					//�Ƿֶδ���
//					}else{
//						//ȡ���ȣ����ݳ���ȡ����
//						//����ȡ����������
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
//				//��ȡrequestͷ�����ݣ�������ʣ�µ����ݷ���
//				for(int i=0;i<dp.len;i = i+1){
//					//����֮ǰ��http�����ͷ��Ϣ
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
//						//���ֿ���
//						if(i < dp.len - 2 && buf[i+1] =='\r' && buf[i+2]=='\n'){
//							dp.hasSpace = true;
//							i = i+2;
//							dp.prev = buf[i];
//							dp.beginIndex = i+1;
//							//��������Ҫ�ı�����Ϣ
//							//�Ƿ�ֶ�
//							if(header.get("Transfer-Encoding") != null){
//								response.setChunked("chunked".equals(header.get("Transfer-Encoding")));
//							}
//							//��ȡ��������ʽ
//							String contentType = header.get("Content-Type");
//							String[] tmps = contentType.split("; ");
//							for(int c = 1;c<tmps.length;c++){
//								String[] charset = tmps[c].split("=");
//								if("charset".equals(charset[0])){
//									response.setCharset(charset[1]);
//									break;
//								}
//							}
//							//�����ж��Ƿ���gzip��ʽ����
//							response.setEncoding(header.get("Content-Encoding"));
//							//�ֶδ���ʱ���޳���
//							if(header.get("Content-Length")!=null){
//								response.setLength(Integer.parseInt(header.get("Content-Length").trim()));
//							}
//							response.setHeader(header);
//							
//							//ʣ�����ݣ���Ҫ������һ���ڴ���
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
			
			//һ�������һ��ʱ�䣬һ��״̬
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
