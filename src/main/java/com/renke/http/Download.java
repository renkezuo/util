package com.renke.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Download implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(Download.class);
	private Controller control;
	private ParseBook pb;
	private List<Map<String,String>> urlList;
	private List<Map<String,String>> errorList = new ArrayList<Map<String,String>>();
	public Download(ParseBook pb,List<Map<String,String>> urlList){
		this.urlList = urlList;
		this.pb = pb;
		control = new Controller(pb.CATALOG_PATH,"");
	}

	@Override
	public void run() {
		for(Map<String,String> map : urlList){
			download(map);
		}
		while(errorList.size() > 0 ){
			Map<String,String> map = errorList.remove(0);
			download(map);
		}
		control.close();
	}
	
	public void download(Map<String,String> map){
		String href = map.get(ParseBook.CHAPTER_HREF);
		try{
			HTTP http = control.readData(pb.CATALOG_PATH+href,"");
			if(http.getBytes() == null || http.getBytes().length <=0){
				control = new Controller(pb.CATALOG_PATH,"");
				http = control.readData(pb.CATALOG_PATH+href,"");
				logger.error("reset {}" , href);
			}
			if(http.getBytes() == null || http.getBytes().length <=0){
				errorList.add(map);
				return ;
			}
			int i = pb.writeBookByBytes(http.getBytes(),map.get(ParseBook.CHAPTER_TITLE), href);
			logger.info("{} length:{} result:{}",href,http.getBytes().length+"",i+"");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("reset {}" , href);
			errorList.add(map);
		}
	}

}
