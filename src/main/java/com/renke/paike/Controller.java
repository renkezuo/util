package com.renke.paike;


import org.apache.poi.ss.usermodel.Workbook;

import com.renke.office.ExcelHelper;
import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.UserBlock;

public class Controller {
	
	
	public static void main(String[] args) throws Exception {
		Workbook workbook = ExcelHelper.getWorkbook("C:/Users/Administrator/Desktop/临时文件/萧山9中分班结果 1022.xlsx");
		LayerData data = LayerUtil.getBaseData(workbook);
		
		// 检查班级数，设置选考科目组合用户映射
		LayerUtil.prepareLayerData(data);
		// 打印组合信息
		LayerPrint.printCombCnt(data.combMapping);
		// 根据科目人数正序排列
		LayerUtil.sortSubsByAsc(data);
//		LayerUtil.sortSubsByRhomb(data);
		// 设置科目班级数，科目班级平均人数，科目分组信息
		LayerUtil.setSubsClassInfo(data);
		// 打印排序后科目信息[人数，班级数]
		LayerPrint.printSortSubs(data);
		// 处理科目
		disposeSubs(data, data.subs1);
		// 计算总人数
		for(UserBlock block : data.blocks){
			for(int use : block.combUse){
				block.cnt += use;
			}
		}
		TeamCell[][] cells = new TeamCell[3][6];
		cells[0][0] = new TeamCell(); // subIndex,combs
		
		int classCnt = 0;
		classCnt += LayerUtil.getAvg(data.blocks[0].subUseCnt[0], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].subUseCnt[1], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].subUseCnt[2], data.classUserCnt);
		System.out.println("team1-选考："+classCnt);
		classCnt += LayerUtil.getAvg(data.blocks[0].cnt - data.blocks[0].subUseCnt[0], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].cnt - data.blocks[1].subUseCnt[1], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].cnt - data.blocks[2].subUseCnt[2], data.classUserCnt);
		System.out.println("team1:"+classCnt);
		
		classCnt = 0;
		classCnt += LayerUtil.getAvg(data.blocks[0].subUseCnt[2], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].subUseCnt[0], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].subUseCnt[1], data.classUserCnt);
		System.out.println("team2-选考："+classCnt);
		
		classCnt += LayerUtil.getAvg(data.blocks[0].cnt - data.blocks[0].subUseCnt[2], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].cnt - data.blocks[1].subUseCnt[0], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].cnt - data.blocks[2].subUseCnt[1], data.classUserCnt);
		System.out.println("team2:"+classCnt);
		
		classCnt = 0;
		classCnt += LayerUtil.getAvg(data.blocks[0].subUseCnt[1], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].subUseCnt[2], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].subUseCnt[0], data.classUserCnt);
		System.out.println("team3-选考："+classCnt);
		classCnt += LayerUtil.getAvg(data.blocks[0].cnt - data.blocks[0].subUseCnt[1], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[1].cnt - data.blocks[1].subUseCnt[2], data.classUserCnt);
		classCnt += LayerUtil.getAvg(data.blocks[2].cnt - data.blocks[2].subUseCnt[0], data.classUserCnt);
		System.out.println("team3:"+classCnt);
		
		
		
		// 检查分班情况，调整分组
//		for(UserBlock block : data.blocks){
//			data.blocks[0].subUseCnt[0];
			
//		}
		
		
		
		
		
		
		disposeSubs(data, data.subs2);
		
//		subBlkClassCnt = LayerUtil.getSubBlkClassCnts(data, data.subs2);
//		
//		LayerPrint.printBlkClassCnt(data.subs2, subBlkClassCnt);
//		LayerPrint.printCombCnt(data.combMappingSubs2);
		
		// 微调功能，判断学考选考
		
		// 根据组合人数，单个调整
		
		// 检查所有对象人数是否比原人数更合理
		
		
		// 一二三志愿分配
		// 人数平均
		
		
		// 
		
		
//		data.subClassCnt;
		
		// 
		
		
		
//		for(int subNo : data.subs1){
//			System.out.println(ExcelHelper.getTitle(subNo) + "---" + data.cntMapping.get(subNo << 24));
//		}
		
//		int[] array = new int[3];
//		int score = getScoreByAvg(array);
		
//		while(it.hasNext()){
//			Map.Entry<Integer, Integer> entry = it.next();
//			System.out.println(ExcelHelper.getConcatTitle(entry.getKey()) + "	" + entry.getValue());
//		}
//		for(int subNo : data.subs2){
//			System.out.println(ExcelHelper.getTitle(subNo)+":"+data.mapping.get(subNo << 24));
//		}
		
		// 处理第二批科目
//		Map<Integer, Integer> cntMapping2 = ExcelService.getCountMapping(data.subs, data.subs2, data.mapping);
		
		
//		int[] filterKeys = new int[]{17,19,15};
//		printResult(keys, count(workbook, 1, 213, 9, keys));
//		printResult(keys, filterKeys, count(workbook, 1, 213, 9, keys));
//		ExcelService.getCountMapping(data.subs, filterKeys, data.cntMapping);
	}

	private static void disposeSubs(LayerData data, Integer[] subs) {
		// 设置科目分组后，选考科目组合用户映射
		data.combMappingSubs = LayerUtil.setSplitSubCombMapping(data, subs);
		LayerPrint.printCombCnt(data.combMappingSubs);
		// 统计科目组合情况
		data.subsCombs = LayerUtil.getSubGroup(subs, true);
		// 根据科目数排序
		data.subsCombs.sort(SortUtil.sortByInteger(Order.DESC));
		LayerPrint.printComb(data.subsCombs);
		
//		Integer[] subs = data.sortSubs.toArray(new Integer[data.subCnt]);
//		List<Integer> allComb = LayerUtil.getSubGroup(subs, false);
//		LayerPrint.printComb(allComb);
//		LayerPrint.printSplitSubs(data);
		// 初始化userBlock信息
		initUserBlocks(data, subs);
		// 初始化comb信息
		initCombs(data, subs);
		// 分配组合
		allocationComb(data);
		LayerPrint.printComBlkCnt(data.combs, data.blocks);
	}

	public static void allocationComb(LayerData data) {
		int subsCombSize = data.subsCombs.size();
		for(int c=0;c<subsCombSize;c++){
			Comb comb = data.combs[c];
			// 所有科目，取block最少人数科目
			int cnts[] = new int[3];
			for (int b = 0; b < 3; b++) {
				UserBlock block = data.blocks[b];
				int minCnt = 10000;
				for (int s : comb.subIndex) {
					if (s != -1) {
						if (block.subNeedCnt[s] < minCnt) {
							minCnt = block.subNeedCnt[s];
						}
					}
				}
				if(minCnt == 10000){
					minCnt = 0;
				}
				cnts[b] = minCnt;
			}
			
			// 如果分配最后，不存在大于2的分组，则终止循环
			while (hasNumber(cnts, 2)) {
				int sumCnt = DataUtil.sum(cnts);
				if (comb.cnt - sumCnt >= 0) {
					comb.cnt -= sumCnt;
					for (int b = 0; b < 3; b++) {
						UserBlock block = data.blocks[b];
						block.combUse[c] += cnts[b];
						comb.use[b] += cnts[b];
						for (int s : comb.subIndex) {
							if (s != -1) {
								block.subNeedCnt[s] -= cnts[b];
								block.subUseCnt[s] += cnts[b];
							}
						}
					}
				} else {
					for (int x = 0; x < 3; x++) {
						cnts[x] = cnts[x] >> 1;
					}
				}
			}
			
			if(!hasNumber(cnts, 1)){
				for (int b = 0; b < 3; b++) {
					cnts[b] = 1;
				}
			}
			
			while(comb.cnt > 0){
				for (int b = 0; b < 3; b++) {
					UserBlock block = data.blocks[b];
					if(cnts[b] > 0 && comb.cnt > 0){
						block.combUse[c] += 1;
						comb.use[b] += 1;
						comb.cnt -= 1;
						for (int s : comb.subIndex) {
							if (s != -1) {
								block.subNeedCnt[s] -= 1;
								block.subUseCnt[s] += 1;
							}
						}
					}
				}
			}
		}
	}


	/**
	 * 初始化学生分场情况
	 * 首先班级分块，根据块中班级数，计算块中学生数
	 * @param data
	 * @param subs
	 * @author Z.R.K
	 */
	private static void initUserBlocks(LayerData data, Integer[] subs) {
		int subsCombSize = data.subsCombs.size();
		int subsLength = subs.length;
		int[][] subBlkClassCnt = LayerUtil.getSubBlkClassCnts(data, subs);
		for (int b = 0; b < 3; b++) {
			UserBlock block = new UserBlock();
			block.combUse = new int[subsCombSize];
			block.subClassCnt = new int[subsLength];
			block.subNeedCnt = new int[subsLength];
			block.subUseCnt = new int[subsLength];
			data.blocks[b] = block;
			for (int s = 0; s < subsLength; s++) {
				block.subClassCnt[s] = subBlkClassCnt[s][b];
				block.subNeedCnt[s] = data.subClassUserCnt.get(subs[s]) * block.subClassCnt[s];
			}
		}
		LayerPrint.printBlkClassCnt(subs, subBlkClassCnt);
	}

	/**
	 * 
	 * @param data
	 * @param subs
	 * @author Z.R.K
	 */
	private static void initCombs(LayerData data, Integer[] subs) {
		int subsCombSize = data.subsCombs.size();
		data.combs = new Comb[subsCombSize];
		for (int i = 0; i < subsCombSize; i++) {
			Integer combKey = data.subsCombs.get(i);
			int[] subIndex = new int[3];
			for (int s = 0; s < 3; s++) {
				Integer subNo = combKey >> (8 * s) & 255;
				subIndex[s] = LayerUtil.getSubIndexByNo(subs, subNo);
			}
			
			int[] tmp = new int[3];
			int x = 0;
			for(int s=0;s<subs.length;s++){
				for(int t=0;t<3;t++){
					if(s == subIndex[t]){
						tmp[x ++] = s;
					}
				}
			}
			
			
			Comb comb = new Comb();
			comb.combKey = combKey;
			comb.users = data.combMappingSubs.get(combKey);
			if (comb.users != null) {
				comb.cnt = comb.users.size();
			}
			comb.subIndex = subIndex;
			data.combs[i] = comb;
		}
	}
	
	public static boolean hasNumber(int[] numbers, int number) {
		for(int x : numbers){
			if(x >= number) {
				return true;
			}
		}
		return false;
	}

}
