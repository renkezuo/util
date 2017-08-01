package com.renke.util.Browers;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 解析书籍数据
 * @author renke
 */
public interface ParseBook {
	public String bookName = "";
	public String catalog_path = "";
	
	public String SAVEPATH = "G:\\ebook\\";
	public String CATALOG_START = "";
	public String CATALOG_END = "";
	public String CONTENT_START =  "";
	public String CONTENT_END =  "";
	
	public String CHAPTER_HREF = "href";
	public String CHAPTER_TITLE = "title";
	public String SOURCE_ENCODING = "GBK";
	public String SAVE_ENCODING = "UTF-8";
	/**
	 * 获取目录
	 * @param html
	 * @return
	 * @throws IOException
	 */
	public List<Map<String,String>> readCatalog(byte[] bytes) throws IOException;
	/***
	 * 将文章信息写入savePath
	 * @param html
	 * @param title
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	public int writeBookByBytes(byte[] bytes,String title,String index) throws IOException;
	/***
	 * 将文章信息写入savePath
	 * @param html
	 * @param title
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	public int writeBookByOutputStream(OutputStream os,String title,String index) throws IOException;
	
	public String getStartPath();
	
	public String getIndex(String href);
	
	public String getBookName();
	public String getCatalogPath();
	public String getSavePath();
	public String getCatalogStart();
	public String getCatalogEnd();
	public String getContentStart();
	public String getContentEnd();
	
	
}
