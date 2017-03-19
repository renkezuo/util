package com.renke.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBrowers{
	final static Logger logger = LoggerFactory.getLogger(TestBrowers.class);
	public void readUrlData(String url){
		String msg = "";
		try {
			long b = System.currentTimeMillis();
			Controller control = new Controller(url,msg);
			HTTP http = control.readData(url, msg);
			DownloadUtil.writeToFile(http.getBytes(), ParseBook.BASE_PATH+"test.txt");
			control.close();
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void readCatalogToList(String url ,String bookName,String siteName){
		String msg = "";
		ParseBook pb = new ParseBook(bookName, url,siteName);
		Controller control = new Controller(url,msg);
		List<Map<String,String>> urlList = new ArrayList<>();
		try {
			HTTP http = control.readData(url, msg);
			urlList = pb.readCatalog(http.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("size:{}",urlList.size());
		for(Map<String,String> map : urlList){
			logger.info("href:{}",map.get("href"));
			logger.info("title:{}",map.get("title"));
		}
//		control.close();
	}
	
	/**
	 * TODO:加入合并章节的方法
	 * TODO:是否可以将查询功能放进来
	 * TODO:其它网站读取www.23wx.com www.biquge.com  www.booktxt.com
	 * TODO:优化[可以不做]
	 * 
	 * @author renke.zuo@foxmail.com
	 * @time 2016-09-13 10:11:58
	 */
	public void readUrlByThread(String url ,String bookName,String siteName){
		String msg = "";
		ParseBook pb = new ParseBook(bookName, url,siteName);
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		try {
			long b = System.currentTimeMillis();
			Controller control = new Controller(url,msg);
			HTTP http = control.readData(url, msg);
			List<Map<String,String>> urlList = pb.readCatalog(http.getBytes());
			List<Map<String,String>> limitList = new ArrayList<>();
			for(int i=0;i<urlList.size();i = i+1){
				limitList.add(urlList.get(i));
				if(i%200 == 0 ){
					Download download = new Download(pb,limitList);
					tpe.execute(download);
					limitList = new ArrayList<>();
				}
//				break;
			}
			Download download = new Download(pb,limitList);
			tpe.execute(download);
			limitList = null;
			logger.info("running");
			while(tpe.getActiveCount() > 0){
				
			}
			tpe.shutdown();
			logger.info("down");
			DownloadUtil.mergeBook(pb.getSavePath(), urlList);
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String url = "http://www.qu.la/book/156/";
		TestBrowers tb = new TestBrowers();
		
//		tb.readUrlData(url);
//		tb.readCatalogToList(url,"颤栗世界","dhzw");
		tb.readUrlByThread(url,"惊悚乐园","qu");
	}
	
}
