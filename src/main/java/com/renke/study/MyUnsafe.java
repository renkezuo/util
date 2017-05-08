package com.renke.study;

import java.lang.reflect.Field;

import com.renke.lesson.pojo.Base;

import sun.misc.Unsafe;

public class MyUnsafe {
	public static void main(String[] args) throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe)f.get(null);
		Long strOffset = unsafe.objectFieldOffset
                (Base.class.getDeclaredField("name"));
		Base base = new Base();
		unsafe.compareAndSwapObject(base, strOffset, null,"123");
		System.out.println(base.getName());
	}
}
