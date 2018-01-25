package com.renke.paike.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.renke.paike.model.LayerUserInfo;
import com.renke.paike.model.SplitKlass;

public class SortUtil {
	
	public static Comparator<SplitKlass> sortBySub(){
		return new Comparator<SplitKlass>(){
			@Override
			public int compare(SplitKlass class1, SplitKlass class2) {
				if(class1.getSubId() == null) return 1;
				if(class2.getSubId() == null) return -1;
				if(class1.getSubId() > class2.getSubId()) return 1;
				if(class1.getSubId().equals(class2.getSubId())) return 0;
				return -1;
			};
		};
	}
	public static Comparator<SplitKlass> sortByTeam(){
		return new Comparator<SplitKlass>(){
			@Override
			public int compare(SplitKlass class1, SplitKlass class2) {
				if(class1.getTeamId() > class2.getTeamId()) return 1;
				if(class1.getTeamId() == class2.getTeamId()) return 0;
				return -1;
			};
		};
	}
	
	/***
	 * 计算组合之间差值和，越大，表示越分散，越优先
	 * @param dayCnt
	 * @return
	 * @author Z.R.K
	 */
	public static Comparator<Integer> sortByGroup(int dayCnt) {
		return new Comparator<Integer>() {
			@Override
			public int compare(Integer param1, Integer param2) {
				int score1 = 0;
				int score2 = 0;
				for (int i = 0; i < dayCnt; i++) {
					int tmp1 = 0;
					int tmp2 = 0;
					int p = i - 1;
					int n = i + 1;
					if (p < 0)
						p = dayCnt - 1;
					if (n == dayCnt)
						n = 0;
					int tmp = (param1 >> (i * 4) & 15);
					tmp1 = tmp - (param1 >> (p * 4) & 15);
					tmp2 = tmp - (param1 >> (n * 4) & 15);
					if(tmp1 < 0) tmp1 = -tmp1;
					if(tmp2 < 0) tmp2 = -tmp2;
					score1 += tmp1 + tmp2;
					
					tmp = (param2 >> (i * 4)) & 15;
					tmp1 = tmp - (param2 >> (p * 4) & 15);
					tmp2 = tmp - (param2 >> (n * 4) & 15);
					if(tmp1 < 0) tmp1 = -tmp1;
					if(tmp2 < 0) tmp2 = -tmp2;
					score2 += tmp1 + tmp2;
				}
				if(score1 > score2) return -1;
				else if(score1 == score2) return 0;
				else return 1;
			};
		};
	}
	
	/**
	 * 总课时数-(课时+禁排数)
	 * 计算结果之间差值和，越小，表示越平均，越优先
	 * @param banCnt
	 * @param dayKeshiCnt
	 * @return
	 * @author Z.R.K
	 */
	public static Comparator<Integer> sortByGroupAndBan(int[] banCnt, int dayKeshiCnt) {
		return new Comparator<Integer>() {
			@Override
			public int compare(Integer param1, Integer param2) {
				int tmp1[] = new int[banCnt.length];
				int tmp2[] = new int[banCnt.length];
				int score1 = 0;
				int score2 = 0;
				for (int i = 0; i < banCnt.length; i++) {
					tmp1[i] = dayKeshiCnt - banCnt[i] - (param1 >> (i * 4) & 15);
					tmp2[i] = dayKeshiCnt - banCnt[i] - (param2 >> (i * 4) & 15);
				}
				
				for (int i = 0; i < banCnt.length; i++) {
					
					for(int x = 0; x < banCnt.length; x++) {
						int tmp = tmp1[i] - tmp1[x];
						if(tmp<0) tmp = -tmp;
						score1 += tmp;
						
						tmp = tmp2[i] - tmp2[x];
						if(tmp<0) tmp = -tmp;
						score2 += tmp;
					}
				}
				if(score1 < score2) return -1;
				else if(score1 == score2) return 0;
				else return 1;
			};
		};
	}
	

	public static Comparator<Integer> sortByComb(Order order){
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer combKey1, Integer combKey2) {
				int cnt1 = 0;
				int cnt2 = 0;
				for(int i=0;i<3;i++){
					if((combKey1 >> (i * 4) & 15) > 0){
						cnt1 += 1;
					}
					if((combKey2 >> (i * 4) & 15) > 0){
						cnt2 += 1;
					}
				}
				
				if(cnt1 > cnt2){
					return order == Order.DESC ? -1 : 1;
				}else if(cnt1 == cnt2){
					return 0;
				}else{
					return order == Order.DESC ? 1 : -1;
				}
			};
		};
	}
	
	public static Comparator<Integer> sortByCombTopSubs(Order order, int[] topSubs){
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer combKey1, Integer combKey2) {
				int cnt1 = 0;
				int cnt2 = 0;
				for (int i = 0; i < 4; i++) {
					if(DataUtil.existsArrayInt(topSubs, (combKey1 >> (i * 4) & 15) - 1)){
						cnt1 += 1;
					}
					if(DataUtil.existsArrayInt(topSubs, (combKey2 >> (i * 4) & 15) - 1)){
						cnt2 += 1;
					}
				}
				
				if(cnt1 > cnt2){
					return order == Order.DESC ? -1 : 1;
				}else if(cnt1 == cnt2){
					return 0;
				}else{
					return order == Order.DESC ? 1 : -1;
				}
			};
		};
	}
	
	
	public static Comparator<Integer> sortByMapping(Map<Integer, List<LayerUserInfo>> subsMapping){
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer subId1, Integer subId2) {
				List<LayerUserInfo> users1 = subsMapping.get(subId1 << 24);
				List<LayerUserInfo> users2 = subsMapping.get(subId2 << 24);
				if(users1 == null) return 1;
				if(users2 == null) return -1;
				int cnt1 = users1.size();
				int cnt2 = users2.size();
				if(cnt1 > cnt2){
					return 1;
				}else if(cnt1 < cnt2){
					return -1;
				}else{
					if(subId1 < subId2){
						return -1;
					}else if(subId1 == subId2){
						return 0;
					}else{
						return 1;
					}
				}
			};
		};
	}
	
	public static Comparator<Long> sortByLong(Order order){
		return new Comparator<Long>(){
			@Override
			public int compare(Long param1, Long param2) {
				if(param1 > param2){
					return order == Order.DESC ? -1 : 1;
				}else if(param1 == param2){
					return 0;
				}else{
					return order == Order.DESC ? 1 : -1;
				}
			};
		};
	}
	
	public static Comparator<Integer> sortByInteger(Order order){
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer param1, Integer param2) {
				if(param1 > param2){
					return order == Order.DESC ? -1 : 1;
				}else if(param1 == param2){
					return 0;
				}else{
					return order == Order.DESC ? 1 : -1;
				}
			};
		};
	}
	
	public static Comparator<Long> sortByLevel(Map<Long, Integer> levelMapping){
		return new Comparator<Long>(){
			@Override
			public int compare(Long subId1, Long subId2) {
				if(subId1 == null) return 1;
				if(subId2 == null) return -1;
				Integer level1 = levelMapping.get(subId1);
				Integer level2 = levelMapping.get(subId2);
				if(level1 == null) level1 = 2;
				if(level2 == null) level2 = 2;
				if(level1 > level2){
					return 1;
				}else if(level1 < level2){
					return -1;
				}else{
					if(subId1 < subId2){
						return -1;
					}else if(subId1 == subId2){
						return 0;
					}else{
						return 1;
					}
				}
			};
		};
	}
	
	public static Comparator<Integer> sortByArray(int[] array){
		return new Comparator<Integer>(){
			@Override
			public int compare(Integer param1, Integer param2) {
				if(array[param1] > array[param2]){
					return 1;
				}else if(array[param1] == array[param2]){
					return 0;
				}else{
					return -1;
				}
			};
		};
	}
	
	public static void main(String[] args) {
		int banCnt[] = new int[5];
		banCnt[0] = 1;
		banCnt[1] = 1;
		banCnt[2] = 1;
		banCnt[3] = 1;
		
		List<Integer> list =  new ArrayList<>();
		list.add(74017);
		list.add(70162);
		list.add(139537);
		list.add(135457);
		list.add(74002);
		list.add(135442);
		list.add(135697);
		list.add(74257);
		list.add(69922);
		
		list.sort(SortUtil.sortByGroup(5));
		list.sort(SortUtil.sortByGroupAndBan(banCnt, 8));
		
		for(Integer group : list){
			for(int i=0;i<5;i++){
				System.out.print((group >> (i*4) & 15));
			}
			System.out.println("---"+group);
		}
		
		list.sort(SortUtil.sortByInteger(Order.ASC));
		for(Integer group : list){
			System.out.println(group);
		}
		
	}
}
