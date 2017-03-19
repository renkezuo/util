package com.renke.ebook.pojo;

import java.util.ArrayList;
import java.util.List;

public class WebSite {
	public String sourceEncoding = "GBK";
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
//		catalog_start = bookName+"���ߣ�";
//		catalog_end = bookName+"������Ҫͨ��";
//		content_start = "show_share";
//		content_end = "show_htm2";
//		href_start = "href";
//		href_end = "\"";
//		title_start = ">";
//		title_end = "<";
//	}
//	
//	public void initShuqi6(){
//		catalog_start = bookName+"�޵��������½��б�";
//		catalog_end = "����С˵ͬ���޵����Ķ��Ƽ�";
//		content_start = bookName + "ȫ���Ķ�";
//		content_end = bookName + "�ֻ��Ķ�";
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
