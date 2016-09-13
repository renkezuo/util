package com.renke.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class DownloadUtil {
	public static void mergeBook(String filePath,List<Map<String,String>> dir) throws IOException{
		File file = new File(filePath+".txt");
		OutputStream os = new FileOutputStream(file);
		byte[] buf = new byte[8096];
		while(dir.size()>0){
			Map<String,String> map = dir.remove(0);
			String file_name = map.get(ParseBook.CHAPTER_HREF);
			InputStream is = new FileInputStream(new File(filePath+"/"+file_name));
			int len = 0;
			while( (len = is.read(buf)) >=0){
				os.write(buf, 0, len);
			}
			is.close();
		}
		os.flush();
		os.close();
	}
	
	public static void writeToFile(byte[] bytes , String filePath) throws IOException{
		File file = new File(filePath);
		OutputStream os = new FileOutputStream(file);
		os.write(bytes);
		os.close();
	}
}
