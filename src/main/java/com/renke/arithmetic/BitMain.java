package com.renke.arithmetic;

/**
 * @author Z.R.K
 * @description
 * @create 2018-10-20 15:36:10
 **/
public class BitMain {
	public static void main(String[] args) {
		byte[] temp = new byte[100];
		for(byte b = 0;b<100;b++){
			temp[b] = b;
		}
		byte[] bytes = new byte[2];
		Long begin = 0L;
		System.out.println("预热----");
		for(int k = 0;k<12;k++) {
			bytes[0] = 1;
			bytes[1] = 2;
			begin = System.currentTimeMillis();
			for(int i=0;i<1000000000;i++){
				bytes[0] = temp[i%100];
				bytes[1] = temp[i%99];
				int m = ((bytes[0] & 0xff) * 256)  + (bytes[1] & 0xff);
			}
		}
		
		
		System.out.println("开始位加：");
		for(int k = 0;k<12;k++) {
			bytes[0] = 1;
			bytes[1] = 2;
			begin = System.currentTimeMillis();
			for (int i = 0; i < 2000000000; i++) {
				bytes[0] = temp[i % 100];
				bytes[1] = temp[i % 99];
				int m = ((bytes[0] & 0xff) << 8) + (bytes[1] & 0xff);
			}
			System.out.println("bit move time : " + (System.currentTimeMillis() - begin) + "ms");
		}
		
		System.out.println("开始位或：");
		for(int k = 0;k<12;k++) {
			bytes[0] = 1;
			bytes[1] = 2;
			begin = System.currentTimeMillis();
			for (int i = 0; i < 2000000000; i++) {
				bytes[0] = temp[i % 100];
				bytes[1] = temp[i % 99];
				int m = ((bytes[0] & 0xff) << 8) | (bytes[1] & 0xff);
			}
			System.out.println("bit move | time : " + (System.currentTimeMillis() - begin) + "ms");
		}
		
		System.out.println("开始乘法：");
		for(int k = 0;k<12;k++) {
			bytes[0] = 1;
			bytes[1] = 2;
			begin = System.currentTimeMillis();
			for(int i=0;i<2000000000;i++){
				bytes[0] = temp[i%100];
				bytes[1] = temp[i%99];
				int m = ((bytes[0] & 0xff) * 256)  + (bytes[1] & 0xff);
			}
			System.out.println("mult time : " +(System.currentTimeMillis() - begin) + "ms");
		}
		

		
		
		

//		System.out.println("bit move : "+((bytes[0] & 0xff) << 8)  + (bytes[1] & 0xff));
//		System.out.println("mult : " + ((bytes[0] & 0xff) * 256)  + (bytes[1] & 0xff));
	
	}
}
