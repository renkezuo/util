package com.renke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Password {
	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Md5.
	 */
	public static String md5(byte[] input) {
		return encodeBase64(Digests.md5(input));
	}

	public static String md5(String input) {
		return encodeBase64(Digests.md5(input));
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBytes(input);
	}

	public static String encodeBase64(String encrypt) {
		return encodeBase64(encrypt.getBytes());
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		try {
			return Base64.decode(input);
		} catch (IOException e) {
			return  null;
		}
	}

	public static String decodeBase64ToString(String value) {
		try {
			return new String(decodeBase64(value), DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * URL 编码, Encode默认为UTF-8. 
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8. 
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转义HTML
	 * @param html
	 * @return
	 */
	public static String htmlEscape(String html) {
		if (html == null || html.trim().length() < 1)
			return "";
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll("'", "&apos;");
		html = html.replaceAll("\"", "&quot;");
		html = html.replaceAll(" ", "&nbsp;");
		return html;
	}
//	
//	public static String encodeBase64(String password){
//		byte[] bytes = password.getBytes();
//		return Base64.getEncoder().encodeToString(bytes);
//	}
//	
//	public static String decodeBase64(String password){
//		byte[] bytes = password.getBytes();
//		return new String(Base64.getDecoder().decode(bytes));
//	}
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		File file = new File("F:/ebook/password.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int i = 0;
//		System.out.println(md5("c6z23oj2"));
		while((line = br.readLine()) !=null){
			String result = md5(line);
			
			//111111  lueSGJZetyySpUndWjMBEg== 
			//1234567a /gCHAPJcsolAyo7ZGyOzVA==
			//a1234567 VpDd36KK4IXSNRigNXBygg==
			//1234567 /OqSD3QStdp74M9CuMk3WQ==
			//7uDzwxnHva9jEVWe7FBY4Q==
			if(result.equals("7uDzwxnHva9jEVWe7FBY4Q==")){
				System.out.println(line);
				break;
			}
			i++;
		}
		br.close();
		System.out.println("匹配次数："+i);
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update("testappkeytestappsecret兑吧".getBytes("UTF-8"));
//		System.out.println(new BigInteger(1, md.digest()).toString(16));;
		
	}
}
