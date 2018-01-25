package com.renke.paike;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.renke.paike.model.LayerUserInfo;


public class SortUtil {
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
}
