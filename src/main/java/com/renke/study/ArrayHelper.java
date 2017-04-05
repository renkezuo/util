package com.renke.study;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

public class ArrayHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strs = new String[5000];
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		set1.add("2");
		set1.add(3);
		set1.add(5);

		set2.add("2");
		set2.add(5);
		set2.add(3);
		System.out.println(set1.equals(set2));
		System.out.println(set1.containsAll(set2));
		set1.equals(set2);
//		System.out.println(strs.length);
		String str = "\u786e\u5b9a\u8981\u5220\u9664\u5417\uff1f";
		try {
			System.out.println(URLEncoder.encode(str,"GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
