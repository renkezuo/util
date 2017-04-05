package com.renke.study;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CompressionHelper {
	/**
	 * ѹ���ļ�
	 * 
	 * @param srcfile
	 *            File[] ��Ҫѹ�����ļ��б�
	 * @param zipfile
	 *            File ѹ������ļ�
	 */
	public static void ZipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			//�������������Ϣ��� ��zipfile�У�
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			//ѭ��srcfile����
			for (int i = 0; i < srcfile.length; i++) {
				//��ȡ�����ļ�
				FileInputStream in = new FileInputStream(srcfile[i]);
				//��ŵ����ļ�
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Complete the entry
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("ѹ�����.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ѹ��
	 * 
	 * @param zipfile
	 *            File ��Ҫ��ѹ�����ļ�
	 * @param descDir
	 *            String ��ѹ���Ŀ��Ŀ¼
	 */
	public static void UnZipFiles(File zipfile, String descDir) {
		try {
			// Open the ZIP file
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				// Get the entry name
				ZipEntry entry = ((ZipEntry) entries.nextElement());
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				// System.out.println(zipEntryName);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				// Close the file and stream
				in.close();
				out.close();
				System.out.println("��ѹ�����.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void unZipFiles(){
		BufferedOutputStream bos = null;
		InputStream is = null;
		BufferedInputStream bis = null;
		byte[] bt = new byte[1024];
		try {
			ZipFile zf = new ZipFile("C:\\Txt.rar");
			Enumeration e = zf.entries();
			while(e.hasMoreElements()){
				ZipEntry ze = (ZipEntry)e.nextElement();
				is = zf.getInputStream(ze);
				bos = new BufferedOutputStream(new FileOutputStream(new File("C:\\test\\"+ze.getName())));
				bis = new BufferedInputStream(is);
				int len = -1;
				while((len=bis.read(bt))!=-1){
					bos.write(bt, 0, len);
				}
				bos.close();
				bis.close();
				is.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File[] files = new File[3];
		files[0] = new File("C:\\xxx.xls");
		files[1] = new File("C:\\xxx2.xls");
		files[2] = new File("C:\\hello.txt");
//		files[3] = new File("C:\\layout");
		File rar_file = new File("C:\\Txt.rar");
//		ZipFiles(files,rar_file);
		unZipFiles();
	}
}
