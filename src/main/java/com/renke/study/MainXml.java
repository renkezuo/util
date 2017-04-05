package com.renke.study;

public class MainXml {
	public static void main(String[] args) {
		ReadXml rx = new ReadXml();
		WriteXml wx = new WriteXml();
		try {
//			rx.getSourse();
			long begin_time = System.currentTimeMillis();
//			wx.editNode("project","业务监控2");
			wx.editText("预警工单发起", "hello");
			long end_time = System.currentTimeMillis();
			System.out.println("耗时："+(end_time-begin_time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
