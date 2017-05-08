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
    //别人给的流，只操作，不管理
	// 打开流和关闭流，请调用方处理，方法不处理
	public static void resizeImage(InputStream is, OutputStream os, double percent, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		int newWidth = (int) (width * (percent / 100));
		int newHeight = (int) (height * (percent / 100));
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		// 图片上下文，理解为画布
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
		// 图片上下文，理解为画布
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, width, height, null);
		ImageIO.write(image, format, os);
	}
    public static void main(String[] args) throws Exception {
		FileInputStream is = new FileInputStream(new File("C:/Users/Administrator/Desktop/九宫格/大图/jb1.jpg"));
		FileOutputStream os = new FileOutputStream(new File("C:/Users/Administrator/Desktop/九宫格/大图/jb1.small.jpg"));
		resizeImage(is,os,1080,1920,"jpg");
		is.close();
		os.close();
	}
}