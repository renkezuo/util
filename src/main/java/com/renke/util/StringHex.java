package com.renke.util;
import java.io.ByteArrayOutputStream;

public class StringHex {
	private static String hexString = "0123456789ABCDEF";
	public final static int TYPE_HEX = 16;
	public final static int TYPE_OCTAL = 8;
	public final static int TYPE_BINARY = 2;
	/***
	 * ���ַ�������Ϊ16������
	 * ȡÿ���ַ���
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-08 13:48:27
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		// ����Ĭ�ϱ����ȡ�ֽ�����
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// ���ֽ�������ÿ���ֽڲ���2λ16��������
		for (int i = 0; i < bytes.length; i++) {
			//11110000  >> 4   1111  ����λ�������㣬������ƶ�������λ
			//00001111    ����λ��������
			//һ���ֽڲ��������16������
			//charAt(x)�����toHexString(x);��4����
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt(bytes[i] & 0x0f));
		}
		return sb.toString();
	}
	
	/**
	 * ��16�����ַ���������Ϊ�ַ���
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-08 13:57:54
	 * @param bytes
	 * @return
	 */
	public static String decode(String bytes) {
		bytes = bytes.toUpperCase();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// ��ÿ2λ16����������װ��һ���ֽ�
		for (int i = 0; i < bytes.length(); i += 2){
			baos.write(
					(hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1)))
					);
		}
		return new String(baos.toByteArray());
	}
	
	/**
	 * ���ַ���ת��Ϊʮ����
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-08 15:06:03
	 * @param hexString
	 * @param type[�ַ������������(�����ơ��˽��ơ�ʮ������)]
	 * @return
	 */
	public static int toInt(String hexString,int type){
		return Integer.parseInt(hexString,type);
	}
	
	/**
	 * ��valueת��Ϊ��Ӧ���͵��ַ���
	 * @author renke.zuo@foxmail.com
	 * @version V1.0
	 * @time 2016-07-08 15:09:15
	 * @param value
	 * @param type
	 * @return
	 */
	public static String toTypeString(int value,int type){
		if(type == TYPE_BINARY){
			return Integer.toBinaryString(value);
		}else if(type == TYPE_HEX){
			return Integer.toHexString(value);
		}else if(type == TYPE_OCTAL){
			return Integer.toOctalString(value);
		}else{
			return Integer.toString(value);
		}
	}
	
}