package com.renke.paike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.renke.office.ExcelHelper;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.LayerUserInfo;

public class LayerUtil {
	/**
	 * 初始化数据
	 * @param workbook
	 * @return
	 * @throws Exception
	 * @author Z.R.K
	 */
	public static LayerData getBaseData(Workbook workbook) throws Exception {
		LayerData data = new LayerData();
		data.subs =  new Integer[]{14,15,16,17,18,19,20};
		data.classCnt = 13;
		data.classUserCnt = 40;
		data.userCnt = 490;
		data.subCnt = 7;
		data.users = ExcelHelper.loadUser(workbook, 0, 491, 21
				// 无乐号，无学号，班级，姓名，性别，七门成绩，科目选择情况
				, new int[]{-1, -1, 13, 0, 1,    6,7,8,9,10,11,12,   14,15,16,17,18,19,20});
		data.subs1 = new Integer[3];
		data.subs2 = new Integer[data.subCnt - 3];
		return data;
	}
	
	/**
	 * 预处理基础数据
	 * 统计：组合人数，单科目人数
	 * @param data
	 * @author Z.R.K
	 */
	public static void prepareLayerData(LayerData data)  throws Exception{
		if(data.userCnt / data.classCnt > data.classUserCnt){
			throw new Exception("设置平均人数小于总人数除以班级数！");
		}
		
		// 设置选考科目组合用户映射
		for(LayerUserInfo user : data.users){
			int key = 0;
			int key1 = 0;
			int key2 = 0;
			int key3 = 0;
			for(int j=0;j<data.subs.length;j++){
				if(user.choices[j]){
					if((key >> 16) > 0 && ((key >> 8) & 255)  > 0){
						key += data.subs[j];
						key3 = data.subs[j] << 24;
					}else if((key >> 16) > 0){
						key += (data.subs[j] << 8);
						key2 = data.subs[j] << 24;
					}else{
						key += (data.subs[j] << 16);
						key1 = data.subs[j] << 24;
					}
				}
			}
			DataUtil.putListEle(data.combMapping, key, user);
			DataUtil.putListEle(data.combMapping, key1, user);
			DataUtil.putListEle(data.combMapping, key2, user);
			DataUtil.putListEle(data.combMapping, key3, user);
		}
	}
	
	/**
	 * 根据选考科目人数排序[正序]1,2,3,4,5,6,7
	 * @param data
	 * @throws Exception
	 * @author Z.R.K
	 */
	public static void sortSubsByAsc(LayerData data)  throws Exception{
		data.sortSubs = DataUtil.arrayToList(data.subs);
		data.sortSubs.sort(SortUtil.sortByMapping(data.combMapping));
	}
	
	/**
	 * 根据选考人数排序[菱形]1,3,5,7,6,4,2
	 * @param data
	 * @throws Exception
	 * @author Z.R.K
	 */
	public static void sortSubsByRhomb(LayerData data)  throws Exception{
		sortSubsByAsc(data);
		int length = data.subs.length;
		Integer[] tmp = new Integer[length];
		for(int i=0;i<length;i++){
			int index = i * 2;
			if(index < length){
				tmp[i] = data.sortSubs.get(index);
			}else{
				break;
			}
		}
		
		for (int i = length - 1; i >= 0; i--) {
			int index = (length - i) * 2 - 1;
			if (index < length) {
				tmp[i] = data.sortSubs.get(index);
			} else {
				break;
			}
		}
		data.sortSubs = DataUtil.arrayToList(tmp);
	}

	public static void setSubsClassInfo(LayerData data) {
		int group1Index = 0;
		int group2Index = 0;
		for (int k = 0; k < data.subCnt; k++) {
			int key = data.sortSubs.get(k);
			List<LayerUserInfo> users = data.combMapping.get(key << 24);
			if(users == null) continue ;
			int cnt = users.size();
			int quot = cnt / data.classUserCnt;
			int rem = cnt % data.classUserCnt;
			if(quot > 0 && rem == 0){
				data.subClassCnt.put(key, quot);
				data.subClassUserCnt.put(key, data.classUserCnt);
			}else if(quot == 0){
				data.subClassCnt.put(key, 1);
				data.subClassUserCnt.put(key, cnt);
			}else{
				data.subClassCnt.put(key, quot + 1);
				data.subClassUserCnt.put(key, getAvg(cnt, quot + 1));
			}
			
			// 科目人数
			if(data.subClassCnt.get(key) % 3 == 0){
				if(group2Index < data.subs2.length){
					data.subs2[group2Index ++] = key;
				}else{
					data.subs1[group1Index ++] = key;
				}
			}else{
				if(group1Index < 3){
					data.subs1[group1Index ++] = key;
				}else{
					data.subs2[group2Index ++] = key;
				}
			}
		}
	}
	
	public static Map<Integer, List<LayerUserInfo>> setSplitSubCombMapping(LayerData data , Integer[] filterColKey) {
		Integer[] allKey = data.subs;
		Integer allLength = allKey.length;
		int filterLength = filterColKey.length;
		try {
			Map<Integer, List<LayerUserInfo>> filterMapping = new HashMap<>();
			for (int i = 0; i < allLength; i++) {
				for (int j = i+1; j < allLength; j++) {
					for (int k = j+1; k < allLength; k++) {
						Integer key = (allKey[i] << 16) + (allKey[j] << 8) + allKey[k];
						List<LayerUserInfo> users = data.combMapping.get(key);
						if (users == null){
							continue;
						}
						boolean check[] = new boolean[filterLength];
						// 存在一个key
						for (int f = 0; f < filterLength; f++) {
							int fKey = filterColKey[f];
							if (fKey == allKey[i] || fKey == allKey[j] || fKey == allKey[k]) {
								check[f] = true;
							}
						}

						int otherKey = 0;
						for (int c = 0; c < filterLength; c++) {
							if (check[c]) {
								if (otherKey == 0) {
									otherKey += filterColKey[c];
								} else if ((otherKey >> 8 & 255) == 0) {
									otherKey += (filterColKey[c] << 8);
								} else if ((otherKey >> 16 & 255) == 0) {
									otherKey += (filterColKey[c] << 16);
								}
							}
						}
						DataUtil.putListAll(filterMapping, otherKey, users);
					}
				}
			}
			return filterMapping;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getSubIndexByNo(Integer[] array, Integer subNo) {
		if (subNo == null || array == null) {
			return -1;
		}
		for(int x = 0 ; x < array.length;x ++){
			if(subNo == array[x] && subNo != 0){
				return x;
			}
		}
		return -1;
	}
	
	public static List<Integer> getSubGroup(Integer[] subs, boolean hasZero) {
		int length = subs.length;
		List<Integer> subGroup = new ArrayList<>();
		for (int i1 = 0; i1 <= length; i1++) {
			for (int i2 = (i1 == length ? i1 : i1 + 1); i2 <= length; i2++) {
				for (int i3 = (i2 == length ? i2 : i2 + 1); i3 <= length; i3++) {
					int No1 = 0;
					int No2 = 0;
					int No3 = 0;
					if (i1 != length) {
						No1 = subs[i1];
					}
					if (i2 != length) {
						No2 = subs[i2];
					}
					if (i3 != length) {
						No3 = subs[i3];
					}
					Integer key = (No3 << 16) + (No2 << 8) + No1;
					if(!hasZero && No3 == 0){
					}else{
						subGroup.add(key);
					}
				}
			}
		}
		return subGroup;
	}
	
	public static int getAvg(int cnt, int step){
		int quot = cnt / step;
		int rem = cnt % step;
		if(rem > 0){
			quot += 1;
		}
		return quot;
	}
	
	/**
	 * 根据数量分段
	 * @param cnt		总数
	 * @param quantity	数量
	 * @return
	 * @author Z.R.K
	 */
	public static int[] getSegments(int cnt ,int quantity){
		int[] segs = new int[quantity];
		int quot = cnt / quantity;
		int rem = cnt % quantity;
		for(int i=0;i<quantity;i++){
			segs[i] = quot;
		}
		for(int i=0;i<rem;i++){
			segs[i] += 1;
		}
		return segs;
	}
	
	/**
	 * 取科目对应学生分段班级数[分配班级]
	 * @param data
	 * @param subs
	 * @return
	 * @author Z.R.K
	 */
	public static int[][] getSubBlkClassCnts(LayerData data, Integer[] subs) {
		int length = subs.length;
		boolean[] isAvg = new boolean[length];
		int[][] result = new int[length][3];
		int[][] subBlkClassCnt = new int[length][3];
		int allClassCnt = 0;
		for (int s = 0; s < length; s++) {
			int subClassCnt = data.subClassCnt.get(subs[s]);
			int[] segs = LayerUtil.getSegments(subClassCnt, 3);
			
			isAvg[s] = DataUtil.getMinIndex(segs, true) == 0;
			subBlkClassCnt[s] = segs;
			allClassCnt += subClassCnt;
		}
		int minCnt = allClassCnt / length;
		int maxCnt = allClassCnt % length > 0 ? minCnt + 1 : minCnt;
		
		int[] tmp = new int[length];
		Map<Integer, Integer> tmpMapping = new HashMap<>();
		
		while(true){
			boolean valid = true;
			for (int s = 0; s < length; s++) {
				result[s] = DataUtil.getArrayByOffset(subBlkClassCnt[s], tmp[s]);
			}
			int[] teamClassCnt = new int[length];
			setTeamClassCntByTable(teamClassCnt, result);
			for (int t = 0; t < length; t++) {
				if (teamClassCnt[t] < minCnt || teamClassCnt[t] > maxCnt) {
					valid = false;
				}
			}
			
			if(valid){
				Integer key = 0;
				for (int s = 0; s < length; s++) {
					key += tmp[s] << (s * 4);
				}
				tmpMapping.put(key, 100 - DataUtil.getScoreByArray(getSegClassCnt(result)));
			}
			
			valid = false;
			for (int t = length - 1; t > 0; t--) {
				if (isAvg[t]) {
					continue;
				}
				if (tmp[t] >= 3) {
					tmp[t] = 0;
				} else {
					tmp[t] += 1;
					valid = true;
					break;
				}
			}
			
			if(!valid){
				break;
			}
		}
		Integer key = DataUtil.getMaxKey(tmpMapping);
		for (int s = 0; s < length; s++) {
			result[s] = DataUtil.getArrayByOffset(subBlkClassCnt[s], key >> (s * 4) & 15);
		}
		tmpMapping.clear();
		return result;
	}


	/**
	 * A1B2C3/B1C2A3/C1A2B3
	 * A1B2C3/B1C2A4/C1A3B4/A2B3C4
	 * @param array
	 * @return
	 * @author Z.R.K
	 */
	private static void setTeamClassCntByTable(int[] teamClassCnt, int[][] array){
		if(teamClassCnt.length == 3){
			teamClassCnt[0] = array[0][0] + array[1][1] + array[2][2];
			teamClassCnt[1] = array[0][1] + array[1][2] + array[2][0];
			teamClassCnt[2] = array[0][2] + array[1][0] + array[2][1];
		}else{
			teamClassCnt[0] = array[0][0] + array[1][1] + array[2][2];
			teamClassCnt[1] = array[0][1] + array[1][2] + array[3][0];
			teamClassCnt[2] = array[0][2] + array[2][0] + array[3][1];
			teamClassCnt[3] = array[1][0] + array[2][1] + array[3][2];
		}
	}
	
	private static int[] getSegClassCnt(int[][] array){
		int[] segClassCnt = new int[3];
		for(int i=0;i<array.length;i++){
			segClassCnt[0] += array[i][0];
			segClassCnt[1] += array[i][1];
			segClassCnt[2] += array[i][2];
		}
		return segClassCnt;
	}
	
}
