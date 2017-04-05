package com.renke.study;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	public static void main(String[] args) {
		
		Properties p  = new Properties();
		try {
			p.load(PropertiesReader.class.getResourceAsStream("param.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p.get("log4j.debug"));
		
	}
}
