package com.renke.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ThreadController {
	public void downloadFile(String downPath,String filePath) {
		try{
			File file = new File(filePath);
			File files[] = file.listFiles();
			for(File f : files){
				String thePath = downPath+f.getPath().substring(filePath.length());
				if(f.isDirectory()){
					System.out.println(Thread.currentThread().getName()+"            "+thePath);
					File downF = new File(thePath);
					if(!downF.exists()){
						downF.mkdirs();
					}
					ThreadActuator ta = new ThreadActuator(thePath,f.getPath());
					ta.start();
					System.out.println(ta.getName());
				}else if(!f.isHidden()){
					File downF = new File(thePath);
					System.out.println(thePath);
					if(!downF.exists()){
						downF.createNewFile();
					}
					copyFile(downF, f);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void copyFile(File downF,File file) throws IOException{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(downF);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			int step = 1024;
			byte bytes[] = new byte[step];
			int rs = -1;
			while((rs=bis.read(bytes))!=-1){
				bos.write(bytes, 0, rs);
			}
			bos.flush();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			bos.close();
			bis.close();
			fos.close();
			fis.close();
		}
	}
	public static void main(String[] args) {
		ThreadActuator ta = new ThreadActuator("E:/test2","E:/test");
		ta.start();
		Thread th = new Thread(ta,"123123");
		System.out.println(th.getName());
//		ThreadController tc = new ThreadController();
//		tc.downloadFile("E:/test2","E:/test");
	}
}
