package com.renke.ebook;

import java.io.File;
import java.util.Map;

import com.renke.ebook.pojo.WebSite;

public class WebSiteFactory {
	public static WebSite getWebSite(Map<String,String> siteProperties){
		WebSite ws = new WebSite();
		ws.bookName = siteProperties.get("bookName");
		ws.siteName = siteProperties.get("siteName");
		ws.catalogUrl = siteProperties.get("catalogUrl");
		ws.catalogStart = siteProperties.get("catalogStart");
		ws.catalogEnd = siteProperties.get("catalogEnd");
		ws.contentStart = siteProperties.get("contentStart");
		ws.contentEnd = siteProperties.get("contentEnd");
		if(siteProperties.get("basePath") != null) ws.basePath = siteProperties.get("basePath");
		if(siteProperties.get("hrefStart") != null) ws.hrefStart = siteProperties.get("hrefStart");
		if(siteProperties.get("hrefEnd") != null) ws.hrefEnd = siteProperties.get("hrefEnd");
		if(siteProperties.get("titleStart") != null) ws.titleStart = siteProperties.get("titleStart");
		if(siteProperties.get("titleEnd") != null) ws.titleEnd = siteProperties.get("titleEnd");
		if(siteProperties.get("sourceEncoding") != null) ws.sourceEncoding = siteProperties.get("sourceEncoding");
		if(siteProperties.get("saveEncoding") != null) ws.saveEncoding = siteProperties.get("saveEncoding");
		ws.savePath = ws.basePath + ws.siteName + File.separator + ws.bookName;
		return ws;
	}
	
	public static void setQu(Map<String,String> siteProperties){
		siteProperties.put("siteName", "qu");
		siteProperties.put("catalogStart", "<dl>");
		siteProperties.put("catalogEnd", "</dl>");
		siteProperties.put("contentStart", "read2");
		siteProperties.put("contentEnd", "chaptererror");
		siteProperties.put("hrefStart", null);
		siteProperties.put("hrefEnd", null);
		siteProperties.put("titleStart", null);
		siteProperties.put("titleEnd", null);
	}
	
	public static void setXs222(Map<String,String> siteProperties){
		siteProperties.put("siteName", "xs222");
		siteProperties.put("catalogStart", "</dt>");
		siteProperties.put("catalogEnd", "</dl>");
		siteProperties.put("contentStart", "read2");
		siteProperties.put("contentEnd", "read3");
		siteProperties.put("hrefStart", null);
		siteProperties.put("hrefEnd", null);
		siteProperties.put("titleStart", null);
		siteProperties.put("titleEnd", null);
	}
}
