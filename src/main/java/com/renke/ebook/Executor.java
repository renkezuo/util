package com.renke.ebook;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.ebook.pojo.HTTP;
import com.renke.ebook.pojo.Section;
import com.renke.ebook.pojo.WebSite;

public class Executor implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(Executor.class);
	private ConnectManager manager;
	private WebSite ws;
	private List<Section> urlList;
	private List<Section> errorList = new ArrayList<Section>();
	public Executor(WebSite ws,List<Section> urlList){
		this.urlList = urlList;
		this.ws = ws;
		manager = new ConnectManager(ws.catalogUrl);
	}

	@Override
	public void run() {
		for(Section s : urlList){
			download(s);
		}
		while(errorList.size() > 0 ){
			Section s = errorList.remove(0);
			download(s);
		}
		manager.close();
	}
	
	public void download(Section s){
		String href = s.getUrl();
		try{
			HTTP http = manager.getHTTP(ws.catalogUrl + "/" +href);
			manager.readData(http);
			if(http.getBytes() == null || http.getBytes().length <=0){
				manager = new ConnectManager(http.getIp(),http.getPort());
				manager.readData(http);
				logger.error("reset {}" , href);
			}
			if(http.getBytes() == null || http.getBytes().length <=0){
				errorList.add(s);
				return ;
			}
			int i = BookParser.writeSectionByBytes(http.getBytes(),s, ws);
			logger.info("{} length:{} result:{}",href,http.getBytes().length+"",i+"");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("reset {}" , href);
			errorList.add(s);
		}
	}

}
