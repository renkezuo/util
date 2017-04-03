package com.renke.jar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarHelper {
	public final static String sourcePath = "D:/workspace/unzip/src/main/java/";
	public final static String classPath = "D:/workspace/unzip/target/classes/";

	public static void decompressJar(File file, String destPath) throws IOException {
		byte[] buf = new byte[8192];
		JarFile jar = null;
		try {
			jar = new JarFile(file);
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					// mkdir
					File dir = new File(destPath + entry.getName());
					if (!dir.exists() )
						dir.mkdirs();
				} else{
					// decompress
					BufferedOutputStream bos = null;
					String absolutePath = destPath + entry.getName();
					try {
						File dir = new File(
								absolutePath.substring(0,absolutePath.lastIndexOf("/")));
						if(!dir.exists()) dir.mkdirs();
						InputStream is = jar.getInputStream(entry);
						bos = new BufferedOutputStream(new FileOutputStream(destPath + entry.getName()));
						int len = is.read(buf);
						bos.write(buf, 0, len);
					} finally {
						if (bos != null)
							bos.close();
					}
				}
			}
		} finally {
			jar.close();
		}
	}

	public static void getFiles(File file, final List<File> fileList) {
		if (file.isDirectory()) {
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (pathname.isFile() && pathname.getName().endsWith(".jar")) {
						fileList.add(pathname);
						return false;
					} else if (pathname.isDirectory()) {
						return true;
					}
					return false;
				}
			});

			for (File f : files) {
				getFiles(f, fileList);
			}
		}
	}

	public static void main(String[] args) {
		String dirPath = "F:/Program Files/Java/jre1.8.0_101/lib";
		dirPath = "D:/workspace/resources";
		File file = new File(dirPath);
		List<File> fileList = new ArrayList<File>();
		getFiles(file, fileList);
		for (File f : fileList) {
			try {
				if (f.getName().indexOf("sources") != -1) {
					decompressJar(f, sourcePath);
				} else {
					decompressJar(f, classPath);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		String str = "/accessibility/AccessBridge$1.class";
//		
//		System.out.println(str.substring(0, str.lastIndexOf("/")));
	}

}
