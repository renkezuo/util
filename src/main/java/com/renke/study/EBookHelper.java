package com.renke.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class EBookHelper {
	private EBook ebook;
	public EBookHelper(EBook ebook){
		this.ebook = ebook;
	}
	
	/**
	 * ��ȡȫ���ı�����
	 * @return
	 */
	public String getAllText(){
		StringBuffer allText = new StringBuffer();
		String lineText = "";
		try {
			//��ȡ�ļ�
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));
			while((lineText=br.readLine())!=null){
				allText.append(lineText).append("\r\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allText.toString();
	}
	
	/**
	 * ÿLESSON_LINEΪһ��
	 * ע���ڵ�ǰҳΪ��ǰ�����һҳʱ����
	 * @return
	 */
	public String getNextLessonByLine(){
		//��һ������
		StringBuffer nextLesson = new StringBuffer();
		//��ǰ���������������ļ�������ʱ
		if(ebook.getThisLine()>=ebook.getFileLine()) return null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//��ȡ�ļ�
			int count = 0;				//�ӿ�ʼ������ǰ������
			int next_line = 0;			//��һҳ�Ķ����ݵ�����
			String lineText = "";		//ÿ������
			while((lineText=br.readLine())!=null){
				count ++;
				//��ǰ����С�ڵ�ǰ�ε���������ʱ
				if(ebook.getThisLine()<count){
					next_line++;
					nextLesson.append(lineText).append("\r\n");
					if(next_line==ebook.LESSON_LINE){
						ebook.setPrevLine(ebook.getThisLine());
						ebook.setThisLine(ebook.getThisLine()+next_line);
						break;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextLesson.toString();
	}
	
	
	/**
	 * ÿLESSON_LINEΪһ��
	 * ע���˷���ֻ�ڵ�ǰҳΪ��ǰ�εĵ�һҳʱ����
	 */
	public String getPreviousLessonByLine(){
		StringBuffer preLesson = new StringBuffer();
		if(ebook.getThisLine()<ebook.LESSON_LINE*2) return null;
		try {
			ebook.setThisLine(ebook.getPrevLine());
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//��ȡ�ļ�
			int count = 0;				//�ӿ�ʼ������ǰ������
			int prev_line = 0;			//��һҳ�Ķ����ݵ�����
			String lineText = "";		//ÿ������
			while((lineText=br.readLine())!=null){
				count ++;
				if(count<ebook.getThisLine()){
					prev_line ++;
					if(prev_line==ebook.LESSON_LINE){
						prev_line = 0;
						preLesson.delete(0, preLesson.length());
						ebook.setPrevLine(count);
					}else{
						preLesson.append(lineText).append("\r\n");
					}
				}else if(count==ebook.getThisLine()){
					preLesson.append(lineText).append("\r\n");
					break;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preLesson.toString();
	}
	
	public String findLessonByRate(int rate){
		if(rate==0){
			ebook.setPrevLine(0);
			ebook.setThisLine(0);
			return getNextLessonByLine();
		}
		ebook.setNowLine((ebook.getFileLine()*rate)/100);
		StringBuffer rateLesson = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//��ȡ�ļ�
			int count = 0;				//�ӿ�ʼ������ǰ������
			int line = 0;				//�������������Ķ�����
			String lineText = "";		//ÿ������
			while((lineText=br.readLine())!=null){
				count ++;
				line ++;
				if(ebook.getNowLine()>count){
					if(line==ebook.LESSON_LINE){
						line = 0;
						rateLesson.delete(0, rateLesson.length());
						ebook.setPrevLine(count);
					}else{
						rateLesson.append(lineText).append("\r\n");
					}
				}else{
					rateLesson.append(lineText).append("\r\n");
					if(line==ebook.LESSON_LINE){
						ebook.setThisLine(count);
						break;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rateLesson.toString();
	}
	
//	public String getNextLessonByByte(){
//		StringBuffer nextLesson = new StringBuffer();
//		if(ebook.getThisByte()>=ebook.getFileByte()) return null;
//		try {
//			//��ȡ�ļ�
//			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));
//			//ȫ������
//			StringBuffer txt = new StringBuffer();
//			//ÿ������
//			String lineText = "";
//			//��ǰ�εĳ���
//			int lesson_size = 0;
//			while((lineText=br.readLine())!=null){
//				txt.append("\r\n").append(lineText);
//				//��ǰλ�õ�ȫ�����ݲ�С����ǰ�����Ķ����ֽ���ʱ�����ļ�����д�뵽��һ��������
//				if(txt.toString().getBytes().length>ebook.getThisByte()+ebook.LESSON_SIZE){
//					nextLesson.append(subStringByByte(txt.toString(),ebook.getThisByte(),ebook.getThisByte()+ebook.LESSON_SIZE));
//					if(nextLesson.toString().endsWith("\r"))nextLesson.append("\n");
//					lesson_size = nextLesson.toString().getBytes().length;
//					ebook.setThisByte(ebook.getThisByte()+lesson_size);
//					break;
//				}
//			}
//			if(nextLesson.length()<=0){
//				nextLesson.append(subStringByByte(txt.toString(),ebook.getThisByte(),ebook.getThisByte()+ebook.LESSON_SIZE));
//				lesson_size = nextLesson.toString().getBytes().length;
//				ebook.setThisByte(ebook.getThisByte()+lesson_size);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return nextLesson.toString();
//	}
//	
//	public String getPreviousLessonByByte(){
//		StringBuffer preLesson = new StringBuffer();
//		if(ebook.getThisByte()>ebook.LESSON_SIZE*2) ebook.setThisByte(ebook.getThisByte()-ebook.LESSON_SIZE*2);
//		else return null;
//		try {
//			//��ȡ�ļ�
//			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));
//			//ȫ������
//			StringBuffer txt = new StringBuffer();
//			//ÿ������
//			String lineText = "";
//			//��ǰ�εĳ���
//			int lesson_size = 0;
//			while((lineText=br.readLine())!=null){
//				txt.append("\r\n").append(lineText);
//				//��ǰλ�õ�ȫ�����ݲ�С����ǰ�����Ķ����ֽ���ʱ�����ļ�����д�뵽��һ��������
//				if(txt.toString().getBytes().length>ebook.getThisByte()+ebook.LESSON_SIZE){
//					preLesson.append(subStringByByte(txt.toString(),ebook.getThisByte(),ebook.getThisByte()+ebook.LESSON_SIZE));
//					if(preLesson.toString().endsWith("\r"))preLesson.append("\n");
//					lesson_size = preLesson.toString().getBytes().length;
//					ebook.setThisByte(ebook.getThisByte()+lesson_size);
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return preLesson.toString();
//	}
	
	
	/***
	 * ȡbytes��С���ַ���
	 * ��������
	 * ��߽�ȡ����ַ�Ϊ�����ұ���ȡһ��ʱ����������������ַ�
	 * �ұ߽�ȡ����ַ�Ϊ�����ұ���ȡһ��ʱ��������Դ������ַ�
	 * @param str
	 * @param begin_bytes			��ȡ�Ŀ�ʼ�ֽ���
	 * @param end_bytes				��ȡ�Ľ����ֽ���
	 * @return
	 */
	public String subStringByByte(String str,int begin_bytes,int end_bytes){
		if(str.getBytes().length<begin_bytes) return "";
		String reStr = str.replaceAll(ebook.CHINA_EN, ebook.SINGLE_CHAR)
							.replaceAll(ebook.CHINA_CN, ebook.DOU_CHAR);
		String left = "";
		String right = "";
		if(reStr.length()<end_bytes){
			left = reStr.substring(0,begin_bytes).replaceAll(ebook.DOU_CHAR,ebook.SINGLE_CHAR)
												.replaceAll(ebook.DOU_SINGLE_CHAR,ebook.SINGLE_CHAR);
			return str.substring(left.length());
		}else{
			left = reStr.substring(0,begin_bytes).replaceAll(ebook.DOU_CHAR,  ebook.SINGLE_CHAR)
												.replaceAll(ebook.DOU_SINGLE_CHAR,ebook.SINGLE_CHAR);
			right = reStr.substring(0,end_bytes).replaceAll(ebook.DOU_CHAR,ebook.SINGLE_CHAR)
												.replaceAll(ebook.DOU_SINGLE_CHAR,ebook.DOU_NULL_CHAR);
			return str.substring(left.length(),right.length());
		}
	}
	
	public static void main(String[] args) {
		File file = new File("C:\\����.txt");
		EBook ebook = new EBook(file);
		EBookHelper ebh = new EBookHelper(ebook);
		System.out.println(ebh.getAllText().getBytes().length);
//		System.out.println(ebh.findLessonByRate(97));
//		ebh.getNextLessonByLine();
//		System.out.println(ebh.getPreviousLessonByLine());
//		System.out.println("===================�Ҹ�=======================���ǻ����ķָ���==========================�Ҹ�=======================");
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		System.out.println(ebh.getNextLessonByLine());
//		ebh.getNextLessonByLine();
//		System.out.println("===================�Ҹ�=======================���ǻ����ķָ���==========================�Ҹ�=======================");
//		System.out.println(ebh.getPreviousLessonByLine());
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		System.out.println("===================�Ҹ�=======================���ǻ����ķָ���==========================�Ҹ�=======================");
//		System.out.println(ebh.getPreviousLessonByLine());
//		System.out.println("===================�Ҹ�=======================���ǻ����ķָ���==========================�Ҹ�=======================");
//		String str = "??�й���-һ���Ӽ�������??";
//		Double d = 123.9;
//		System.out.println(d.intValue());
	}
}
