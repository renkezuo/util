package com.renke.paike.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renke.paike.model.LayerData;
import com.renke.paike.model.LayerUserInfo;

public class LayerSubUtil {
	public static void initSortSubs(LayerData data, boolean isChoice) {
		for (LayerUserInfo user : data.users) {
			for (int j = 0; j < data.subs.length; j++) {
				if (user.choices[j] == isChoice) {
					data.choiceSubCnt[j] += 1;
				}
			}
		}
		for (int j = 0; j < data.subs.length; j++) {
			data.sortSubs.add(j);
		}
		
		data.sortSubs.sort(SortUtil.sortByArray(data.choiceSubCnt));
	}
	
	public static int[] getSubsBySortSub(LayerData data) {
		int[] subs = new int[data.subCnt];
		for (int i = 0; i < data.subCnt; i++) {
			subs[i] = data.sortSubs.get(i);
		}
		return subs;
	}
	
	/**
	 * 根据选考人数排序[菱形]1,3,5,7,6,4,2
	 * @param data
	 * @throws Exception
	 * @author Z.R.K
	 */
	public static void sortSubsByRhomb(LayerData data){
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
	
	public static int[] getTopSub(int[] subs) {
		int[] topSubs = new int[4];
		topSubs[0] = subs[0];
		topSubs[1] = subs[1];
		topSubs[2] = subs[subs.length - 2];
		topSubs[3] = subs[subs.length - 1];
		return topSubs;
	}
	
	
	public static void initCombMapping(LayerData data, boolean isChoice) {
		for (LayerUserInfo user : data.users) {
			Integer key = 0;
			for (int subIndex : data.sortSubs) {
				if (user.choices[subIndex] == isChoice) {
					int val = subIndex + 1;
					key = key << 4;
					key += val;
					DataUtil.putListEle(data.subMapping, val, user);
				}
			}
			
			DataUtil.putListEle(data.combMapping, key, user);
		}
	}
	
	public static List<Integer> getSubGroup4(int[] subs) {
		int length = subs.length;
		List<Integer> subGroup = new ArrayList<>();
		for (int i1 = 0; i1 < length; i1++) {
			for (int i2 = i1 + 1; i2 < length; i2++) {
				for (int i3 = i2 + 1; i3 < length; i3++) {
					for (int i4 = i3 + 1; i4 < length; i4++) {
						int No1 = subs[i1] + 1;
						int No2 = subs[i2] + 1;
						int No3 = subs[i3] + 1;
						int No4 = subs[i4] + 1;
						Integer key = (No1 << 12) + (No2 << 8) + (No3 << 4) + No4;
						subGroup.add(key);
					}
				}
			}
		}
		return subGroup;
	}
	
	
	public static List<Integer> getSubGroup(int[] subs) {
		int length = subs.length;
		List<Integer> subGroup = new ArrayList<>();
		for (int i1 = 0; i1 < length; i1++) {
			for (int i2 = i1 + 1; i2 < length; i2++) {
				for (int i3 = i2 + 1; i3 < length; i3++) {
					int No1 = subs[i1] + 1;
					int No2 = subs[i2] + 1;
					int No3 = subs[i3] + 1;
					Integer key = (No1 << 8) + (No2 << 4) + No3;
					subGroup.add(key);
				}
			}
		}
		return subGroup;
	}
	
	public static void setSubClassCntBlend(LayerData data) {
		for (int subIndex : data.sortSubs) {
			int cnt = data.choiceSubCnt[subIndex];
			int studyCnt = data.userCnt - cnt;
			int classCnt = LayerUtil.getAvg(cnt, data.classUserCnt + data.offset);
			int studyClassCnt = LayerUtil.getAvg(studyCnt, data.classUserCnt + data.offset);
			data.subClassCnt[subIndex] = classCnt;
			data.studySubClassCnt[subIndex] = studyClassCnt;
			if(classCnt + studyClassCnt < data.needClassCnt){
				int rem = LayerUtil.getRem(cnt, data.classUserCnt, classCnt);
				int studyRem = LayerUtil.getRem(studyCnt, data.classUserCnt, studyClassCnt);
				if(rem >= studyRem){
					data.subClassCnt[subIndex] += 1;
				}else{
					data.studySubClassCnt[subIndex] += 1;
				}
			}
		}
	}
	
	/**
	 * 配置各科目需要的班级数
	 * @param data
	 * @param teamSize
	 * @author Z.R.K
	 * @throws Exception 
	 */
	public static void setSubClassCntSingle(LayerData data, int teamSize) throws Exception {
		// 检查直接分班之后多出的班级数
		calcSubClassCntByUserCnt(data, data.classUserCnt + data.offset);
		int countCnt = DataUtil.sum(data.subClassCnt);
		
		while ( countCnt > data.classCnt * teamSize ){
			data.classCnt += 1;
		}
		
		if(data.classCnt > data.needClassCnt){
			throw new Exception("场地不够！");
		}
		
		calcSubClassCntByUserCnt(data, data.classUserCnt);
		
		while(countCnt > data.classCnt * teamSize){
			int minRem = data.classUserCnt;
			int minIndex = -1;
			for (int subIndex : data.sortSubs) {
				int cnt = data.choiceSubCnt[subIndex];
				int rem = LayerUtil.getRem(cnt, data.classUserCnt, data.subClassCnt[subIndex] - 1);
				if(rem < minRem){
					minRem = rem;
					minIndex = subIndex;
				}
			}
			if(minIndex == -1){
				break;
			}
			
			if(minRem > data.offset){
				break;
			}
			
			data.subClassCnt[minIndex] -= 1;
			countCnt -= 1;
		}
	}

	public static void calcSubClassCntByUserCnt(LayerData data, int classUserCnt) {
		for (int subIndex : data.sortSubs) {
			int cnt = data.choiceSubCnt[subIndex];
			int classCnt = LayerUtil.getAvg(cnt, classUserCnt);
			data.subClassCnt[subIndex] = classCnt;
		}
	}
	
	public static void splitSubs(LayerData data){
		int minScore = 4;
		int subs1Index = 0;
		int subs2Index = 0;
		for (int i = 0; i < data.subCnt; i++) {
			for (int j = i + 1; j < data.subCnt; j++) {
				for (int k = j + 1; k < data.subCnt; k++) {
					int sub1 = data.sortSubs.get(i);
					int sub2 = data.sortSubs.get(j);
					int sub3 = data.sortSubs.get(k);
					int sub1ClassCnt = data.subClassCnt[sub1] + data.subClassCnt[sub2] + data.subClassCnt[sub3];
					int sub2ClassCnt = 0;
					int times = 0;
					int subs2Tmp = 0;
					for(int m=0;m<data.subCnt;m++){
						int sub4 = data.sortSubs.get(m);
						if(sub4!=sub1 && sub4!=sub2 && sub4!=sub3){
							sub2ClassCnt += data.subClassCnt[sub4];
							subs2Tmp += sub4 << (times * 4);
							times ++;
						}
					}
					int rem1 = sub1ClassCnt % 3;
					int rem2 = sub2ClassCnt % (data.subCnt - 3);
					if(rem1 + rem2 < minScore){
						minScore = rem1 + rem2;
						subs1Index = (sub3 << 8) + (sub2 << 4) + sub1; 
						subs2Index = subs2Tmp;
					}
				}
			}
		}
		
		for (int i = 0; i < 3; i++) {
			data.subs1[i] = subs1Index >> (i * 4) & 15;
		}
		for (int i = 0; i < data.subCnt - 3; i++) {
			data.subs2[i] = subs2Index >> (i * 4) & 15;
		}
	}
	
	public static void resetSubsCombMapping(LayerData data , int[] filterColKey) {
		data.combSubsMapping = new HashMap<>();
		Integer allLength = data.subCnt;
		int filterLength = filterColKey.length;
		for (int i = 0; i < allLength; i++) {
			for (int j = i+1; j < allLength; j++) {
				for (int k = j+1; k < allLength; k++) {
					Integer key1 = data.sortSubs.get(i) + 1;
					Integer key2 = data.sortSubs.get(j) + 1;
					Integer key3 = data.sortSubs.get(k) + 1;
					Integer key = (key1 << 8) + (key2 << 4) + key3;
					List<LayerUserInfo> users = data.combMapping.get(key);
					if (users == null){
						continue;
					}
					boolean check[] = new boolean[filterLength];
					// 存在一个key
					for (int f = 0; f < filterLength; f++) {
						int fKey = filterColKey[f] + 1;
						if (fKey == key1 || fKey == key2 || fKey == key3) {
							check[f] = true;
						}
					}

					int otherKey = 0;
					for (int f = 0; f < filterLength; f++) {
						if (check[f]) {
							int fKey = f + 1;
							if((otherKey >> 8 & 15) == 0){
								otherKey += fKey << 8;
							}else if((otherKey >> 4 & 15) == 0){
								otherKey += fKey << 4;
							}else{
								otherKey += fKey;
							}
						}
					}
					DataUtil.putListAll(data.combSubsMapping, otherKey, users);
				}
			}
		}
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
	
	public static List<Integer> getSubGroup(int[] subs, boolean hasZero) {
		int length = subs.length;
		List<Integer> subGroup = new ArrayList<>();
		for (int i1 = 0; i1 <= length; i1++) {
			for (int i2 = (i1 == length ? i1 : i1 + 1); i2 <= length; i2++) {
				for (int i3 = (i2 == length ? i2 : i2 + 1); i3 <= length; i3++) {
					int No1 = 0;
					int No2 = 0;
					int No3 = 0;
					if (i1 != length) {
						No1 = i1 + 1;
					}
					if (i2 != length) {
						No2 = i2 + 1;
					}
					if (i3 != length) {
						No3 = i3 + 1;
					}
					Integer key = (No1 << 8) + (No2 << 4) + No3;
					if(!hasZero && No3 == 0){
					}else{
						subGroup.add(key);
					}
				}
			}
		}
		return subGroup;
	}

	/**
	 * 取科目对应学生分段班级数[分配班级]
	 * bug  3 6 6 9 
	 * @param data
	 * @param subs
	 * @return
	 * @author Z.R.K
	 */
	public static int[][] getSubBlkClassCnts(int classCnt[], int[] subs) {
		int length = subs.length;
		boolean[] isAvg = new boolean[length];
		int[][] result = new int[length][3];
		int[][] subBlkClassCnt = new int[length][3];
		int allClassCnt = 0;
		for (int s = 0; s < length; s++) {
			int subClassCnt = classCnt[subs[s]];
			// 将班级总数平均分为3段，从大至小排序
			int[] segs = LayerUtil.getSegments(subClassCnt, 3);
			// minIndex = 0，表示班级数平均
			isAvg[s] = DataUtil.getMinIndex(segs, true) == 0;
			subBlkClassCnt[s] = segs;
			allClassCnt += subClassCnt;
		}
		// 分team后，班级数最小和最大
		int minCnt = allClassCnt / length;
		int maxCnt = allClassCnt % length > 0 ? minCnt + 1 : minCnt;
		
		int[] tmp = new int[length];
		Map<Integer, Integer> tmpMapping = new HashMap<>();

		int[] teamClassCnt = new int[length];
		while(true){
			boolean valid = true;
			// 重排科目班级数顺序
			for (int s = 0; s < length; s++) {
				result[s] = DataUtil.getArrayByOffset(subBlkClassCnt[s], tmp[s]);
			}
			// 根据记录team班级数
			setTeamClassCntByTable(teamClassCnt, result);
			for (int t = 0; t < length; t++) {
				// 班级数小于最小，或大于最大，则重新安排顺序
				if (teamClassCnt[t] < minCnt || teamClassCnt[t] > maxCnt) {
					valid = false;
				}
			}
			
			if(valid){
				Integer key = 0;
				for (int s = 0; s < length; s++) {
					key += tmp[s] << (s * 4);
				}
				// 记录得分
				tmpMapping.put(key, 100 - DataUtil.getScoreByArray(LayerUtil.getSegClassCnt(result)));
			}
			
			valid = false;
			for (int s = length - 1; s > 0; s--) {
				// 如果所有的isAvg[s]都为true时，表示全部平均，直接跳出
				if (isAvg[s]) {
					continue;
				}
				// 如果所有的tmp[s]都为3时，表示全部循环完毕，则跳出大循环
				if (tmp[s] >= 3) {
					tmp[s] = 0;
				} else {
					tmp[s] += 1;
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
	 * 得到学考班级数分布，小于classCnt时，智能补充
	 * @param data
	 * @param subs
	 * @param subBlkClassCnt
	 * @return
	 * @author Z.R.K
	 */
	public static int[][] getStudySubBlkClassCnts(LayerData data, int[] subs, int[][] subBlkClassCnt) {
		int subsLength = subs.length;
		int[][] studySubBlkClassCnt = new int[subsLength][3];
		for (int s = 0; s < subsLength; s++) {
			// 将班级总数平均分为3段，从大至小排序
			studySubBlkClassCnt[s] = LayerUtil.getSegments(data.studySubClassCnt[subs[s]], 3);
		}
		int[] rem = new int[subsLength];
		int[] maxClassCnt = new int[subsLength];
		for (int s = 0; s < subsLength; s++) {
			for (int b = 0; b < 3; b++) {
				int[] result = DataUtil.getArrayByOffset(studySubBlkClassCnt[s], b);
				int minCnt = 100;
				int maxCnt = 0;
				for (int i = 0; i < 3; i++) {
					int cnt = result[i] + subBlkClassCnt[s][i];
					if (cnt < minCnt) {
						minCnt = cnt;
					}
					if (cnt > maxCnt) {
						maxCnt = cnt;
					}
				}
				if (maxCnt - minCnt <= 1) {
					studySubBlkClassCnt[s] = result;
					rem[s] = maxCnt - minCnt;
					maxClassCnt[s] = maxCnt;
				}
			}
		}
		
		return studySubBlkClassCnt;
	}
	

	/**
	 * A1B2C3/B1C2A3/C1A2B3
	 * A1B2C3/B1C2A4/C1A3B4/A2B3C4
	 * @param array
	 * @return
	 * @author Z.R.K
	 */
	public static void setTeamClassCntByTable(int[] teamClassCnt, int[][] array){
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
}
