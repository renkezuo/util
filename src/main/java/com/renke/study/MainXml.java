package com.renke.study;

public class MainXml {
	public static void main(String[] args) {
		ReadXml rx = new ReadXml();
		WriteXml wx = new WriteXml();
		try {
//			rx.getSourse();
			long begin_time = System.currentTimeMillis();
//			wx.editNode("project","ҵ����2");
			wx.editText("Ԥ����������", "hello");
			long end_time = System.currentTimeMillis();
			System.out.println("��ʱ��"+(end_time-begin_time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
