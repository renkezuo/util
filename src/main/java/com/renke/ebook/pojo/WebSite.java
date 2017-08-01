package com.renke.ebook.pojo;

import java.util.ArrayList;
import java.util.List;

public class WebSite {
	public String sourceEncoding = "UTF-8";
	public String saveEncoding = "UTF-8";
	public String basePath = "G:\\ebook\\";
	public String siteName ;
	public String catalogUrl;
	public String savePath = "";
	public List<Section> sections = new ArrayList<Section>();
	public String bookName = "";
	public String hrefStart = "href";
	public String hrefEnd = "\"";
	public String titleStart = ">";
	public String titleEnd = "<";
	public String catalogStart = "";
	public String catalogEnd = "";
	public String contentStart =  "";
	public String contentEnd =  "";
	public WebSite(){}
	
//	public WebSite(String bookName,String catalogUrl,String siteName){
//		this.bookName = bookName;
//		this.catalogUrl = catalogUrl;
//		if("23wx".equals(siteName.toLowerCase()))
//			init23WX();
//		else if("shuqi6".equals(siteName.toLowerCase()))
//			initShuqi6();
//		else if("qu".equals(siteName.toLowerCase()))
//			initQu();
//	}
//	public void init23WX(){
//		catalog_start = bookName+"作者：";
//		catalog_end = bookName+"更新重要通告";
//		content_start = "show_share";
//		content_end = "show_htm2";
//		href_start = "href";
//		href_end = "\"";
//		title_start = ">";
//		title_end = "<";
//	}
//	
//	public void initShuqi6(){
//		catalog_start = bookName+"无弹窗最新章节列表";
//		catalog_end = "书旗小说同类无弹窗阅读推荐";
//		content_start = bookName + "全文阅读";
//		content_end = bookName + "手机阅读";
//		href_start = "href";
//		href_end = "\"";
//		title_start = "title=\"";
//		title_end = "\"";
//	}
//	
//	public void initQu(){
//		catalog_start = "<dl>";
//		catalog_end = "</dl>";
//		content_start = "read2";
//		content_end = "read3";
//		href_start = "href";
//		href_end = "\"";
//		title_start = ">";
//		title_end = "<";
//	}
}
