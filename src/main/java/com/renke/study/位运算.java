package com.renke.study;

public class Î»ÔËËã {
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
//		int i = 2<<3;
//		int i = 2* 8;
//		int i = 1<<3;
		int i = 8>>2;
		System.out.println(i);
//		System.out.println(1<<3);
		System.out.println(System.currentTimeMillis()-begin);
		int page = 10;
		String sql ="select top 8 letterid from letterreceiverinfo where letterreceiver='' and receiverinfoid not in(select top "+(page-1)*8+" receiverinfoid from letterreceiverinfo ) and isread=1";
		System.out.println(sql);
	}
}
