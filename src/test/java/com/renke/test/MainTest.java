package com.renke.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.renke.util.StringHex;

public class MainTest {
	static void getMsg(){
		System.out.println("null:hello world");
	}
	
	
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		System.out.println("123123".substring(5));
		MainTest.getMsg();
		
		
		try {
			GZIPInputStream gis = new GZIPInputStream(new FileInputStream(new File("OUTPUTGZIP")));
			byte[] buf = new byte[32];
			int len = gis.read(buf);
			System.out.println(new String(buf,0,len));
			
			FileInputStream fis = new FileInputStream(new File("OUTPUTGZIP"));
			len = fis.read(buf);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(buf,0,len);
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			gis = new GZIPInputStream(bais);
			len = gis.read(buf);
			System.out.println(new String(buf,0,len));
			
			GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(new File("OUTPUTGZIP")));
			gos.write("ÄãºÃ".getBytes());
			gos.close();
			fis.close();
			
			buf = new byte[3];
			buf[0]=56;
			buf[1]=99;
			buf[2]=98;
			String str = new String(buf);
			System.out.println(str);
			
			System.out.println(StringHex.toInt(str, StringHex.TYPE_HEX));
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("hello", "ÊÀ½ç");
			test(map);
			System.out.println(map.get("hello"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int y =  0;
	}
	
	public static void test(Map<String,Object> map){
		map = new HashMap<String,Object>();
		map.put("hello", "world");
	}
}
