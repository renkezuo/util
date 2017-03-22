package com.renke.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 *
 * ����:֧��SHA-1/MD5��ϢժҪ�Ĺ�����.
 * 
 * ����ByteSource���ɽ�һ��������ΪHex, Base64��UrlSafeBase64
 * 
 * Base64.encodeToString(Digests.sha1("123456".getBytes("UTF-8")))
 *
 * @author  WQB
 * @created 2014-4-4 ����2:57:56
 * @since   v1.0.0
 */
public class Digests {

	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();

	/**
	 * �������ַ�������sha1ɢ��.
	 */
	public static byte[] sha1(byte[] input) {
		return digest(input, SHA1, null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, SHA1, salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}

	public static byte[] sha1(String input) {
		return digest(input.getBytes(), SHA1, null, 1);
	}
	
	public static byte[] sha1(String input, String salt) {
		return digest(input.getBytes(), SHA1, salt.getBytes(), 1);
	}
	
	public static byte[] sha1(String input, String salt, int iterations) {
		return digest(input.getBytes(), SHA1, salt.getBytes(), iterations);
	}
	
	/**
	 * �������ַ�������md5.
	 */
	public static byte[] md5(String input){
		return digest(input.getBytes(), MD5, null, 0);
	}
	
	public static byte[] md5(byte[] input){
		return digest(input, MD5, null, 0);
	}
	
	
	/**
	 * ���ַ�������ɢ��, ֧��md5��sha1�㷨.
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���������Byte[]��Ϊsalt.
	 * 
	 * @param numBytes byte����Ĵ�С
	 */
	public static byte[] generateSalt(int numBytes) {
		if (numBytes <= 0) {
			throw new IllegalArgumentException("numBytes argument must be a positive integer (1 or larger)");
		}
		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * ���ļ�����md5ɢ��.
	 */
	public static byte[] md5(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * ���ļ�����sha1ɢ��.
	 */
	public static byte[] sha1(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
}