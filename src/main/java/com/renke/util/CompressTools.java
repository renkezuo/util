package com.renke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CompressTools {
	/***
	 * ��ȡ�ļ���ÿһ�У�trim()
	 * ������',|;|:|=|+|-|*|/|%|(|)|{|}|[|]'֮��֮ǰ�Ŀհ��ַ�ȥ��
	 * ȡ<>֮�����ݣ�����հ��ַ��滻Ϊһ��
	 * ������""''�ڵ�����trim()
	 * ȡ><֮�����ݣ�trim();
	 * ����Ӧ������󲿷�����ˣ���������������ټ�
	 * 
	 * @param html
	 * @return
	 * @author renke.zuo@foxmail.com
	 * @time 2016-11-18 15:02:07
	 */
	public static String CompressHtml(String html){
		StringBuilder minHtml = new StringBuilder();
		html = html.trim();
		html = html.replaceAll("\\s*,\\s*", ",")
				.replaceAll("\\s*;\\s*", ";").replaceAll("\\s*:\\s*", ":")
				.replaceAll("\\s*=\\s*", "=").replaceAll("\\s*\\+\\s*", "+")
				.replaceAll("\\s*-\\s*", "-").replaceAll("\\s*\\*\\s*", "*")
				.replaceAll("\\s*\\/\\s*", "/").replaceAll("\\s*\\%\\s*", "%")
				.replaceAll("\\s*\\(\\s*", "(").replaceAll("\\s*\\)\\s*", ")")
				.replaceAll("\\s*\\{\\s*", "{").replaceAll("\\s*\\}\\s*", "}")
				.replaceAll("\\s*\\[\\s*", "[").replaceAll("\\s*\\]\\s*", "]")
				.replaceAll("\\s*\\>\\s*", ">");
//		System.out.println(html);
//		int indexBegin = 0;
//		int indexEnd = html.indexOf(">");
//		String segment = html.substring(indexBegin,indexEnd);
		return minHtml.toString();
	}
	
	public void CompressJs(File js){
		
	}
	
	public void CompressCss(File css){
		
	}
	
	public static void main(String[] args) {
		File file = new File("F:\\work\\model\\�򵥺�̨\\compress.html");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder html = new StringBuilder();
			String line = br.readLine();
			while(line != null){
				System.out.println(new String(line.getBytes("GBK"),"UTF-8"));
				html.append(CompressHtml(line));
				line = br.readLine();
			}
			System.out.println(html.toString());
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
