package com.renke.paike;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.LayerUserInfo;
import com.renke.paike.model.UserBlock;

public class LayerPrint {
	private static final Logger logger = LoggerFactory.getLogger(LayerPrint.class);
	
	public static void printComb(List<Integer> combs){
		logger.info("--------------组合列表----------");
		logger.info("	编号	组合");
		int i=1;
		for(Integer comb : combs){
			if(comb != null){
				logger.info("	{}	[{},{},{}]", i++, comb >> 16 & 255, comb >> 8 & 255, comb & 255);
			}
		}
	}
	
	public static void printCombCnt(Map<Integer, List<LayerUserInfo>> mapping){
		logger.info("--------------选考科目组合统计人数----------");
		logger.info("	选考科目组合	人数");
		Set<Entry<Integer, List<LayerUserInfo>>> it = mapping.entrySet();
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && key >> 24 == 0 && users!=null){
				logger.info("	[{},{},{}]	{}", key >> 16 & 255, key >> 8 & 255, key & 255, users.size());
			}else if(key != null && key >> 24 != 0 && users!=null){
				logger.info("	[   {}   ]	{}", key >> 24, users.size());
			}
		}
		logger.info("	选考科目组合	学生名单");
		for(Entry<Integer, List<LayerUserInfo>> entry : it){
			Integer key = entry.getKey();
			List<LayerUserInfo> users = entry.getValue();
			if(key != null && key >> 24 == 0 && users!=null){
				logger.info("	[{},{},{}]	{}", key >> 16 & 255, key >> 8 & 255, key & 255, users);
			}else if(key != null && key >> 24 != 0 && users!=null){
				logger.info("	[   {}   ]	{}", key >> 24, users);
			}
		}
		
	}
	
	public static void printSortSubs(LayerData data){
		logger.info("--------------科目----------");
		logger.info("	科目编号	总人数	班级数");
		for(Integer subNo : data.sortSubs){
			List<LayerUserInfo> users = data.combMapping.get(subNo << 24);
			logger.info("	{}	{}	{}", subNo ,users == null ? 0 : users.size(), data.subClassCnt.get(subNo));
		}
	}
	
	public static void printSplitSubs(LayerData data){
		logger.info("--------------科目分组1----------");
		logger.info("	科目编号	总人数	班级数");
		for(int subNo : data.subs1){
			List<LayerUserInfo> users = data.combMapping.get(subNo << 24);
			logger.info("	{}	{}	{}", subNo ,users == null ? 0 : users.size(), data.subClassCnt.get(subNo));
		}
		logger.info("--------------科目分组2----------");
		logger.info("	科目编号	总人数	班级数");
		for(int subNo : data.subs2){
			List<LayerUserInfo> users = data.combMapping.get(subNo << 24);
			logger.info("	{}	{}	{}", subNo ,users == null ? 0 : users.size(), data.subClassCnt.get(subNo));
		}
	}
	
	public static void printBlkClassCnt(Integer[] subs, int[][] subBlkClassCnt){
		logger.info("--------------分块班级数----------");
		logger.info("		A	B	C");
		String msg = "";
		for (int i = 0; i < subBlkClassCnt.length; i++) {
			msg = "	"+subs[i];
			for (int j = 0; j < 3; j++) {
				msg += "	" + subBlkClassCnt[i][j];
			}
			logger.info(msg);
		}
	}
	
	public static void printComBlkCnt(Comb[] combs, UserBlock[] blocks){
		logger.info("		A	B	C");
		for(Comb comb : combs){
			String result = "	";
			for (int i = 0; i < 3; i++) {
				result += "," + comb.subIndex[i];
			}
			for (int i = 0; i < 3; i++) {
				result += "	" + comb.use[i];
			}
			logger.info(result);
		}
	}
}
