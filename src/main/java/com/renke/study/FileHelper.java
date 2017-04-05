package com.renke.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileHelper {
	public void CopyFileToDir(String file_path,String dir){
		try {
			FileInputStream fis = new FileInputStream(file_path);
			FileOutputStream fos = new FileOutputStream(dir);
			byte[] bt = new byte[1024];
			int a = 0;
			while((a = fis.read(bt))!=-1){
				fos.write(bt,0,a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getFiles(){
		File file = new File("E:\\FavoriteVideo");
		file.list(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				System.out.println(dir.getName());
				System.out.println(name);
				return false;
			}
		});
	}
	
	
	public static void main(String[] args) {
		File file = new File("C:\\demo2.doc");
//		File file2 = new File("C:\\xxx\\demo1.doc");
		file.renameTo(new File("C:\\demo1.doc"));
//		if(!file2.exists()){
//			try {
//				file2.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(file.lastModified());
//		System.currentTimeMillis();
//		Calendar c = Calendar.getInstance();
//		c.setTimeInMillis(file.lastModified());
//		System.out.println(c.get(Calendar.YEAR));
//		System.out.println(c.getTime());
//		Date date = c.getTime();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(date));
		FileHelper fh = new FileHelper();
//		fh.CopyFileToDir("C:\\demo1.doc","C:\\xxx\\demo1.doc");
//		fh.CopyFileToDir("C:\\test1.txt","C:\\testaaaa.txt");
//		fh.getFiles();
	}
}
