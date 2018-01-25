package com.renke.paike.util;

import java.util.List;

import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;

public class Manager {
	public LayerData data;
	public List<List<ITeamCell>> teamCells;
	public int[][] cellRem;
	public int[][] cellClassCnt;
	public int[][] cellNeedCnt;
	public int[][] oldRem;
	public int[][] oldClassCnt;
	public int[] teamClassCnt;
	public int[] oldTeamClassCnt;
	public boolean[] classMinus;
	public Manager(LayerData data, int subLength){
		this.data = data;
		cellClassCnt = new int[subLength][6];
		cellRem = new int[subLength][6];
		oldRem = new int[subLength][6];
		oldClassCnt = new int[subLength][6];
		cellNeedCnt = new int[subLength][6];
		teamClassCnt = new int[subLength];
		oldTeamClassCnt = new int[subLength];
		classMinus = new boolean[subLength];
	}
	
	public void register(List<List<ITeamCell>> teamCells){
		if(teamCells == null) return ;
		this.teamCells = teamCells;
		int t=0;
		for(List<ITeamCell> cells : teamCells){
			int c = 0;
			for(ITeamCell cell : cells){
				cellNeedCnt[t][c] = data.blocks[cell.getBlockIndex()].subNeedClassCnt[cell.getSubIndex()];
				c ++;
			}
			t ++ ;
		}
		
		reset(4);
	}
	
	public void tryChangeCombUse(int offset, int val){
		Comb bestComb = null;
		int bestPlusIndex = -1;
		int bestMinusIndex = -1;
		int maxScore = -1;
		// 检查班级数
		
		for (int c = data.combs.length - 1; c >= 0; c--) {
			Comb comb = data.combs[c];
			for (int index1 = 0; index1 < 3; index1++) {
				for (int index2 = 0; index2 < 3; index2++) {
					if(index1 != index2){
						int score = getChangeCombScore(comb, index1, index2, offset, val);
						if(score > maxScore){
							maxScore = score;
							bestPlusIndex = index1;
							bestMinusIndex = index2;
							bestComb = comb;
						}
					}
				}
			}
		}
		if(bestComb != null){
//			LayerPrint.printComb(bestComb.combKey);
//			System.out.println("bestPlusIndex:"+bestPlusIndex+";bestMinusIndex:"+bestMinusIndex+";score:"+maxScore);
			changeComb(bestComb, bestPlusIndex, bestMinusIndex, offset, val);
		}
	}
	
	public void reset(int offset){
		int t=0;
		for(List<ITeamCell> cells : teamCells){
			classMinus[t] = false;
			oldTeamClassCnt[t] = 0;
			teamClassCnt[t] = 0;
			int c = 0;
			for(ITeamCell cell : cells){
				cell.calculateCnt(data.combs);
				oldClassCnt[t][c] = cellClassCnt[t][c];
				oldRem[t][c] = cellRem[t][c];
				cellClassCnt[t][c] = cell.getClassCnt(data.classUserCnt, offset);
				cellRem[t][c] = cell.getRemCnt(data.classUserCnt, offset);
				oldTeamClassCnt[t] += oldClassCnt[t][c];
				teamClassCnt[t] += cellClassCnt[t][c];
				c ++;
			}
			t ++ ;
		}
	}
	
	public void changeComb(Comb comb, int plusIndex, int minusIndex, int offset, int val){
		comb.use[plusIndex] += val;
		comb.use[minusIndex] -= val;
		reset(offset);
	}
	
	public int getChangeCombScore(Comb comb, int plusIndex, int minusIndex, int offset, int val){
		int score = 0;
		if(comb.use[minusIndex] < val){
			return -1;
		}
		comb.use[plusIndex] += val;
		comb.use[minusIndex] -= val;
		
//		for(int t=0;t<teamCellRem.length;t++){
//			String result = "";
//			for(int c=0;c<6;c++){
//				result += "	" + teamCellRem[t][c];
//			}
//			System.out.println(result);
//		}
		reset(offset);
		
		
		for (int t = 0; t < cellClassCnt.length; t++) {
//			List<ITeamCell> cells = teamCells.get(t);
			for (int c = 0; c < 6; c++) {
				int tmp = oldRem[t][c] - cellRem[t][c];
				// 班级数大于指定班级数且等于历史班级数
				if (oldTeamClassCnt[t] > data.needClassCnt) {
					if (teamClassCnt[t] == oldTeamClassCnt[t]) {
						// 人数变少，则加2分
						if(tmp > 0){
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";+2");
//							}
							score += 2;
							// 人数变多，扣2分
						}else if(tmp < 0){
							score -= 2;
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";-2");
//							}
						}
					} else if (teamClassCnt[t] < oldTeamClassCnt[t]) {
						// 加10分
						if(!classMinus[t]){
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";+10");
//							}
							score += 10;
							classMinus[t] = true;
						}
					} else {
						// 扣10分
						if(!classMinus[t]){
							score -= 10;
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";-10");
//							}
							classMinus[t] = true;
						}
					}
				} else if(oldTeamClassCnt[t] == data.needClassCnt){
					if (teamClassCnt[t] == oldTeamClassCnt[t]) {
						// 人数变少，则加1分
						if(tmp > 0){
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";+1");
//							}
							score += 1;
							// 人数变多，扣1分
						}else if(tmp < 0){
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";-1");
//							}
							score -= 1;
						}
					} else if (teamClassCnt[t] > oldTeamClassCnt[t]) {
						// 扣10分
						if(!classMinus[t]){
							score -= 10;
//							if(comb.combKey == (0 << 8)){
//								System.out.println(plusIndex + "-"+ minusIndex + ";"+cell + ";-10");
//							}
							classMinus[t] = true;
						}
					}
					
				} else {
//					if(tmp != 0){
//						score += 1;
//					}
				}
			}
		}
//		for(int t=0;t<teamCellRem.length;t++){
//			String result = "";
//			for(int c=0;c<6;c++){
//				result += "	" + teamCellRem[t][c];
//			}
//			System.out.println(result);
//		}
		
		comb.use[plusIndex] -= val;
		comb.use[minusIndex] += val;
		
		reset(offset);
		return score;
	}
	
	
	public void clear(){
		for(List<ITeamCell> cells : teamCells){
			if(cells != null){
				cells.clear();
				cells = null;
			}
		}
		for(int i=0;i<cellRem.length;i++){
			cellRem[i] = null;
			cellClassCnt[i] = null;
			cellNeedCnt[i] = null;
			oldRem[i] = null;
			oldClassCnt[i] = null;
		}
		teamCells = null;
		cellRem = null;
		cellClassCnt = null;
		cellNeedCnt = null;
		oldRem = null;
		oldClassCnt = null;
		teamClassCnt = null;
		oldTeamClassCnt = null;
		classMinus = null;
	}
}
