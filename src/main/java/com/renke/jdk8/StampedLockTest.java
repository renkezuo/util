package com.renke.jdk8;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final long WBIT  = 1L << 7;
    private static final long RBITS = WBIT - 1L;
    private static final long RFULL = RBITS - 1L;
    private static final long ABITS = RBITS | WBIT;
    private static final long SBITS = ~RBITS; // note overlap with ABITS
	private StampedLock sl = new StampedLock();
	
	public static void main(String[] args) {
		System.out.println(Long.toBinaryString(WBIT));
		System.out.println(Long.toBinaryString(RBITS));
		System.out.println(Long.toBinaryString(RFULL));
		System.out.println(Long.toBinaryString(ABITS));
		System.out.println(Long.toBinaryString(SBITS));
	}
	
}
