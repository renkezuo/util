package com.renke.test;

import java.util.HashSet;
import java.util.Set;

public class RefreshTest {
	
	// 计算，a+b+c+d+e = 10的可能组合
	
	public static void test(int count, String str, int maxCnt, int depth, int maxDepth, Set<String> result){
//		if (use.length >= depth) {
//			return;
//		}
//		int count = 0;
//		for(int u : use){
//			count += u;
//		}
		for (int i = 0; i <= maxCnt - count; i++) {
			if (count + i == maxCnt) {
				str += "+" + i;
				
				for(int d=depth;d<maxDepth-1;d++){
					str += "+" + 0;
				}
				result.add(str.substring(1) + "=" + 10);
				break;
			} else {
				if(depth + 1 < maxDepth){
					test(count + i, str + "+" + i, maxCnt, depth + 1, maxDepth, result);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Set<String> result = new HashSet<String>();
		test(0,"",10,0,2,result);
		for(String str : result){
			System.out.println(str);
		}
		System.out.println(result.size());
	}
}
