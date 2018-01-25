package com.renke.paike.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renke.paike.util.DataUtil;

public class LayerData {
	public int layerType; // 0混合，1选考，2选考和学考单独分班
	public Long[] subs;		// 实际科目ID列表
	public int[] choiceSubCnt;	// 选择科目人数
	public int[] subClassCnt;	// 科目班级数
	public int[] studySubClassCnt;	// 学考科目班级数
	public int[] subs1;			// 第一批科目，混合分班时使用
	public int[] subs2;			// 第二批科目
	public int[] ascSubs;		// 正序科目索引列表
	public int[] rhombSubs;		// 菱形排序科目索引列表
	public int[] topSubs;		// 前两个科目和后二个科目
	public Map<Integer, List<LayerUserInfo>> subMapping = new HashMap<>();	// 科目对应选择学生映射
	public Map<Integer, List<LayerUserInfo>> combMapping = new HashMap<>();	// 组合对应学生映射
	public Map<Integer, List<LayerUserInfo>> combSubsMapping = new HashMap<>();	// 重新排序后组合对应学生映射 
	public List<Integer> subsCombs = new ArrayList<>();	// 科目组合列表
	public List<Integer> sortSubs = new ArrayList<>(7);	// 排序科目索引列表
	public UserBlock[] blocks = new UserBlock[3];
	public UserBlockHope[] hopeBlocks;	// 学生志愿块[第一志愿，第二志愿，第三志愿]
	public Comb[] combs;	// 组合数组
	public int classCnt;	// 计算最少同时上课班级数
	public int needClassCnt;// 设定同时上课班级数
	public int classUserCnt;// 班级学生数
	public int offset;		// 班级学生数有效偏移量(classUserCnt / 10)
	public int userCnt;		// 学生总数
	public int subCnt;		// 科目总数
	public LayerUserInfo[] users;// 学生列表
	public int scoreInterval;	// 分数段间隔
	public int scoreSegLength;	// 分数段个数
	public int[][] subScoreSegCnt;// 科目分段人数
	public int[][] subSexSegCnt;// 性别分段人数
	public Long taskId;
	public Long schoolId;
	public Long schoolStageId;
	public Long userId;
	public Long gradeId;
	public String gradeName;
	public String[] subNames;
	
	public void clearAll(){
		subsCombs.clear();
		sortSubs.clear();
		DataUtil.clearMapList(subMapping);
		DataUtil.clearMapList(combMapping);
		DataUtil.clearMapList(combSubsMapping);
		DataUtil.clearArrayData(blocks);
		DataUtil.clearArrayData(hopeBlocks);
		DataUtil.clearArrayData(combs);
		DataUtil.clearArrayInt(subScoreSegCnt);
		DataUtil.clearArrayInt(subSexSegCnt);
		subs = null;
		choiceSubCnt = null;
		subClassCnt = null;
		studySubClassCnt = null;
		subs1 = null;
		subs2 = null;
		subMapping = null;
		combMapping = null;
		combSubsMapping = null;
		subsCombs = null;
		blocks = null;
		hopeBlocks = null;
		combs = null;
		users = null;
		sortSubs = null;
	}
	
	public void resetPart(){
		if(choiceSubCnt != null){
			choiceSubCnt = new int[choiceSubCnt.length];
		}
		if(subClassCnt != null){
			subClassCnt = new int[subClassCnt.length];
		}
		subsCombs.clear();
		sortSubs.clear();
		DataUtil.clearMapList(subMapping);
		DataUtil.clearMapList(combMapping);
		DataUtil.clearMapList(combSubsMapping);
		DataUtil.clearArrayData(blocks);
		DataUtil.clearArrayData(hopeBlocks);
		DataUtil.clearArrayData(combs);
		DataUtil.clearArrayInt(subScoreSegCnt);
		DataUtil.clearArrayInt(subSexSegCnt);
		subsCombs = new ArrayList<>();
		sortSubs = new ArrayList<>(7);
		subMapping = new HashMap<>();
		combMapping = new HashMap<>();
		combSubsMapping = new HashMap<>();
	}
}
