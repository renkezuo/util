package com.renke.study;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class EBook {
	public final static int LESSON_LINE = 100;				//每段字节大小
	public final static int NEXT_LESSON = 1;				//下一段
	public final static int PREV_LESSON = 2;				//上一段
	public final static String CHINA_CN = "[^\\x00-xff]";	//双字节字符
	public final static String CHINA_EN = "[\\x00-xff]";	//单字节字符
	public final static String DOU_CHAR = "XX";				//双字节替换字符
	public final static String DOU_SINGLE_CHAR = "X";		//双字节中落单字节替换字符
	public final static String DOU_NULL_CHAR = "";			//双字节中落单字节替换字符			
	public final static String SINGLE_CHAR = "A";			//单字节替换字符
	private int fileLine = 0;		//文件总大小
	private int thisLine = 0;		//文件当前段的行数索引
	private int prevLine = 0;		//文件前一段的行数索引
	private int nowLine = 0;		//文件当前所在的行数索引
	private File file;				//txt文件
	public EBook(File file){
		setFile(file);
		setFileLine(getFileLine(file));
	}
	
	/**
	 * 取文件总行数
	 * @param file
	 * @return
	 */
	public int getFileLine(File file){
		int i = 0;
		try {
			//读取文件
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(br.readLine()!=null){
				i++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	public int getFileLine() {
		return fileLine;
	}
	public void setFileLine(int fileLine) {
		this.fileLine = fileLine;
	}
	public int getThisLine() {
		return thisLine;
	}
	public void setThisLine(int thisLine) {
		this.thisLine = thisLine;
	}
	public int getPrevLine() {
		return prevLine;
	}
	public void setPrevLine(int prevLine) {
		this.prevLine = prevLine;
	}
	public int getNowLine() {
		return nowLine;
	}
	public void setNowLine(int nowLine) {
		this.nowLine = nowLine;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
