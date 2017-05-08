package com.renke.study;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
public class PICHelper {
    //���˸�������ֻ������������
	// �����͹ر���������÷���������������
	public static void resizeImage(InputStream is, OutputStream os, double percent, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		int newWidth = (int) (width * (percent / 100));
		int newHeight = (int) (height * (percent / 100));
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		// ͼƬ�����ģ����Ϊ����
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
	}
	
	public static void resizeImage(InputStream is, OutputStream os,int width, int height, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
//		double width = prevImage.getWidth();
//		double height = prevImage.getHeight();
//		int newWidth = (int) (width * (percent / 100));
//		int newHeight = (int) (height * (percent / 100));
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// ͼƬ�����ģ����Ϊ����
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, width, height, null);
		ImageIO.write(image, format, os);
	}
    public static void main(String[] args) throws Exception {
		FileInputStream is = new FileInputStream(new File("C:/Users/Administrator/Desktop/�Ź���/��ͼ/jb1.jpg"));
		FileOutputStream os = new FileOutputStream(new File("C:/Users/Administrator/Desktop/�Ź���/��ͼ/jb1.small.jpg"));
		resizeImage(is,os,1080,1920,"jpg");
		is.close();
		os.close();
	}
}