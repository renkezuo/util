package com.renke.ebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.renke.ebook.pojo.Section;
import com.renke.ebook.pojo.WebSite;

public class BookParser {
	/**
	 * 读取目录，封装到list
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static List<Section> readCatalog(byte[] bytes,WebSite ws)
			throws IOException {
		String html = new String(bytes,ws.sourceEncoding);
		File f = new File(ws.savePath);
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
		List<Section> urlList = new ArrayList<Section>();
		Section s = new Section();
		for(char c : chars){
			sb.append(c);
			if(!isBegin&&sb.toString().endsWith(ws.catalogStart)){
				sb.delete(0, sb.length());
				isBegin = true;
			}
			if(isBegin && !isEnd && sb.toString().endsWith(ws.catalogEnd)){
				break;
			}
			if(isBegin && sb.toString().endsWith("<a")){
				a = true;
				sb.delete(0, sb.length());
			}
			if(a && sb.toString().endsWith(ws.hrefStart)){
				href = true;
				sb.delete(0, sb.length());
			}
			if(a && href && !semicolon && sb.toString().endsWith(ws.hrefEnd)){
				semicolon = true;
				sb.delete(0, sb.length());
			}
			if(a && href && semicolon && sb.toString().endsWith(ws.hrefEnd)){
				s.setUrl(sb.substring(sb.lastIndexOf("/")+1,sb.length()-1));
				href = false;
				semicolon = false;
				sb.delete(0, sb.length());
			}
			if(a && sb.toString().endsWith(ws.titleStart)){
				title = true;
				sb.delete(0, sb.length());
			}
			if(a && title && sb.toString().endsWith(ws.titleEnd)){
				s.setName(sb.toString().substring(0,sb.length()-1));
				urlList.add(s);
				s = new Section();
				title = false;
				a = false;
				sb.delete(0, sb.length());
			}
		}
		return urlList;
	}
	
	/**
	 * 将抓取的数据写入到对应文件
	 * @param bytes
	 * @param s
	 * @param ws
	 * @return
	 * @throws IOException
	 */
	public static int writeSectionByBytes(byte[] bytes,Section s,WebSite ws)
			throws IOException {
		try {
			File file = new File(ws.savePath+File.separator +s.getUrl());
			if(!file.exists()) file.createNewFile();
			BufferedReader in = new BufferedReader(new InputStreamReader(
						new ByteArrayInputStream(bytes),ws.sourceEncoding));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file),ws.saveEncoding));
			String line = "";
			boolean start = false;
			boolean end = false;
			bw.write(s.getName());
			bw.newLine();
			while((line=in.readLine()) != null){
				if(line.indexOf(ws.contentEnd) > -1 ) end = true;
				if(end){
					break;
				}
				if(start){
					line = new String(line.getBytes(),ws.sourceEncoding);
					line = line.replaceAll("<br />", "\r\n").replaceAll("&nbsp;", " ")
							.replaceAll("<.*?>","").replaceAll("&lt;.*?&gt;", "")
							.replaceAll("\\.","").replaceAll("readx\\(\\);","");
//							.replaceAll("style5\\(\\);","").replaceAll("style8\\(\\);","");
					if(!"".equals(line.trim())) {
						bw.write(line);
						bw.newLine();
					}
				}
				if(line.indexOf(ws.contentStart) > -1 ) start = true;
			}
			bw.flush();
			bw.close();
			in.close();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			return -1;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -2;
		}
		return 0;
	}
}
