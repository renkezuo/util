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
	 * 获取全部文本内容
	 * @return
	 */
	public String getAllText(){
		StringBuffer allText = new StringBuffer();
		String lineText = "";
		try {
			//读取文件
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
	 * 每LESSON_LINE为一段
	 * 注：在当前页为当前段最后一页时触发
	 * @return
	 */
	public String getNextLessonByLine(){
		//下一段内容
		StringBuffer nextLesson = new StringBuffer();
		//当前段行数索引大于文件总行数时
		if(ebook.getThisLine()>=ebook.getFileLine()) return null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//读取文件
			int count = 0;				//从开始读到当前行行数
			int next_line = 0;			//下一页的段内容的行数
			String lineText = "";		//每行内容
			while((lineText=br.readLine())!=null){
				count ++;
				//当前行数小于当前段的行数索引时
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
	 * 每LESSON_LINE为一段
	 * 注：此方法只在当前页为当前段的第一页时触发
	 */
	public String getPreviousLessonByLine(){
		StringBuffer preLesson = new StringBuffer();
		if(ebook.getThisLine()<ebook.LESSON_LINE*2) return null;
		try {
			ebook.setThisLine(ebook.getPrevLine());
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//读取文件
			int count = 0;				//从开始读到当前行行数
			int prev_line = 0;			//上一页的段内容的行数
			String lineText = "";		//每行内容
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
			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));//读取文件
			int count = 0;				//从开始读到当前行行数
			int line = 0;				//所在行数索引的段行数
			String lineText = "";		//每行内容
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
//			//读取文件
//			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));
//			//全部内容
//			StringBuffer txt = new StringBuffer();
//			//每行内容
//			String lineText = "";
//			//当前段的长度
//			int lesson_size = 0;
//			while((lineText=br.readLine())!=null){
//				txt.append("\r\n").append(lineText);
//				//当前位置的全部内容不小于以前读过的段落字节数时，将文件内容写入到下一段内容中
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
//			//读取文件
//			BufferedReader br = new BufferedReader(new FileReader(ebook.getFile()));
//			//全部内容
//			StringBuffer txt = new StringBuffer();
//			//每行内容
//			String lineText = "";
//			//当前段的长度
//			int lesson_size = 0;
//			while((lineText=br.readLine())!=null){
//				txt.append("\r\n").append(lineText);
//				//当前位置的全部内容不小于以前读过的段落字节数时，将文件内容写入到下一段内容中
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
	 * 取bytes大小的字符串
	 * 中文缩进
	 * 左边截取点的字符为中文且被截取一半时，结果包含此中文字符
	 * 右边截取点的字符为中文且被截取一半时，结果忽略此中文字符
	 * @param str
	 * @param begin_bytes			载取的开始字节数
	 * @param end_bytes				载取的结束字节数
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
		File file = new File("C:\\测试.txt");
		EBook ebook = new EBook(file);
		EBookHelper ebh = new EBookHelper(ebook);
		System.out.println(ebh.getAllText().getBytes().length);
//		System.out.println(ebh.findLessonByRate(97));
//		ebh.getNextLessonByLine();
//		System.out.println(ebh.getPreviousLessonByLine());
//		System.out.println("===================我割=======================我是华丽的分割线==========================我割=======================");
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		ebh.getNextLessonByLine();
//		System.out.println(ebh.getNextLessonByLine());
//		ebh.getNextLessonByLine();
//		System.out.println("===================我割=======================我是华丽的分割线==========================我割=======================");
//		System.out.println(ebh.getPreviousLessonByLine());
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		ebh.getPreviousLessonByLine();
//		System.out.println("===================我割=======================我是华丽的分割线==========================我割=======================");
//		System.out.println(ebh.getPreviousLessonByLine());
//		System.out.println("===================我割=======================我是华丽的分割线==========================我割=======================");
//		String str = "??中国人-一个加几个？？??";
//		Double d = 123.9;
//		System.out.println(d.intValue());
	}
}
