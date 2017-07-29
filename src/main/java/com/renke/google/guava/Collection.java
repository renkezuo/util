package com.renke.google.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;

public class Collection {
	public static void main(String[] args) {
		ListMultimap<String, Integer> list = LinkedListMultimap.create();
		Multiset<Integer> set = HashMultiset.create();
		Multiset<Integer> set2 = HashMultiset.create();
		set.add(123);
		set2.add(123);
		set.add(456);
		set2.add(789);
		set.add(789);
		set2.add(456);
		System.out.println(set.equals(set2));
	}
}
