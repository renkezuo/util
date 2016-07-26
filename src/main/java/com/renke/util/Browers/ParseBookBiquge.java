package com.renke.util.Browers;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ParseBookBiquge implements ParseBook{
	public String BOOKNAME = "奥术神座";
	public String CATALOG_PATH = "http://m.biquge.la/booklist/197.html";
	
	public String SAVEPATH = "G:\\ebook\\"+BOOKNAME;
	public String CATALOG_START = BOOKNAME+"无弹窗最新章节列表";
	public String CATALOG_END = "书旗小说同类无弹窗阅读推荐";
	public String CONTENT_START = BOOKNAME + "全文阅读";
	public String CONTENT_END = BOOKNAME + "手机阅读";
	@Override
	public List<Map<String, String>> readCatalog(byte[] bytes)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int writeBookByBytes(byte[] bytes, String title, String index)
			throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int writeBookByOutputStream(OutputStream os, String title,
			String index) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStartPath() {
		return CATALOG_PATH.substring(0,CATALOG_PATH.lastIndexOf("/booklist"));
	}

	@Override
	public String getIndex(String href) {
		// TODO Auto-generated method stub
		return href.substring(href.lastIndexOf("/")+1);
	}

	@Override
	public String getBookName() {
		// TODO Auto-generated method stub
		return BOOKNAME;
	}

	@Override
	public String getCatalogPath() {
		// TODO Auto-generated method stub
		return CATALOG_PATH;
	}

	@Override
	public String getSavePath() {
		// TODO Auto-generated method stub
		return SAVEPATH;
	}

	@Override
	public String getCatalogStart() {
		// TODO Auto-generated method stub
		return CATALOG_START;
	}

	@Override
	public String getCatalogEnd() {
		// TODO Auto-generated method stub
		return CATALOG_END;
	}

	@Override
	public String getContentStart() {
		// TODO Auto-generated method stub
		return CONTENT_START;
	}

	@Override
	public String getContentEnd() {
		// TODO Auto-generated method stub
		return CONTENT_END;
	}
}
