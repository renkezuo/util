package com.renke.y2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Z.R.K
 * @description
 * @create 2018-09-13 11:52:21
 **/
public class SortUtil {
	public static void main(String[] args) {
		Long begin = System.currentTimeMillis();
		List<Integer> sortList = new ArrayList<>(1000000);
		Random random = new Random();
		for(int i=0;i<1000000;i++){
			sortList.add(random.nextInt(10000));
		}
		
		System.out.println("initSortList : "+(System.currentTimeMillis() - begin) + "ms");
		sortList.sort((o1, o2) -> {if(o1 > o2) return 1 ;else if(o1 < o2) return -1;else return 0;});
		
		System.out.println("sortList : " + (System.currentTimeMillis() - begin) + "ms");
		
	}
}
