package com.renke.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		String url = "http://www.shuqi6.com/2486/";
		String msg = "";
		ParseBook pb = new ParseBookShuqi6("奥术神座", url);
		try {
			long b = System.currentTimeMillis();
			Controller control = new Controller(url,msg);
			HTTP http = control.readData(url, msg);
			List<Map<String,String>> urlList = pb.readCatalog(http.getBytes());
			List<Map<String,String>> errorList = new SyncArrayList<Map<String,String>>();
			logger.info("size:{}",urlList.size());
			for(Map<String,String> map : urlList){
				String href = map.get(ParseBook.CHAPTER_HREF);
				http = control.readData(pb.getCatalogPath()+href+".html",msg);
				if(http.getBytes() == null || http.getBytes().length <=0){
					control = new Controller(url,msg);
					http = control.readData(url+href+".html",msg);
					logger.error("reset {}" , href);
				}
				if(http.getBytes() == null || http.getBytes().length <=0){
					errorList.add(map);
					continue;
				}
				int i = pb.writeBookByBytes(http.getBytes(),map.get(ParseBook.CHAPTER_TITLE), href);
				logger.info("{} length:{} result:{}",href,http.getBytes().length+"",i+"");
			}
			while(errorList.size()>0){
				Map<String,String> map = errorList.remove(0);
				String href = map.get(ParseBook.CHAPTER_HREF);
				http = control.readData(url+href+".html",msg);
				if(http.getBytes() == null || http.getBytes().length <=0){
					control = new Controller(url,msg);
					http = control.readData(url+href+".html",msg);
					logger.error("reset {}" , href);
				}
				if(http.getBytes() == null || http.getBytes().length <=0){
					errorList.add(map);
					continue;
				}
				int i = pb.writeBookByBytes(http.getBytes(),map.get(ParseBook.CHAPTER_TITLE), href);
				logger.info("{} length:{} result:{}",href,http.getBytes().length+"",i+"");
			}
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
		ParseBook pb = new ParseBookShuqi6(bookName, url);
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
			mergeBook(pb.getSavePath(), urlList);
			logger.info("parse time : {}ms" , System.currentTimeMillis() - b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mergeBook(String filePath,List<Map<String,String>> dir) throws IOException{
		File file = new File(filePath+".txt");
		OutputStream os = new FileOutputStream(file);
		byte[] buf = new byte[8096];
		while(dir.size()>0){
			Map<String,String> map = dir.remove(0);
			String file_name = map.get(ParseBook.CHAPTER_HREF);
			InputStream is = new FileInputStream(new File(filePath+"/"+file_name));
			int len = 0;
			while( (len = is.read(buf)) >=0){
				os.write(buf, 0, len);
			}
			is.close();
		}
		os.flush();
		os.close();
	}
}
