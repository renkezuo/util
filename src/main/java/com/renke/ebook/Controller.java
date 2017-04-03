package com.renke.ebook;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.ebook.pojo.HTTP;
import com.renke.ebook.pojo.Section;
import com.renke.ebook.pojo.WebSite;
public class Controller {

	final static Logger logger = LoggerFactory.getLogger(Controller.class);
	
	/**
	 * ��ȡurl���ݣ�д�뵽һ���ļ�
	 * @param url
	 * @param filePath
	 */
	public static void readUrlData(String url, String filePath){
		try {
			long b = System.currentTimeMillis();
			ConnectManager manager = new ConnectManager(url);
			manager.readData(manager.getHTTP());
			writeToFile(manager.getHTTP().getBytes(), filePath);
			manager.close();
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���Զ�ȡĿ¼����
	 * @param http
	 * @param ws
	 * @throws UnknownHostException 
	 */
	public static void readCatalogToList(WebSite ws){
		ConnectManager manager = new ConnectManager(ws.catalogUrl);
		HTTP http = manager.getHTTP();
		List<Section> urlList = new ArrayList<>();
		try {
			manager.readData(http);
			urlList = BookParser.readCatalog(http.getBytes(),ws);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("size:{}",urlList.size());
		for(Section s : urlList){
			logger.info("href:{}",s.getUrl());
			logger.info("title:{}",s.getName());
		}
		manager.close();
	}
	
	/**
	 * TODO:����ϲ��½ڵķ���
	 * TODO:�Ƿ���Խ���ѯ���ܷŽ���
	 * TODO:�Ż�[���Բ���]
	 * 
	 * ʹ�ö��߳�ץȡС˵
	 * 
	 * @author renke.zuo@foxmail.com
	 * @time 2016-09-13 10:11:58
	 */
	public static void grabEbookByThread(WebSite ws){
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		try {
			long b = System.currentTimeMillis();
			ConnectManager manager = new ConnectManager(ws.catalogUrl);
			HTTP http = manager.getHTTP();
			manager.readData(http);
			List<Section> urlList = BookParser.readCatalog(http.getBytes(),ws);
			List<Section> limitList = new ArrayList<Section>();
			int limit = urlList.size()/20;
			for(int i=0;i<urlList.size();i = i+1){
				limitList.add(urlList.get(i));
				if(i%limit == 0 ){
					Executor download = new Executor(ws,limitList);
					tpe.execute(download);
					limitList = new ArrayList<>();
				}
//				break;
			}
			Executor download = new Executor(ws,limitList);
			tpe.execute(download);
			limitList = null;
			logger.info("running");
			while(tpe.getActiveCount() > 0){
				
			}
			tpe.shutdown();
			logger.info("down");
			mergeBook(ws, urlList);
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ϲ��½ڣ���װΪȫ��
	 * @param ws
	 * @param dir
	 * @throws IOException
	 */
	public static void mergeBook(WebSite ws,List<Section> dir) throws IOException{
		File file = new File(ws.savePath+".txt");
		OutputStream os = new FileOutputStream(file);
		byte[] buf = new byte[8096];
		while(dir.size()>0){
			Section s = dir.remove(0);
			String file_name = s.getUrl();
			InputStream is = new FileInputStream(new File(ws.savePath+"/"+file_name));
			int len = 0;
			while( (len = is.read(buf)) >=0){
				os.write(buf, 0, len);
			}
			is.close();
		}
		os.flush();
		os.close();
	}
	
	/**
	 * �ϲ�Ŀ¼�е��ļ�����װ��savePath�ļ���
	 * @param ws
	 * @param dir
	 * @throws IOException
	 */
	public static void mergeBook(String savePath,String dir) throws IOException{
		File file = new File(savePath);
		File[] files = new File(dir).listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});
		OutputStream os = new FileOutputStream(file);
		byte[] buf = new byte[8096];
		for(File single : files){
			InputStream is = new FileInputStream(single);
			int len = 0;
			while( (len = is.read(buf)) >=0){
				os.write(buf, 0, len);
			}
			is.close();
		}
		os.flush();
		os.close();
	}
	
	/**
	 * ���ֽ�д���ļ�
	 * @param bytes
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeToFile(byte[] bytes , String filePath) throws IOException{
		File file = new File(filePath);
		OutputStream os = new FileOutputStream(file);
		os.write(bytes);
		os.close();
	}
	
	public static void main(String[] args) {
		String url = "http://www.xs222.com/html/0/612/";
		Map<String,String> siteProperties = new HashMap<String,String>();
		siteProperties.put("bookName", "����֮��");
		siteProperties.put("catalogUrl", url);
		siteProperties.put("basePath", "G:/ebook/");
//		WebSiteFactory.setQu(siteProperties);
		WebSiteFactory.setXs222(siteProperties);
		WebSite ws = WebSiteFactory.getWebSite(siteProperties);
//		Controller.grabEbookByThread(ws);
		readCatalogToList(ws);
//		readUrlData(url,"G:\\ebook\\test.txt");
	}
}
