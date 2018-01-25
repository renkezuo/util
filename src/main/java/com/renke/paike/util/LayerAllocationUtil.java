package com.renke.paike.util;

import java.util.ArrayList;
import java.util.List;

import com.renke.paike.model.Comb;
import com.renke.paike.model.KlassUser;
import com.renke.paike.model.LayerData;
import com.renke.paike.model.LayerUserInfo;
import com.renke.paike.model.SplitKlass;
import com.renke.paike.model.UserBlock;
import com.renke.paike.model.UserBlockHope;

public class LayerAllocationUtil {

	public static void allocationComb(LayerData data, int teamSize){
		int subsCombSize = data.combs.length;
		for (int c = 0; c < subsCombSize; c++) {
			Comb comb = data.combs[c];
			int cnts[] = new int[teamSize];
			for (int b = 0; b < teamSize; b++) {
				UserBlockHope block = data.hopeBlocks[b];
				int minCnt = 10000;
				for (int w = 0; w < teamSize; w++) {
					int subIndex = comb.subIndexes[w];
					subIndex = getTopSubIndex(data.topSubs, subIndex);
					if(subIndex != -1){
						int cnt = block.wishSubNeedUserCnt[subIndex][w];
						if(cnt < minCnt){
							minCnt = cnt;
						}
					}
				}
				if(minCnt == 10000){
					minCnt = 0;
				}
				cnts[b] = minCnt;
			}
			
			// 如果分配最后，不存在大于2的分组，则终止循环
			// 两种情况：	1、数量超过需求[多余的交给相对需求比较多的块]
			//			2、数量不足[直接分配给前两个需求块]
			while (hasNumber(cnts, 2)) {
				int sumCnt = DataUtil.sum(cnts);
				if (comb.cnt - sumCnt >= 0) {
					comb.cnt -= sumCnt;
					for (int b = 0; b < teamSize; b++) {
						UserBlockHope block = data.hopeBlocks[b];
						comb.use[b] += cnts[b];
						for (int w = 0; w < teamSize; w++) {
							int subIndex = comb.subIndexes[w];
							subIndex = getTopSubIndex(data.topSubs, subIndex);
							if(subIndex != -1 && block.wishSubNeedUserCnt[subIndex][w] >= cnts[b]){
								block.wishSubNeedUserCnt[subIndex][w] -= cnts[b];
								block.wishSubUserCnt[subIndex][w] += cnts[b];
							}
						}
						
					}
				} else {
					for (int x = 0; x < teamSize; x++) {
						cnts[x] = cnts[x] >> 1;
					}
				}
			}
			
			if(!hasNumber(cnts, 1)){
				for (int b = 0; b < teamSize; b++) {
					cnts[b] = 1;
				}
			}
			
			while(comb.cnt > 0){
				for (int b = 0; b < teamSize; b++) {
					UserBlockHope block = data.hopeBlocks[b];
					if(cnts[b] > 0 && comb.cnt > 0){
						comb.use[b] += 1;
						comb.cnt -= 1;
						for (int w = 0; w < teamSize; w++) {
							int subIndex = comb.subIndexes[w];
							subIndex = getTopSubIndex(data.topSubs, subIndex);
							if(subIndex != -1 && block.wishSubNeedUserCnt[subIndex][w] >= 1){
								block.wishSubNeedUserCnt[subIndex][w] -= 1;
								block.wishSubUserCnt[subIndex][w] += 1;
							}
						}
					}
				}
			}
		}
	}
	
	public static void allocationComb(LayerData data) {
		int subsCombSize = data.subsCombs.size();
		for (int c = 0; c < subsCombSize; c++) {
			Comb comb = data.combs[c];
			// 取blocks中comb科目列表的最小科目人数
			int cnts[] = new int[3];
			for (int b = 0; b < 3; b++) {
				UserBlock block = data.blocks[b];
				int minCnt = 10000;
				
				for (int s : comb.subIndexes) {
					if (s != -1) {
						if (block.subNeedUserCnt[s] < minCnt) {
							minCnt = block.subNeedUserCnt[s];
						}
					}
				}
				// 检查block的相关科目是否存在零项
				for (int s = 0; s < comb.studySubExists.length; s++) {
					if(comb.studySubExists[s] != -1){
						if(block.studySubNeedUserCnt[s] < minCnt){
							minCnt = block.studySubNeedUserCnt[s];
						}
					}
				}
				if(minCnt == 10000){
					minCnt = 0;
				}
				cnts[b] = minCnt;
			}

			// 如果分配最后，不存在大于2的分组，则终止循环
			// 两种情况：	1、数量超过需求[多余的交给相对需求比较多的块]
			//			2、数量不足[直接分配给前两个需求块]
			while (hasNumber(cnts, 2)) {
				int sumCnt = DataUtil.sum(cnts);
				if (comb.cnt - sumCnt >= 0) {
					comb.cnt -= sumCnt;
					for (int b = 0; b < 3; b++) {
						UserBlock block = data.blocks[b];
						block.combUse[c] += cnts[b];
						comb.use[b] += cnts[b];
						for (int s : comb.subIndexes) {
							if (s != -1) {
								if(block.subNeedUserCnt[s] >= cnts[b]){
									block.subNeedUserCnt[s] -= cnts[b];
									block.subUserCnt[s] += cnts[b];
								}
							}
						}
						
						for (int s = 0; s < comb.studySubExists.length; s++) {
							if(comb.studySubExists[s] != -1){
								if(block.studySubNeedUserCnt[s] >= cnts[b]){
									block.studySubNeedUserCnt[s] -= cnts[b];
									block.studySubUserCnt[s] += cnts[b];
								}
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
					int cnt = 1;
					UserBlock block = data.blocks[b];
					for(int classCnt : block.studySubNeedClassCnt){
						if(classCnt == 0){
							cnt = 0;
						}
					}
					cnts[b] = cnt;
				}
			}
			
			while(comb.cnt > 0){
				for (int b = 0; b < 3; b++) {
					UserBlock block = data.blocks[b];
					if(cnts[b] > 0 && comb.cnt > 0){
						block.combUse[c] += 1;
						comb.use[b] += 1;
						comb.cnt -= 1;
						for (int s : comb.subIndexes) {
							if (s != -1) {
								if(block.subNeedUserCnt[s] >= 1){
									block.subNeedUserCnt[s] -= 1;
									block.subUserCnt[s] += 1;
								}
							}
						}
						
						for (int s = 0; s < comb.studySubExists.length; s++) {
							if(comb.studySubExists[s] != -1){
								if(block.studySubNeedUserCnt[s] >= 1){
									block.studySubNeedUserCnt[s] -= 1;
									block.studySubUserCnt[s] += 1;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 分配学生
	 * @param data
	 * @param combCellMapping
	 * @author Z.R.K
	 */
	public static void allocationUser(LayerData data, TeamTable table) {
		boolean[] over = new boolean[data.combs.length];
		int blockLength = data.hopeBlocks.length;

		// 设置科目班级分段平均人数
		initClassSegAvgUserCnt(data, table);
		
		LayerPrint.printUserCnt(data);
		
		initUserList(data, table, blockLength);
		
		// 根据科目，分配学生，五个科目必然处理完全部组合
		// 此处分配有些许问题，第一个科目不平均，请查出问题
		for(int s=0;s<5;s++){
			allocationBlockBySub(data, table, over, blockLength, s);
		}
		
		// 分配学生到班
		allocationUserToClass(data, table);
		
	}
	
	/**
	 * 分配人数前预准备
	 * 科目各分段总人数 / 科目班级数 = 班级平均分段人数
	 * 记录班级平均分段人数
	 * @param data
	 * @param ascSubs
	 * @param table
	 * @author Z.R.K
	 */
	public static void initClassSegAvgUserCnt(LayerData data, TeamTable table) {
		// 设定总分150分为上限
		data.subScoreSegCnt = new int[data.subCnt][data.scoreSegLength];
		data.subSexSegCnt = new int[data.subCnt][2];
		
		for(Integer subIndex : data.ascSubs){
			List<LayerUserInfo> users = data.subMapping.get(subIndex + 1);
			if(users != null){
				// 记录各分数段人数
				for(LayerUserInfo user :data.subMapping.get(subIndex + 1)){
					int index = (int)user.scores[subIndex] / data.scoreInterval;
					user.scoreSegIndex[subIndex] = index;
					data.subScoreSegCnt[subIndex][index] += 1;
					data.subSexSegCnt[subIndex][user.isMan] += 1;
				}
				
				if(data.subClassCnt[subIndex] != 0){
					for(int i=0;i<data.scoreSegLength;i++){
						data.subScoreSegCnt[subIndex][i] = data.subScoreSegCnt[subIndex][i] / data.subClassCnt[subIndex];
					}
					data.subSexSegCnt[subIndex][0] = data.subSexSegCnt[subIndex][0] / data.subClassCnt[subIndex];
					data.subSexSegCnt[subIndex][1] = data.subSexSegCnt[subIndex][1] / data.subClassCnt[subIndex];
				}
			}
		}

	}
	
	private static void initUserList(LayerData data, TeamTable table, int blockLength) {
		for(Comb comb : data.combs){
			comb.blockUsers = new ArrayList<>(blockLength);
			for (int b = 0; b < blockLength; b++) {
				comb.blockUsers.add(new ArrayList<>(comb.use[b]));
			}
		}
		
		for(List<TeamCell> cells : table.teamCells){
			for(TeamCell cell: cells){
				cell.users = new ArrayList<>(cell.userCnt);
			}
		}
	}
	
	
	/***
	 * @param data
	 * @param table
	 * @author Z.R.K
	 */
	private static void allocationUserToClass(LayerData data, TeamTable table){
		int teamSize = data.hopeBlocks.length;
		for(int s = 0; s < data.subCnt; s++){
			int classIndex = 1;
			for (int t = 0; t < teamSize; t++) {
				TeamCell cell = table.teamCells.get(t).get(s);
				if(cell.userCnt > 0){
					int classCnt = cell.getClassCnt(data.classUserCnt, data.offset);
					cell.klasses = new ArrayList<>(classCnt);
					// 先平均人数，再平均性别，再平均成绩
					int[] userCnts = DataUtil.getAvg(cell.userCnt, data.classUserCnt + data.offset);
					int[] avgSexCnt = DataUtil.getQuot(cell.sexCnt, classCnt);
					int[] avgSegCnt = DataUtil.getQuot(cell.scoreCnt, classCnt);
					String className = cell.isChoice ? "选考" : "学考";
					Integer teamType = cell.isChoice ? 1 : 2;
					int startTeamId = cell.isChoice ? 1 : 4;
					for (int c = 0; c < classCnt; c++) {
						SplitKlass klass = new SplitKlass();
						klass.setClassName(className + classIndex + "班");
						klass.setHeadCount(userCnts[c]);
						int sexCnt[] = new int[2];
						int sexNeedCnt[] = new int[2];
						int scoreCnt[] = new int[data.scoreSegLength];
						int scoreNeedCnt[] = new int[data.scoreSegLength];
						klass.setSexCnt(sexCnt);
						klass.setSexNeedCnt(sexNeedCnt);
						klass.setScoreCnt(scoreCnt);
						klass.setScoreNeedCnt(scoreNeedCnt);
						klass.setSubId(data.subs[s]);
						klass.setSubName(data.subNames[s]);
						klass.setTeamType(teamType);
						klass.setTeamId(startTeamId + t);
						
						klass.setSchoolId(data.schoolId);
						klass.setSchoolStageId(data.taskId + 10);
						klass.setGradeId(data.gradeId);
						klass.setGradeName(data.gradeName);
//						klass.setCreatedBy(data.userId);
						
						cell.klasses.add(klass);
						for (int i = 0; i < 2; i++) {
							sexNeedCnt[i] = avgSexCnt[i];
						}
						for (int i = 0; i < data.scoreSegLength; i++) {
							scoreNeedCnt[i] = avgSegCnt[i];
						}
						klass.setStudents(new ArrayList<>(userCnts[c]));
						classIndex++;
					}
					
					for(LayerUserInfo user : cell.users){
						int segIndex = user.scoreSegIndex[s];
						int maxScore = -10;
						int klassIndex = 0;
						int bestIndex = 0;
						for(SplitKlass klass : cell.klasses){
							int score = 0;
							score += klass.getSexNeedCnt()[user.isMan] - klass.getSexCnt()[user.isMan];
							score += klass.getScoreNeedCnt()[segIndex] - klass.getScoreCnt()[segIndex];
							
							if(score > maxScore && klass.getHeadCount() > klass.getStudents().size()){
								maxScore = score;
								bestIndex = klassIndex;
							}
							klassIndex ++;
						}
						SplitKlass klass = cell.klasses.get(bestIndex);
						KlassUser student = new KlassUser();
						student.setUserId(user.getUserId());
						student.setUserName(user.getUserName());
						student.setSchoolId(data.schoolId);
//						student.setCreatedBy(data.userId);
						klass.getStudents().add(student);
						klass.getSexCnt()[user.isMan] += 1;
						klass.getScoreCnt()[segIndex] += 1;
					}
				} else {
					cell.klasses = new ArrayList<>(0);
				}
			}
		}
	}

	/**
	 * 科目正序
	 * 1、处理第一科目是第一志愿的组合0-0		sub % 2 = 0
	 * 2、处理第二科目是最后志愿的组合1-N		sub % 2 = 1
	 * 3、处理第三科目是第一志愿的组合2-0		sub % 2 = 0
	 * 4、处理第四科目是最后志愿的组合3-N		sub % 2 = 1
	 * 5、处理第五科目是第一志愿的组合4-0		sub % 2 = 0
	 * 五个科目必然处理完全部组合
	 * @param data
	 * @param table
	 * @param over
	 * @param blockLength
	 * @param baseSub
	 * @author Z.R.K
	 */
	private static void allocationBlockBySub(LayerData data, TeamTable table
			, boolean[] over, int blockLength, int baseSub) {
		int baseWish = baseSub % 2 == 0 ? 0 : (blockLength - 1);
		for (int c = 0; c < over.length; c++) {
			Comb comb = data.combs[c];
			if (!over[c] && comb.subIndexes[baseWish] == data.ascSubs[baseSub]) {
				for (LayerUserInfo user : comb.users) {
					long[] blockScore = new long[blockLength];
					for (int w = 0; w < blockLength; w++) {
						int subIndex = comb.subIndexes[w];
						int segIndex = user.scoreSegIndex[subIndex];
						for (int t = 0; t < blockLength; t++) {
							TeamCell cell = table.teamCells.get(t).get(subIndex);
							// t+w%length = b
							int b = (cell.teamId + w) % blockLength;
							List<LayerUserInfo> users = comb.blockUsers.get(b);
							if (users.size() < comb.use[b]) {
								int cellClassCnt = cell.getClassCnt(data.classUserCnt, data.offset);
								
								long score = data.subScoreSegCnt[subIndex][segIndex] - cell.scoreCnt[segIndex] / cellClassCnt;
								
								score +=  data.subSexSegCnt[subIndex][user.isMan] - cell.sexCnt[user.isMan] / cellClassCnt;
								
								score += 100;
								// 菱形处理第一志愿，最后志愿，中间志愿顺序打分
								int offset = 0;
								if(w == 0){
									offset = (blockLength - 1) * 8 ;
								}else if(w == 1){
									offset = blockLength == 4 ? 8 : 0;
								}else if(w == 2){
									offset = blockLength == 4 ? 0 : 8;
								}else if(w == 3){
									offset = 2 * 8;
								}
								blockScore[b] += (score << offset);
							}
						}
					}
					int blockIndex = DataUtil.getMaxIndex(blockScore);
					comb.blockUsers.get(blockIndex).add(user);
					for (int w = 0; w < blockLength; w++) {
						int s = comb.subIndexes[w];
						int segIndex = user.scoreSegIndex[s];
						int t = blockIndex - w;
						if (t < 0) {
							t += blockLength;
						}
						TeamCell cell = table.teamCells.get(t).get(s);
						cell.scoreCnt[segIndex] += 1;
						cell.sexCnt[user.isMan] += 1;
						cell.users.add(user);
					}
				}
				over[c] = true;
			}
		}
	}

	public static int getTopSubIndex(int[] topSubs, int subIndex){
		for (int s = 0; s < topSubs.length; s++) {
			if (topSubs[s] == subIndex) {
				return s;
			}
		}
		return -1;
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
