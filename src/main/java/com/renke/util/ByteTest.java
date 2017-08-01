package com.renke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteTest {
	
	public static void main(String[] args) throws IOException {
		String str = "你好";
		System.out.println(str.getBytes().length);
//		File file = new File("GBK");
		File file2 = new File("UTF-8");
		//以GBK方式读UTF-8文件，2个中文字符串变成6个字节 1 3 
		//以UTF-8方式读GBK文件，2个中文字符串变成3个字节
		//GBK读GBK和UTF-8读UTF-8为4个字节
		//设GBK一个中文的位数X，UTF-8的一个中文位数为Y
		//UTF-8为6字节
		//GBK为4字节
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"UTF-8"));
		String line;
		while((line = br.readLine())!=null){
			System.out.println("1:"+line);//GBK true true
			System.out.println("2:"+new String(line.getBytes(),"GBK"));//GBK  true  true
			System.out.println("3:"+new String(line.getBytes(),"UTF-8"));//read:GBK file:UTF-8 -->  true
			System.out.println("4:"+new String(line.getBytes("GBK"),"GBK"));//GBK true  true
			System.out.println("6:"+new String(line.getBytes("GBK"),"UTF-8"));//read:GBK  file:UTF-8  true
			System.out.println("5:"+new String(line.getBytes("UTF-8"),"GBK"));//
			System.out.println("7:"+new String(line.getBytes("UTF-8"),"UTF-8"));//
			

			System.out.println(line.getBytes("GBK").length);
			System.out.println(line.getBytes("UTF-8").length);
		}
		br.close();
	}
}
