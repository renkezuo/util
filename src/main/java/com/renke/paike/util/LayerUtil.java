package com.renke.paike.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.renke.office.ExcelHelper;
import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.SplitKlass;
import com.renke.paike.model.UserBlock;
import com.renke.paike.model.UserBlockHope;

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
		data.subs =  new Long[]{4L,5L,6L,7L,8L,9L,19L};
		data.needClassCnt = 19;
		data.classUserCnt = 40;
		data.userCnt = 490;
		data.subCnt = 7;
		data.users = ExcelHelper.loadUser(workbook, 0, 491, 21
				// 无乐号，无学号，班级编号，姓名，性别，七门成绩，科目选择情况
				, new int[]{-1, -1, 13, 0, 1,    6,7,8,9,10,11,12,   14,15,16,17,18,19,20});
		
		// 第二个
//		data.subs =  new Integer[]{3,4,5,6,7,8}; // 第二个
//		data.needClassCnt = 6;// 第二个
//		data.classUserCnt = 40;// 第二个
//		data.userCnt = 212;// 第二个
//		data.subCnt = 6; // 第二个
//		data.users = ExcelHelper.loadUser(workbook, 0, 213, 9
//				// 无乐号，无学号，班级编号，姓名，性别，七门成绩，科目选择情况
//				, new int[]{-1, 0, -1, 2, -1,    -1,-1,-1,-1,-1,-1,-1,   3,4,5,6,7,8,-1});
		
		
		data.classCnt = getAvg(data.userCnt, data.classUserCnt);
		data.offset = data.classUserCnt / 10;
		data.choiceSubCnt = new int[data.subCnt];
		data.subClassCnt = new int[data.subCnt];
		data.studySubClassCnt = new int[data.subCnt];
		data.subs1 = new int[3];
		data.subs2 = new int[data.subCnt - 3];
		data.scoreInterval = 10;
		data.scoreSegLength = 15;
		data.layerType = 2;
		return data;
	}
	
	public static void checkData(LayerData data) throws Exception{
		if(data.userCnt / data.needClassCnt > data.classUserCnt){
			throw new Exception("设置平均人数小于总人数除以班级数！");
		}
	}
	
	public static void layerBlend(LayerData data) {
		LayerSubUtil.initSortSubs(data, true);
		LayerSubUtil.initCombMapping(data, true);
		LayerPrint.printSubs(data.sortSubs, data.choiceSubCnt, data.subs);
		LayerPrint.printAllComb(data);
		// 设置选考，学考科目班级数
		LayerSubUtil.setSubClassCntBlend(data);
		// 科目分组信息
		LayerSubUtil.splitSubs(data);
		
		LayerPrint.printSubs(data);
		// 打印排序后科目信息[人数，班级数]
		LayerPrint.printSortSubs(data);
		// 处理科目列表1
		disposeSubs(data, data.subs1, 1);
		// 处理科目列表2
		disposeSubs(data, data.subs2, 4);
	}
	
	/**
	 * 单独分班
	 * @param data
	 * @param isChoice		true选考/false学考
	 * @throws Exception
	 * @author Z.R.K
	 */
	public static List<SplitKlass> layerIndep(LayerData data, boolean isChoice) throws Exception {
		// 7科目时，学生有四门学考，故分为四组
		int teamSize = isChoice ? 3 : (data.subCnt - 3);
		
		// 科目排序
		sortSubs(data, isChoice);
		
		// 根据科目顺序，给组合排序
		sortCombBySubs(data, isChoice, teamSize);
		
		// 有序处理组合，分配组合
		allocationComb(data, teamSize);
		
		if(teamSize == 4){
			LayerPrint.printComBlkCnt4(data);
		}else{
			LayerPrint.printComBlkCnt3(data);
		}
		
		// 调整组合，达到最优，产生结果表格数据
		TeamTable table = adjustAndReturn(data, teamSize, isChoice);
		
		if(teamSize == 4){
			LayerPrint.printComBlkCnt4(data);
		}else{
			LayerPrint.printComBlkCnt3(data);
		}
		
		// 分配学生到班
		LayerAllocationUtil.allocationUser(data, table);
		
		LayerPrint.printCellUsers(data, table);
		LayerPrint.printTeamTable(data, table);
		LayerPrint.printTeamClassUsers(data, table);
		
		List<SplitKlass> klasses = getLayerKlasses(table);
		
		table.clear();
		
		return klasses;
	}

	private static List<SplitKlass> getLayerKlasses(TeamTable table) {
		List<SplitKlass> klasses = new ArrayList<>();
		for(List<TeamCell> cells : table.teamCells){
			for(TeamCell cell: cells){
				klasses.addAll(cell.klasses);
			}
		}
		return klasses;
	}
	
	private static void sortSubs(LayerData data, boolean isChoice) {
		// 根据科目选择人数排序[正序]
		LayerSubUtil.initSortSubs(data, isChoice);
		data.ascSubs = LayerSubUtil.getSubsBySortSub(data);
		
		// 再次排序[菱形排序]
		LayerSubUtil.sortSubsByRhomb(data);
		data.rhombSubs = LayerSubUtil.getSubsBySortSub(data);
		
		// 取前两个科目和后两个科目
		data.topSubs = LayerSubUtil.getTopSub(data.rhombSubs);
	}

	private static void sortCombBySubs(LayerData data, boolean isChoice, int teamSize) {
		// 根据科目排序，设置组合及组合对应的学生，区分学考(false)和选考(true)
		LayerSubUtil.initCombMapping(data, isChoice);
		
		LayerPrint.printSubs(data.sortSubs, data.choiceSubCnt, data.subs);
		
		if(teamSize == 4){
			LayerPrint.printAllComb4(data);
		}else{
			LayerPrint.printAllComb(data);
		}
		// 有序科目数组
		// 设置全部组合
		if(teamSize == 4){
			data.subsCombs = LayerSubUtil.getSubGroup4(data.rhombSubs);
			LayerPrint.printComb4(data.subsCombs, data.rhombSubs, data.subs);
		}else{
			data.subsCombs = LayerSubUtil.getSubGroup(data.rhombSubs);
			LayerPrint.printComb3(data.subsCombs, data.rhombSubs, data.subs);
		}
		data.combSubsMapping = data.combMapping;
		
		// 给data.subsCombs排序[存在topSubs的优先]
		data.subsCombs.sort(SortUtil.sortByCombTopSubs(Order.DESC, data.topSubs));
	}
	

	private static void allocationComb(LayerData data, int teamSize) throws Exception {
		// 设置科目班级数(当科目班级数之和大于设定班级数*teamSize时，调整科目班级数)
		LayerSubUtil.setSubClassCntSingle(data, teamSize);
		// 打印排序后科目信息[人数，班级数]
		LayerPrint.printSortSubs(data);
		
		// 初始化用户块
		initUserBlocks(data, teamSize);
		
		// 初始化组合列表
		initCombs(data, teamSize);
		
		// 分配组合人数，优先处理存在topSubs的组合
		LayerAllocationUtil.allocationComb(data, teamSize);
	}
	
	private static TeamTable adjustAndReturn(LayerData data, int teamSize, boolean isChoice){
		TeamTable table = new TeamTable();
		table.initTable(data, LayerTeamUtil.getTeamCellsAlone(teamSize, data.subCnt, isChoice));
		// 初始化表格[包含：单元格班级数，单元格相关偏移量，单元格限定班级数]
		for (int i = 0; i < 100; i++) {
			table.adjustCombUse(1);
		}
		return table;
	}
	
	private static void disposeSubs(LayerData data, int[] subs, int startTeamId) {
		// 设置科目分组后，选考科目组合用户映射
		LayerSubUtil.resetSubsCombMapping(data, subs);
		LayerPrint.printCombCnt(data.combSubsMapping, subs, data.subs);
		// 统计科目组合情况
		data.subsCombs = LayerSubUtil.getSubGroup(subs, true);
		// 根据科目数排序
		data.subsCombs.sort(SortUtil.sortByComb(Order.DESC));
		LayerPrint.printComb(data.subsCombs, subs, data.subs);
		
		// 初始化userBlock信息
		initUserBlocks(data, subs);
		// 初始化comb信息
		initCombs(data, subs);
		// 分配组合
		LayerAllocationUtil.allocationComb(data);
		
		LayerPrint.printComBlkCnt(data, subs);
		
		Manager manager = new Manager(data, subs.length);
		manager.register(LayerTeamUtil.getTeamCells(startTeamId, subs.length));
		LayerPrint.printManager(manager);
		int t=4;
		for (; t > 0; t--) {
			for (int i = 0; i < 15; i++) {
				manager.tryChangeCombUse(t, 4);
				manager.tryChangeCombUse(t, 3);
				manager.tryChangeCombUse(t, 2);
				manager.tryChangeCombUse(t, 1);
			}
		}
		for (int i = 0; i < 15; i++) {
			manager.tryChangeCombUse(4, 4);
			manager.tryChangeCombUse(4, 3);
			manager.tryChangeCombUse(4, 2);
			manager.tryChangeCombUse(4, 1);
		}
		LayerPrint.printComBlkCnt(data, subs);
		LayerPrint.printManager(manager);
		
		manager.clear();
	}
	
	
	private static void initUserBlocks(LayerData data, int teamSize) {
		int subsLength = data.topSubs.length;
		int[][] subTeamClassCnt = LayerTeamUtil.getSubTeamClassCnt(data, teamSize);
		data.hopeBlocks = new UserBlockHope[teamSize];
		for (int b = 0; b < teamSize; b++) {
			UserBlockHope block = new UserBlockHope();
			block.wishSubNeedClassCnt = new int[subsLength][teamSize];
			block.wishSubNeedUserCnt = new int[subsLength][teamSize];
			block.wishSubUserCnt = new int[subsLength][teamSize];
			data.hopeBlocks[b] = block;
			for (int w = 0; w < teamSize; w++) {
				int teamIndex = ((teamSize - w) % teamSize + b) % teamSize;
				for (int s = 0; s < subsLength; s++) {
					block.wishSubNeedClassCnt[s][w] = subTeamClassCnt[s][teamIndex];
					block.wishSubNeedUserCnt[s][w] = data.classUserCnt * block.wishSubNeedClassCnt[s][w];
				}
			}
		}
		LayerPrint.printTeamClassCnt(data.topSubs, subTeamClassCnt, data.subs);
	}
	
	/**
	 * 
	 * @param data
	 * @param teamSize
	 * @author Z.R.K
	 */
	private static void initCombs(LayerData data, int teamSize) {
		int subsCombSize = data.subsCombs.size();
		data.combs = new Comb[subsCombSize];
		for (int i = 0; i < subsCombSize; i++) {
			Integer combKey = data.subsCombs.get(i);
			int[] subIndexes = new int[teamSize];
			for (int s = 0; s < teamSize; s++) {
				int subIndex = (combKey >> (4 * s) & 15) - 1;
				subIndexes[teamSize - s - 1] = subIndex;
			}
			
			Comb comb = new Comb();
			comb.use = new int[teamSize];
			comb.combKey = combKey;
			comb.users = data.combSubsMapping.get(combKey);
			if (comb.users == null) {
				comb.users = new ArrayList<>();
			}
			comb.cnt = comb.users.size();
			comb.subIndexes = subIndexes;
			comb.combIndex = i;
			comb.blockUsers = new ArrayList<>(teamSize);
			data.combs[i] = comb;
		}
	}

	
	

	/**
	 * 初始化学生分场情况
	 * 首先班级分块，根据块中班级数，计算块中学生数
	 * @param data
	 * @param subs
	 * @author Z.R.K
	 */
	private static void initUserBlocks(LayerData data, int[] subs) {
		int subsCombSize = data.subsCombs.size();
		int subsLength = subs.length;
		int[][] subBlkClassCnt = LayerSubUtil.getSubBlkClassCnts(data.subClassCnt, subs);
		int[][] studySubBlkClassCnt = LayerSubUtil.getStudySubBlkClassCnts(data, subs, subBlkClassCnt);
		
		for (int b = 0; b < 3; b++) {
			UserBlock block = new UserBlock();
			block.combUse = new int[subsCombSize];
			block.subNeedClassCnt = new int[subsLength];
			block.studySubNeedClassCnt = new int[subsLength];
			block.subNeedUserCnt = new int[subsLength];
			block.studySubNeedUserCnt = new int[subsLength];
			block.subUserCnt = new int[subsLength];
			block.studySubUserCnt = new int[subsLength];
			data.blocks[b] = block;
			for (int s = 0; s < subsLength; s++) {
				block.subNeedClassCnt[s] = subBlkClassCnt[s][b];
				block.studySubNeedClassCnt[s] = studySubBlkClassCnt[s][b];
				block.subNeedUserCnt[s] = data.classUserCnt * block.subNeedClassCnt[s];
				block.studySubNeedUserCnt[s] = data.classUserCnt * block.studySubNeedClassCnt[s];
			}
		}
		LayerPrint.printBlkClassCnt(subs, subBlkClassCnt, data.subs);
		LayerPrint.printBlkClassCnt(subs, studySubBlkClassCnt, data.subs);
	}
	

	/**
	 * 
	 * @param data
	 * @param subs
	 * @author Z.R.K
	 */
	private static void initCombs(LayerData data, int[] subs) {
		int subsCombSize = data.subsCombs.size();
		data.combs = new Comb[subsCombSize];
		for (int i = 0; i < subsCombSize; i++) {
			Integer combKey = data.subsCombs.get(i);
			int[] subIndexes = new int[3];
			int[] studySubExists = new int[subs.length];
			for (int s = 0; s < 3; s++) {
				int subIndex = (combKey >> (4 * s) & 15) - 1;
				subIndexes[s] = subIndex;
				if(subIndex != -1){
					studySubExists[subIndex] = -1;
				}
			}
			
			Comb comb = new Comb();
			comb.use = new int[3];
			comb.combKey = combKey;
			comb.users = data.combSubsMapping.get(combKey);
			if (comb.users != null) {
				comb.cnt = comb.users.size();
			}
			comb.subIndexes = subIndexes;
			comb.studySubExists = studySubExists;
			comb.combIndex = i;
			data.combs[i] = comb;
		}
	}
	
	public static int getAvg(int cnt, int step){
		int quot = cnt / step;
		int rem = cnt % step;
		if(rem > 0){
			quot += 1;
		}
		return quot;
	}
	
	public static int getQuotByAvg(int cnt, int avg){
		int quot = cnt / avg;
		int rem = cnt % avg;
		quot = quot == 0 ? 1 : quot;
		int quot2 = rem / quot;
		int rem2 = rem % quot;
		if(rem2 > 0){
			quot2 += 1;
		}
		return quot2;
	}
	
	public static int getRemByOffset(int cnt, int avg, int offset){
		int quot = cnt / avg;
		if(quot == 0){
			if(cnt > offset){
				return cnt - avg;
			}else{
				return cnt;
			}
		}
		
		if(cnt > (quot * (avg + offset))){
			quot = quot + 1;
		}
		
		return cnt - avg * quot;
	}
	
	public static int getRem(int cnt, int avg, int quot){
		if(quot > 0) {
			int x = cnt - avg * quot;
			if(x > 0){
				return x / quot;
			}else{
				return 0;
			}
		}else{
			return cnt;
		}
	}
	
	public static int getAvgOffset(int cnt, int avg, int quot){
		int x = cnt - avg * quot;
		if(quot == 0) return x;
		return x / quot;
	}
	
	public static int getAllOffset(int cnt, int avg, int quot){
		int x = cnt - avg * quot;
		return x;
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
		for(int index = 0;index<quantity ;index ++ ){
			segs[index] = quot;
			if(rem > 0)
				segs[index] ++ ;
			rem --;
		}
		return segs;
	}
	
	public static int[] getSegClassCnt(int[][] array){
		int[] segClassCnt = new int[3];
		for(int i=0;i<array.length;i++){
			segClassCnt[0] += array[i][0];
			segClassCnt[1] += array[i][1];
			segClassCnt[2] += array[i][2];
		}
		return segClassCnt;
	}
	
	public static Long getSchoolStageIdByGradeId(Long gradeId){
		if(gradeId <= 6) return 1L;
		else if(gradeId <=9) return 2L;
		else if(gradeId <= 12) return 3L;
		else return 5L;
	}
}
