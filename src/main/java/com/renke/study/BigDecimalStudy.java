package com.renke.study;

import java.math.BigDecimal;

public class BigDecimalStudy {
	public static void main(String[] args) {
		BigDecimal bd1 = new BigDecimal(100.20);
		BigDecimal bd2 = new BigDecimal(200.30);
		System.out.println(bd1.subtract(bd2));
	}
}
