package com.renke.study;

public class StrangeByInt {
	/**
	 *	机器码	原码、反码、补码				数值		原	码		反	码		补	码
	 *	正数的原码、反码、补码相同				6		00000110	00000110	00000110
	 *	负数的反码为原码逐位取反，符号位除外 		-6		10000110	11111001	11111010		
	 *	负数的补码为反码加1
	 *	负数原码取反码逐位取反，符号位除外 
	 *	或取补码逐位取反，符号位除外，加1
	 **/
	/**
	 *	位运算	&、|、^	(计算反码，返回反码)
	 *	&位与运算		补码同位值，都为1时，该位值为1，否则为0		6&5		0110&0101	0100	4
	 *	|位或运算		补码同位值，同为0时，该位值为0，否则为1		6|5		0110|0101	0111	7
	 *	^位非运算		补码同位值相同，则为1，不同则为0			6^5		0110^0101	0011	3
	 **/
	/**
	 *	位移		>>、<<、>>>			没猜错的话   应该也是补码取值
	 *	>>		右移运算符	空位补符号位[正数补0，负数补1]，低位右移		(运算符位不变)
	 *	<<		左移运算符	空位补零，低位左移						(运算符位不变)
	 *	>>>		右移运算符	空位补零，低位右移						(运算符位改变)
	 *	<<<		左移运算符	空位补零，低位右移						(运算符位改变) 不存在此运算符
	 **/
	/**
	 * 判断一个整数是否为奇数
	 */
	public boolean isOdd(int i){
		return (i&1)==1;
	}
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(-23));
		System.out.println(-6&-5);
		System.out.println(6|5);
		System.out.println(6^5);
		System.out.println(-23 >>> 1);
		System.out.println(Integer.toBinaryString(-23  >>> 1));
		System.out.println(6<<32);
		
		System.out.println(65536 & 65535);
	}
}
