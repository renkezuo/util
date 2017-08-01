package com.renke.study;

import java.util.ArrayList;
import java.util.List;

public class MathHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List goodList = new ArrayList();
		goodList.add(11);
		goodList.add(12);
		goodList.add(2);
		goodList.add(3);
		goodList.add(4);
		goodList.add(5);
		goodList.add(6);
		goodList.add(9);
		goodList.add(8);
		List<Integer> n = new ArrayList<Integer>();
		n.add(7);
		n.add(1);
		n.add(0);
//		System.out.println(2500%2500);
		for(int m=n.size()-1;m>=0;m--){
			System.out.println(n.get(m));
			int i = n.get(m);
			goodList.remove(i);
			System.out.println(goodList.size());
			goodList.remove(i);
			System.out.println(goodList.size());
			System.out.println("remove了吗？");
		}
//		goodList.remove(9);
//		goodList.remove(8);
//		goodList.remove(7);
//		goodList.remove(6);
//		goodList.remove(5);
//		goodList.remove(4);
//		goodList.remove(3);
//		goodList.remove(2);
//		goodList.remove(0);
		
		System.out.println("goodList的大小 为"+goodList.size());


	}

}
