package com.renke.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseBook {
	public static final String CHAPTER_HREF = "href";
	public static final String CHAPTER_TITLE = "title";
	public static final String SOURCE_ENCODING = "GBK";
	public static final String SAVE_ENCODING = "UTF-8";
	public String SAVEPATH = "F:\\ebook\\shuqi6\\";
	public String BOOKNAME = "奥术神座";
	public String CATALOG_PATH = "http://www.shuqi6.com/2486/";
	public String HREF_START = "";
	public String HREF_END = "";
	public String TITLE_START = "";
	public String TITLE_END = "";
	public String CATALOG_START = "";
	public String CATALOG_END = "";
	public String CONTENT_START =  "";
	public String CONTENT_END =  "";
	
	public ParseBook(String bookName,String catalog_path,String siteName){
		BOOKNAME = bookName;
		CATALOG_PATH = catalog_path;
		SAVEPATH = SAVEPATH+BOOKNAME;
		if("23wx".equals(siteName.toLowerCase()))
			init23WX(bookName,catalog_path);
		else if("shuqi6".equals(siteName.toLowerCase()))
			initShuqi6(bookName,catalog_path);
	}
	
	public void init23WX(String bookName,String catalog_path){
		CATALOG_START = BOOKNAME+"作者：";
		CATALOG_END = BOOKNAME+"更新重要通告";
		CONTENT_START = "show_share";
		CONTENT_END = "show_htm2";
		HREF_START = "href";
		HREF_END = "\"";
		TITLE_START = ">";
		TITLE_END = "<";
		SAVEPATH = "F:\\ebook\\23wx\\"+BOOKNAME;
	}
	
	public void initShuqi6(String bookName,String catalog_path){
		CATALOG_START = BOOKNAME+"无弹窗最新章节列表";
		CATALOG_END = "书旗小说同类无弹窗阅读推荐";
		CONTENT_START = BOOKNAME + "全文阅读";
		CONTENT_END = BOOKNAME + "手机阅读";
		HREF_START = "href";
		HREF_END = "\"";
		TITLE_START = "title=\"";
		TITLE_END = "\"";
		SAVEPATH = "F:\\ebook\\shuqi6\\"+BOOKNAME;
	}
	
	public List<Map<String, String>> readCatalog(byte[] bytes)
			throws IOException {
		String html = new String(bytes,SOURCE_ENCODING);
		File f = new File(SAVEPATH);
		if(!f.isDirectory()) f.mkdirs();
		//设置抓取头，和抓取尾[避免抓取错误的a标签]
		char[] chars = html.toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean isBegin = false;
		boolean isEnd = false;
		boolean a = false;
		boolean href = false;
		boolean title = false;
		boolean semicolon = false;
		List<Map<String,String>> urlList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		for(char c : chars){
			sb.append(c);
			if(!isBegin&&sb.toString().endsWith(CATALOG_START)){
				sb.delete(0, sb.length());
				isBegin = true;
			}
			if(isBegin && !isEnd && sb.toString().endsWith(CATALOG_END)){
				break;
			}
			if(isBegin && sb.toString().endsWith("<a")){
				a = true;
				sb.delete(0, sb.length());
			}
			if(a && sb.toString().endsWith(HREF_START)){
				href = true;
				sb.delete(0, sb.length());
			}
			if(a && href && !semicolon && sb.toString().endsWith(HREF_END)){
				semicolon = true;
				sb.delete(0, sb.length());
			}
			if(a && href && semicolon && sb.toString().endsWith(HREF_END)){
				map = new HashMap<String,String>();
				map.put(CHAPTER_HREF, sb.toString().substring(0,sb.length()-1));
				href = false;
				semicolon = false;
				sb.delete(0, sb.length());
			}
			if(a && sb.toString().endsWith(TITLE_START)){
				title = true;
				sb.delete(0, sb.length());
			}
			if(a && title && sb.toString().endsWith(TITLE_END)){
				map.put(CHAPTER_TITLE, sb.toString().substring(0,sb.length()-1));
				urlList.add(map);
				title = false;
				a = false;
				sb.delete(0, sb.length());
			}
		}
		return urlList;
	}

	public int writeBookByBytes(byte[] bytes, String title, String index)
			throws IOException {
		try {
			File file = new File(SAVEPATH+File.separator+index);
			if(!file.exists()) file.createNewFile();
			BufferedReader in = new BufferedReader(new InputStreamReader(
						new ByteArrayInputStream(bytes),SOURCE_ENCODING));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file),SAVE_ENCODING));
			String line = "";
			boolean start = false;
			boolean end = false;
			bw.write(title);
			bw.newLine();
			while((line=in.readLine()) != null){
				if(line.indexOf(CONTENT_END) > -1 ) end = true;
				if(end){
					break;
				}
				if(start){
					line = new String(line.getBytes(),SOURCE_ENCODING);
					line = line.replaceAll("<br />", "\r\n").replaceAll("&nbsp;", " ").replaceAll("<.*?>","")
							.replaceAll("&lt;.*?&gt;", "").replaceAll("\\.","");
					if(!"".equals(line.trim())) {
						bw.write(line);
						bw.newLine();
					}
				}
				if(line.indexOf(CONTENT_START) > -1 ) start = true;
			}
			bw.flush();
			bw.close();
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -2;
		}
		return 0;
	}

	public int writeBookByOutputStream(OutputStream os, String title,
			String index) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getIndex(String href) {
		return href;
	}

	public String getStartPath() {
		return CATALOG_PATH;
	}
	
	public String getBookName() {
		// TODO Auto-generated method stub
		return BOOKNAME;
	}

	public String getCatalogPath() {
		// TODO Auto-generated method stub
		return CATALOG_PATH;
	}

	public String getSavePath() {
		// TODO Auto-generated method stub
		return SAVEPATH;
	}

	public String getCatalogStart() {
		// TODO Auto-generated method stub
		return CATALOG_START;
	}

	public String getCatalogEnd() {
		// TODO Auto-generated method stub
		return CATALOG_END;
	}

	public String getContentStart() {
		// TODO Auto-generated method stub
		return CONTENT_START;
	}

	public String getContentEnd() {
		// TODO Auto-generated method stub
		return CONTENT_END;
	}
}
