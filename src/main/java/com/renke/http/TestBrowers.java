package com.renke.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBrowers{
	final static Logger logger = LoggerFactory.getLogger(TestBrowers.class);
//	@Test
	public void readUrl(){
		String url = "";
		url = "http://www.23wx.com/html/55/55035/";
		url = "http://www.23wx.com/html/58/58901/";
		String msg = "";
		try {
			long b = System.currentTimeMillis();
			Controller control = new Controller(url,msg);
			HTTP http = control.readData(url, msg);
			DownloadUtil.writeToFile(http.getBytes(), "F:/ebook/58901.txt");
			control.close();
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@Test
	public void readUrlByThread(){
		String url = "http://www.shuqi6.com/40070/";
		String bookName = "贞观大闲人";
		String msg = "";
//		ParseBook pb = new ParseBookShuqi6(bookName, url);
		ParseBook pb = new ParseBook(bookName, url,"shuqi6");
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		try {
			long b = System.currentTimeMillis();
			Controller control = new Controller(url,msg);
			HTTP http = control.readData(url, msg);
			List<Map<String,String>> urlList = pb.readCatalog(http.getBytes());
			List<Map<String,String>> limitList = new ArrayList<>();
			for(int i=0;i<urlList.size();i = i+1){
				limitList.add(urlList.get(i));
				if(i%50 == 0 ){
					Download download = new Download(pb,limitList);
					tpe.execute(download);
					limitList = new ArrayList<>();
				}
			}
			Download download = new Download(pb,limitList);
			tpe.execute(download);
			limitList = null;
			logger.info("running");
			while(tpe.getActiveCount() > 0){
				
			}
			logger.info("down");
			DownloadUtil.mergeBook(pb.getSavePath(), urlList);
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestBrowers tb = new TestBrowers();
		tb.readUrl();
	}
	
}
