package com.renke.office;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class WordHelper {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\测试.doc");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		byte[] a=new String("看见了！").getBytes();
		ByteArrayInputStream bs = new ByteArrayInputStream(a);
		POIFSFileSystem fs = new POIFSFileSystem();
		DirectoryEntry directory = fs.getRoot();
		DocumentEntry de = directory.createDocument("WordDocument", bs);
		//以上两句代码不能省略，否则输出的是乱码
		fs.writeFilesystem(fos);
		fs.close();
		bs.close();
		de.delete();
		fos.flush();
		fos.close();
//		FileInputStream fis = new FileInputStream(file);
//		HWPFDocument doc = new HWPFDocument(fis); 
//		WordExtractor we = new WordExtractor(fis);
//		we.getParagraphText();
	}
}
