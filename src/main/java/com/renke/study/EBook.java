package com.renke.study;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class EBook {
	public final static int LESSON_LINE = 100;				//ÿ���ֽڴ�С
	public final static int NEXT_LESSON = 1;				//��һ��
	public final static int PREV_LESSON = 2;				//��һ��
	public final static String CHINA_CN = "[^\\x00-xff]";	//˫�ֽ��ַ�
	public final static String CHINA_EN = "[\\x00-xff]";	//���ֽ��ַ�
	public final static String DOU_CHAR = "XX";				//˫�ֽ��滻�ַ�
	public final static String DOU_SINGLE_CHAR = "X";		//˫�ֽ����䵥�ֽ��滻�ַ�
	public final static String DOU_NULL_CHAR = "";			//˫�ֽ����䵥�ֽ��滻�ַ�			
	public final static String SINGLE_CHAR = "A";			//���ֽ��滻�ַ�
	private int fileLine = 0;		//�ļ��ܴ�С
	private int thisLine = 0;		//�ļ���ǰ�ε���������
	private int prevLine = 0;		//�ļ�ǰһ�ε���������
	private int nowLine = 0;		//�ļ���ǰ���ڵ���������
	private File file;				//txt�ļ�
	public EBook(File file){
		setFile(file);
		setFileLine(getFileLine(file));
	}
	
	/**
	 * ȡ�ļ�������
	 * @param file
	 * @return
	 */
	public int getFileLine(File file){
		int i = 0;
		try {
			//��ȡ�ļ�
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
