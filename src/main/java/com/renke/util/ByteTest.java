package com.renke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteTest {
	
	public static void main(String[] args) throws IOException {
		String str = "���";
		System.out.println(str.getBytes().length);
//		File file = new File("GBK");
		File file2 = new File("UTF-8");
		//��GBK��ʽ��UTF-8�ļ���2�������ַ������6���ֽ� 1 3 
		//��UTF-8��ʽ��GBK�ļ���2�������ַ������3���ֽ�
		//GBK��GBK��UTF-8��UTF-8Ϊ4���ֽ�
		//��GBKһ�����ĵ�λ��X��UTF-8��һ������λ��ΪY
		//UTF-8Ϊ6�ֽ�
		//GBKΪ4�ֽ�
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
