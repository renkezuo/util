package com.renke.jdk8;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JVMTest {
	
	public static void testPermGen(){
		URL url = null;
		List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
		try {
			url = new File("/tmp").toURI().toURL();
			URL[] urls = {url};
			int i=0;
			while (true){
				ClassLoader loader = new URLClassLoader(urls);
				classLoaderList.add(loader);
				loader.loadClass("com.renke.jdk8.CycleTest");
				i++;
				if(i<5){
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		testPermGen();
	}
	
}
