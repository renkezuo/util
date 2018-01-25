package com.renke.paike.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.LayerUserInfo;
import com.renke.paike.model.SplitKlass;

public class LayerPrint {
	private static final Logger logger = LoggerFactory.getLogger(LayerPrint.class);
	
	public static void printSubs(LayerData data){
		logger.info("------------科目分组--------------");
		logger.info("	分组1");
		for(int subIndex : data.subs1){
			logger.info("	{}", data.subs[subIndex]);
		}
		logger.info("	分组2");
		for(int subIndex : data.subs2){
			logger.info("	{}", data.subs[subIndex]);
		}
	}
	
	public static void printSubs(List<Integer> subs, int[] choiceSubCnt, Long[] allSubs){
		logger.info("--------------科目顺序----------");
		logger.info("	编号	科目	人数");
		for(Integer subIndex : subs){
			if(subIndex != null){
				logger.info("	{}	{}	{}", subIndex + 1, allSubs[subIndex], choiceSubCnt[subIndex]);
			}
		}
	}
	
	public static void printComb(List<Integer> combs, int subs[], Long allSubs[]){
		logger.info("--------------组合列表----------");
		logger.info("	编号	组合");
		int i=1;
		for(Integer comb : combs){
			if(comb != null){
				long[] vals = getSubNos(comb, subs, allSubs);
				logger.info("	{}	[{},{},{}]", i++, vals[0], vals[1], vals[2]);
			}
		}
	}
	
	public static void printComb3(List<Integer> combs, int subs[], Long allSubs[]){
		logger.info("--------------组合列表----------");
		logger.info("	编号	组合");
		int i=1;
		for(Integer comb : combs){
			if(comb != null){
				long[] vals = getSubNos(comb, allSubs);
				logger.info("	{}	[{},{},{}]", i++, vals[0], vals[1], vals[2]);
			}
		}
	}
	
	public static void printComb4(List<Integer> combs, int subs[], Long allSubs[]){
		logger.info("--------------组合列表----------");
		logger.info("	编号	组合");
		int i=1;
		for(Integer comb : combs){
			if(comb != null){
				long[] vals = getSubNos4(comb, allSubs);
				logger.info("	{}	[{},{},{},{}]", i++, vals[0], vals[1], vals[2], vals[3]);
			}
		}
	}
	
	public static void printAllComb4(LayerData data){
		logger.info("--------------选考科目组合统计人数----------");
		logger.info("	选考科目组合	人数");
		Set<Entry<Integer, List<LayerUserInfo>>> it = data.combMapping.entrySet();
		int cnt = 0;
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				logger.info("	[{},{},{},{}]	{}", data.subs[(key >> 12 & 15) - 1], data.subs[(key >> 8 & 15) - 1]
					, data.subs[(key >> 4 & 15) - 1], data.subs[(key & 15) - 1], users.size());
			}
			cnt ++;
		}
		logger.info("	合计：{}", cnt);
		logger.info("	选考科目组合	学生名单");
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				logger.info("	[{},{},{},{}]	{}", data.subs[(key >> 12 & 15) - 1], data.subs[(key >> 8 & 15) - 1]
						, data.subs[(key >> 4 & 15) - 1], data.subs[(key & 15) - 1], users);
			}
		}
	}
	
	public static void printAllComb(LayerData data){
		logger.info("--------------选考科目组合统计人数----------");
		logger.info("	选考科目组合	人数");
		Set<Entry<Integer, List<LayerUserInfo>>> it = data.combMapping.entrySet();
		int cnt = 0;
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				logger.info("	[{},{},{}]	{}", data.subs[(key >> 8 & 15) - 1]
					, data.subs[(key >> 4 & 15) - 1], data.subs[(key & 15) - 1], users.size());
			}
			cnt ++;
		}
		logger.info("	合计：{}", cnt);
		logger.info("	选考科目组合	学生名单");
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				logger.info("	[{},{},{}]	{}", data.subs[(key >> 8 & 15) - 1]
						, data.subs[(key >> 4 & 15) - 1], data.subs[(key & 15) - 1], users);
			}
		}
	}
	
	public static void printCombCnt(Map<Integer, List<LayerUserInfo>> mapping, int[] subs, Long[] allSubs){
		logger.info("--------------选考科目组合统计人数----------");
		logger.info("	选考科目组合	人数");
		Set<Entry<Integer, List<LayerUserInfo>>> it = mapping.entrySet();
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				long[] vals = getSubNos(key, subs, allSubs);
				logger.info("	[{},{},{}]	{}", vals[0], vals[1], vals[2], users.size());
			}
		}
		logger.info("	选考科目组合	学生名单");
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && users != null){
				long[] vals = getSubNos(key, subs, allSubs);
				logger.info("	[{},{},{}]	{}", vals[0], vals[1], vals[2], users);
			}
		}
	}
	
	public static void printSortSubs(LayerData data){
		logger.info("--------------科目----------");
		logger.info("	科目编号	科目	总人数	班级数");
		for(Integer subIndex : data.sortSubs){
			logger.info("	{}	{}	{}	{}", subIndex + 1, data.subs[subIndex], data.choiceSubCnt[subIndex], data.subClassCnt[subIndex]);
		}
	}
	
	public static void printSplitSubs(LayerData data){
		logger.info("--------------科目分组1----------");
		logger.info("	科目编号	总人数	班级数");
		for(int subIndex : data.subs1){
			logger.info("	{}	{}	{}", subIndex , data.choiceSubCnt[subIndex], data.subClassCnt[subIndex]);
		}
		logger.info("--------------科目分组2----------");
		logger.info("	科目编号	总人数	班级数");
		for(int subIndex : data.subs2){
			logger.info("	{}	{}	{}", subIndex , data.choiceSubCnt[subIndex], data.subClassCnt[subIndex]);
		}
	}
	
	public static void printTeamClassCnt(int[] subs, int[][] subTeamClassCnt, Long allSubs[]){
		logger.info("--------------Team班级数----------");
		logger.info("		1	2	3	4");
		for(int i=0;i<4;i++){
			String msg = "	" + allSubs[subs[i]];
			for (int t = 0; t < subTeamClassCnt[i].length; t++) {
				msg += "	" + subTeamClassCnt[i][t];
			}
			logger.info(msg);
		}
	}
	
	public static void printBlkClassCnt(int[] subs, int[][] subBlkClassCnt, Long allSubs[]){
		logger.info("--------------分块班级数----------");
		logger.info("		A	B	C");
		String msg = "";
		for (int i = 0; i < subBlkClassCnt.length; i++) {
			msg = "	"+allSubs[subs[i]];
			for (int j = 0; j < 3; j++) {
				msg += "	" + subBlkClassCnt[i][j];
			}
			logger.info(msg);
		}
	}
	
	public static void printComBlkCnt(LayerData data, int subs[]){
		logger.info("--------------分块人数----------");
		logger.info("		A	B	C");
		for(Comb comb : data.combs){
			String result = "	";
			for (int i = 2; i >=0; i--) {
				int key = comb.subIndexes[i];
				long val = key == -1 ? 0 : data.subs[subs[key]];
				result += "," + val;
			}
			for (int i = 0; i < 3; i++) {
				result += "	" + comb.use[i];
			}
//			result += "	" + comb.cnt;
			logger.info(result);
		}
	}
	
	public static void printComBlkCnt(LayerData data){
		logger.info("--------------分块人数----------");
		logger.info("		A	B	C");
		for(Comb comb : data.combs){
			String result = "	";
			for (int i = 2; i >=0; i--) {
				int key = comb.subIndexes[i];
				long val = key == -1 ? 0 : data.subs[key];
				result += "," + val;
			}
			for (int i = 0; i < 3; i++) {
				result += "	" + comb.use[i];
			}
			logger.info(result);
		}
	}
	
	public static void printComBlkCnt3(LayerData data){
		logger.info("--------------分块人数----------");
		logger.info("		A	B	C");
		for(Comb comb : data.combs){
			String result = "	";
			for (int i = 0; i < 3; i++) {
				int key = comb.subIndexes[i];
				long val = key == -1 ? 0 : data.subs[key];
				result += "," + val;
			}
			for (int i = 0; i < 3; i++) {
				result += "	" + comb.use[i];
			}
			logger.info(result);
		}
	}
	
	public static void printComBlkCnt4(LayerData data){
		logger.info("--------------分块人数----------");
		logger.info("		A	B	C	D");
		for(Comb comb : data.combs){
			String result = "	";
			for (int i = 0; i < 4; i++) {
				int key = comb.subIndexes[i];
				long val = key == -1 ? 0 : data.subs[key];
				result += "," + val;
			}
			for (int i = 0; i < 4; i++) {
				result += "	" + comb.use[i];
			}
			logger.info(result);
		}
	}
	
	public static void printManager(Manager manager){
		int teamId = 1;
		logger.info("	分组结果：	1	2	3");
		for(List<ITeamCell> cells : manager.teamCells){
			String result = "	" + (teamId ++);
			int cnt = 0;
			for(ITeamCell cell : cells){
				result += "	" + cell;
				cnt += cell.getClassCnt(manager.data.classUserCnt , 4);
			}
			logger.info(result + "	合计班级数："+ cnt);
		}
		String result = "	合计：";
		int blockCnts[] = new int[3];
		for(Comb comb : manager.data.combs){
			for(int i=0;i<3;i++){
				blockCnts[i] += comb.use[i];
			}
		}
		
		for(int cnt : blockCnts){
			result += "	"+cnt;
		}
		logger.info(result);
	}
	
	
	public static void printTeamCellUser(LayerData data, List<List<TeamCell>> teamCells) {
		int teamIndex = 1;
		String msg = "	";
		for(TeamCell cell : teamCells.get(0)){
			msg += "	" + data.subs[cell.getSubIndex()];
		}
		logger.info(msg);
		for(List<TeamCell> cells : teamCells){
			String result = "	" + teamIndex ++;
			for(TeamCell cell : cells){
				cell.calculateCnt(data.combs);
				result += "	" + cell.getClassCnt(data.classUserCnt, data.offset);
			}
			logger.info(result);
		}
	}
	
	private static long[] getSubNos(int comb , int subs[], Long allSubs[]){
		int key1 = (comb >> 8 & 15) - 1;
		int key2 = (comb >> 4 & 15) - 1;
		int key3 = (comb & 15) - 1;
		long result[] = new long[3];
		result[0] =  key1 == -1 ? 0 : allSubs[subs[key1]];
		result[1] =  key2 == -1 ? 0 : allSubs[subs[key2]];
		result[2] =  key3 == -1 ? 0 : allSubs[subs[key3]];
		return result;
	}
	
	private static long[] getSubNos(int comb, Long allSubs[]){
		int key1 = (comb >> 8 & 15) - 1;
		int key2 = (comb >> 4 & 15) - 1;
		int key3 = (comb & 15) - 1;
		long result[] = new long[3];
		result[0] =  key1 == -1 ? 0 : allSubs[key1];
		result[1] =  key2 == -1 ? 0 : allSubs[key2];
		result[2] =  key3 == -1 ? 0 : allSubs[key3];
		return result;
	}
	
	private static long[] getSubNos4(int comb, Long allSubs[]){
		int key1 = (comb >> 12 & 15) - 1;
		int key2 = (comb >> 8 & 15) - 1;
		int key3 = (comb >> 4 & 15) - 1;
		int key4 = (comb & 15) - 1;
		long result[] = new long[4];
		result[0] =  key1 == -1 ? 0 : allSubs[key1];
		result[1] =  key2 == -1 ? 0 : allSubs[key2];
		result[2] =  key3 == -1 ? 0 : allSubs[key3];
		result[3] =  key4 == -1 ? 0 : allSubs[key4];
		return result;
	}
	
	public static void printComb(int comb){
		int key1 = (comb >> 8 & 15);
		int key2 = (comb >> 4 & 15);
		int key3 = (comb & 15);
		logger.info("comb -- [{},{},{}]",key1,key2,key3);
	}
	
	public static void printUserCnt(LayerData data){
		for (int i = 0; i < data.subCnt; i++) {
			logger.info("	subNo	beginScore	endScore	cnt");
			for (int s = 0; s < data.scoreSegLength; s++) {
				logger.info("	{}	{}	{}	{}", data.subs[i], s * 10, (s + 1) * 10 - 1, data.subScoreSegCnt[i][s]);
			}
			
			logger.info("	subNo	woman	man");
			logger.info("	{}	{}	{}", data.subs[i], data.subSexSegCnt[i][0], data.subSexSegCnt[i][1]);
		}
	}
	
	public static void printCellUsers(LayerData data, TeamTable table){
		for(List<TeamCell> cells : table.teamCells){
			for(TeamCell cell : cells){
				logger.info("-------------------------------------------------------------");
				int val1 = cell.scoreCnt.length > 7 ? cell.scoreCnt[5] : cell.scoreCnt[0];
				int val2 = cell.scoreCnt.length > 7 ? cell.scoreCnt[6] : cell.scoreCnt[1];
				int val3 = cell.scoreCnt.length > 7 ? cell.scoreCnt[7] : cell.scoreCnt[2];
				logger.info("	teamId:{}, subNo:{}, userCnt:{}, womanCnt:{}, manCnt:{}, scoreCnt50-60:{}, scoreCnt60-70:{}, scoreCnt70-80:{}"
									,cell.teamId + 1, data.subs[cell.subIndex], cell.userCnt, cell.sexCnt[0]
									, cell.sexCnt[1], val1, val2, val3);
				for(LayerUserInfo user : cell.users){
					logger.info("	{}	{}	{}", user.getUserName(), user.scores[cell.subIndex], user.isMan);
				}
			}
		}
	}
	
	public static void printTeamTable(LayerData data, TeamTable table){
		logger.info("	teamId	subNo	userCnt	womanCnt	manCnt	50~60	60~70	70~80");
		for(List<TeamCell> cells : table.teamCells){
			for(TeamCell cell : cells){
				int val1 = cell.scoreCnt.length > 7 ? cell.scoreCnt[5] : cell.scoreCnt[0];
				int val2 = cell.scoreCnt.length > 7 ? cell.scoreCnt[6] : cell.scoreCnt[1];
				int val3 = cell.scoreCnt.length > 7 ? cell.scoreCnt[7] : cell.scoreCnt[2];
				logger.info("	{}	{}	{}	{}	{}	{}	{}	{}",
							cell.teamId + 1, data.subs[cell.subIndex], cell.userCnt, cell.sexCnt[0],
							cell.sexCnt[1], val1, val2, val3);
			}
		}
	}
	
	public static void printTeamClassUsers(LayerData data, TeamTable table){
		logger.info("	teamId	科目	班级名称	人数	女	男	50~60	60~70	70~80");
		for(List<TeamCell> cells : table.teamCells){
			for(TeamCell cell : cells){
				for(SplitKlass klass : cell.klasses){
					int val1 = klass.getScoreCnt().length > 7 ? klass.getScoreCnt()[5] : klass.getScoreCnt()[0];
					int val2 = klass.getScoreCnt().length > 7 ? klass.getScoreCnt()[6] : klass.getScoreCnt()[1];
					int val3 = klass.getScoreCnt().length > 7 ? klass.getScoreCnt()[7] : klass.getScoreCnt()[2];
					logger.info("	{}	{}	{}	{}	{}	{}	{}	{}	{}"
							, cell.teamId+1, data.subs[cell.subIndex], klass.getClassName(), klass.getHeadCount()
							, klass.getSexCnt()[0], klass.getSexCnt()[1], val1 , val2, val3);
				}
			}
		}
	}
	
}
