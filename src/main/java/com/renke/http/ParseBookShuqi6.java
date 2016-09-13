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

public class ParseBookShuqi6 implements ParseBook{
	
	
	public String BOOKNAME = "奥术神座";
	public String CATALOG_PATH = "http://www.shuqi6.com/2486/";
	
	public String SAVEPATH = "F:\\ebook\\";
	public String CATALOG_START = BOOKNAME+"无弹窗最新章节列表";
	public String CATALOG_END = "书旗小说同类无弹窗阅读推荐";
	public String CONTENT_START = BOOKNAME + "全文阅读";
	public String CONTENT_END = BOOKNAME + "手机阅读";
	
	
	public ParseBookShuqi6(String bookName,String catalog_path){
		BOOKNAME = bookName;
		CATALOG_PATH = catalog_path;
		SAVEPATH = SAVEPATH+BOOKNAME;
		CATALOG_START = BOOKNAME+"无弹窗最新章节列表";
		CONTENT_START = BOOKNAME + "全文阅读";
		CONTENT_END = BOOKNAME + "手机阅读";
	}
	
	@Override
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
			if(a && sb.toString().endsWith(CHAPTER_HREF)){
				href = true;
				sb.delete(0, sb.length());
			}
			if(a && href && !semicolon && sb.toString().endsWith("\"")){
				semicolon = true;
				sb.delete(0, sb.length());
			}
			if(a && href && semicolon && sb.toString().endsWith("\"")){
				map = new HashMap<String,String>();
				map.put(CHAPTER_HREF, sb.toString().replaceAll("\"",""));
				href = false;
				semicolon = false;
				sb.delete(0, sb.length());
			}
			if(a && sb.toString().endsWith(CHAPTER_TITLE)){
				title = true;
				sb.delete(0, sb.length());
			}
			if(a && title && !semicolon && sb.toString().endsWith("\"")){
				semicolon = true;
				sb.delete(0, sb.length());
			}
			if(a && title && semicolon && sb.toString().endsWith("\"")){
				map.put(CHAPTER_TITLE, sb.toString().replaceAll("\"",""));
				urlList.add(map);
				title = false;
				semicolon = false;
				a = false;
				sb.delete(0, sb.length());
			}
		}
		return urlList;
	}

	@Override
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
					line = line.replaceAll("&nbsp;", " ").replaceAll("<.*?>","")
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

	@Override
	public int writeBookByOutputStream(OutputStream os, String title,
			String index) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getIndex(String href) {
		return href;
	}

	@Override
	public String getStartPath() {
		return CATALOG_PATH;
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
