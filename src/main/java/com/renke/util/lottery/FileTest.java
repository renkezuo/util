package com.renke.util.lottery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		OutputStream os = new FileOutputStream(new File("C:/333.txt"));
		Thread.sleep(5000);
		try{
			
		}catch(Exception e){
		}
		os.write("dfsasdfsdfsdafsadfsaddfsdfasd".getBytes());
		Thread.sleep(1000000);
//		System.out.println();
		System.out.println();
		;
	}
}
