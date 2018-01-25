package com.renke.paike.util;

import java.util.List;

import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerData;

public class TeamTable {
	public LayerData data;
	public List<List<TeamCell>> teamCells;
	public int[][] cellAvgOffset;	// 平均班级偏移量
	public int[][] cellAllOffset;	// 总偏移量
	public int[][] cellClassCnt;	// 班级数
	public int[][] cellNeedCnt;		// 需要班级数
	public int[][] oldAvgOffset;	// 历史平均班级偏移量
	public int[][] oldAllOffset;	// 历史总偏移量
	public int[][] oldClassCnt;		// 历史班级数
	public int[] teamClassCnt;		// 分组班级总数
	public int[] subClassCnt;		// 分科目班级总数
	public int rowLength;			// 行数
	public int colLength;			// 列数
	public boolean[] teamError;		// 分组班级数是否异常
	public boolean[][] isError;		// cell是否班级数是否异常
	
	public void initTable(LayerData data, List<List<TeamCell>> teamCells){
		initData(data, teamCells);
		initCntAndOffset();
		adjustNeedCnt();
	}
	
	private void initData(LayerData data, List<List<TeamCell>> teamCells){
		this.teamCells = teamCells;
		colLength = data.subCnt;
		this.data = data;
		this.rowLength = teamCells.size();
		cellAvgOffset = new int[rowLength][colLength];
		cellAllOffset = new int[rowLength][colLength];
		cellClassCnt = new int[rowLength][colLength];
		cellNeedCnt = new int[rowLength][colLength];
		oldAvgOffset = new int[rowLength][colLength];
		oldAllOffset = new int[rowLength][colLength];
		oldClassCnt = new int[rowLength][colLength];
		teamClassCnt = new int[rowLength];
		subClassCnt = new int[colLength];
		teamError = new boolean[rowLength];
		isError = new boolean[rowLength][colLength];
	}
	
	private void initCntAndOffset(){
		int t=0;
		
		for (int s = 0; s < colLength; s++) {
			subClassCnt[s] = 0;
		}
		
		for(List<TeamCell> cells : teamCells){
			int c = 0;
			for(TeamCell cell : cells){
				cell.calculateCnt(data.combs);
				int userCnt = cell.getUserCnt();
				int classCnt = LayerUtil.getAvg(userCnt, data.classUserCnt + data.offset);
				cellAvgOffset[t][c] = LayerUtil.getAvgOffset(userCnt, data.classUserCnt, classCnt);
				cellAllOffset[t][c] = LayerUtil.getAllOffset(userCnt, data.classUserCnt, classCnt);
				cellClassCnt[t][c] = classCnt;
				teamClassCnt[t] += classCnt;
				subClassCnt[c] += classCnt;
				cellNeedCnt[t][c] = classCnt;
				cell.scoreCnt = new int[data.scoreSegLength];
				cell.sexCnt = new int[2];
				c++;
			}
			
			if(teamClassCnt[t] > data.classCnt){
				teamError[t] = true;
			}
			
			t ++;
		}
	}
	
	private void adjustNeedCnt(){
		while(true){
			int index = -1;
			int minRem = 10;
			for (int t = 0; t < rowLength; t++) {
				if (teamError[t]) {
					// 检查多出的科目
					for (int s = 0; s < colLength; s++) {
						if(subClassCnt[s] > data.subClassCnt[s]){
							if(cellAvgOffset[t][s] < minRem){
								index = (t << 4) + s;
								minRem = cellAvgOffset[t][s];
							}
						}
					}
				}
			}
			
			if (index != -1){
				int t = index >> 4;
				int s = index & 15;
				cellNeedCnt[t][s] = cellNeedCnt[t][s] - 1;
				isError[t][s] = true;
				resetTeamError();
			} else {
				return ;
			}
			
			if(!checkTeamError()){
				return ;
			}
		}
	}
	
	private boolean checkTeamError(){
		for (int t = 0; t < rowLength; t++) {
			if(teamError[t]){
				return true;
			}
		}
		return false;
	}
	
	private void resetTeamError(){
		int t=0;
		for (int s = 0; s < colLength; s++) {
			subClassCnt[s] = 0;
		}
		
		for(List<TeamCell> cells : teamCells){
			int c = 0;
			teamClassCnt[t] = 0;
			teamError[t] = false;
			for(TeamCell cell : cells){
				int userCnt = cell.getUserCnt();
				int classCnt = cellNeedCnt[t][c];
				cellAvgOffset[t][c] = LayerUtil.getAvgOffset(userCnt, data.classUserCnt, classCnt);
				cellAllOffset[t][c] = LayerUtil.getAllOffset(userCnt, data.classUserCnt, classCnt);
				teamClassCnt[t] += classCnt;
				subClassCnt[c] += classCnt;
				c++;
			}
			if(teamClassCnt[t] > data.classCnt){
				teamError[t] = true;
			}
			t ++;
		}
	}
	
	private void setOldRemAndClassCnt(){
		for (int t = 0; t < rowLength; t++) {
			for (int s = 0; s < colLength; s++) {
				oldAvgOffset[t][s] = cellAvgOffset[t][s];
				oldAllOffset[t][s] = cellAllOffset[t][s];
				oldClassCnt[t][s] = cellClassCnt[t][s];
			}
		}
	}
	
	private void resetRemAndClassCnt(){
		int t=0;
		for(List<TeamCell> cells : teamCells){
			int c = 0;
			teamClassCnt[t] = 0;
			teamError[t] = false;
			for(TeamCell cell : cells){
				cell.calculateCnt(data.combs);
				int userCnt = cell.getUserCnt();
				int needClassCnt = cellNeedCnt[t][c];
				int classCnt = LayerUtil.getAvg(userCnt, data.classUserCnt + data.offset);
				cellAvgOffset[t][c] = LayerUtil.getAvgOffset(userCnt, data.classUserCnt, needClassCnt);
				cellAllOffset[t][c] = LayerUtil.getAllOffset(userCnt, data.classUserCnt, needClassCnt);
				cellClassCnt[t][c] = classCnt;
				teamClassCnt[t] += isError[t][c] ? needClassCnt : classCnt;
				c++;
			}
			
			if(teamClassCnt[t] > data.classCnt){
				teamError[t] = true;
			}
			
			t ++;
		}
	}
	
	private void resetCellError(){
		int t=0;
		for(List<TeamCell> cells : teamCells){
			int c = 0;
			for(TeamCell cell : cells){
				int userCnt = cell.getUserCnt();
				int needClassCnt = cellNeedCnt[t][c];
				int classCnt = LayerUtil.getAvg(userCnt, data.classUserCnt + data.offset);
				isError[t][c] = needClassCnt < classCnt;
				c++;
			}
			t ++;
		}
	}
	
	public void printClassCnt(){
		int t=0;
		String subs = "subs：";
		for(int i=0;i<colLength;i++){
			subs += "	" + data.subs[teamCells.get(0).get(i).getSubIndex()];
		}
		System.out.println(subs);
		for(List<TeamCell> cells : teamCells){
			int c = 0;
			String classes = (t+1) + ";";
			
			for(TeamCell cell : cells){
				int userCnt = cell.getUserCnt();
				int needClassCnt = cellNeedCnt[t][c];
				int classCnt = LayerUtil.getAvg(userCnt, data.classUserCnt + data.offset);
				classes += "	" + needClassCnt + "," + classCnt+","+isError[t][c];
				c++;
			}
			System.out.println(classes);
			
			if(teamClassCnt[t] > data.classCnt){
				teamError[t] = true;
			}
			
			t ++;
		}
	}
	
	public void printOffset(){
		String cols = "";
		for (int c = 0; c < colLength; c++) {
			cols += "	"+data.subs[c];
		}
		System.out.println(cols);
		for (int r = 0; r < rowLength; r++) {
			String result = "";
			for (int c = 0; c < colLength; c++) {
				result += "	"+cellAvgOffset[r][c]+","+cellAllOffset[r][c];
			}
			System.out.println(result);
		}
		
	}
	
	
	public void adjustCombUse(int val){
		Comb bestComb = null;
		int bestPlusIndex = -1;
		int bestMinusIndex = -1;
		int maxScore = -1;
		setOldRemAndClassCnt();
		for (int c = data.combs.length - 1; c >= 0; c--) {
			Comb comb = data.combs[c];
			for (int index1 = 0; index1 < rowLength; index1++) {
				for (int index2 = 0; index2 < rowLength; index2++) {
					if(index1 != index2){
						int score = getChangeCombScore(comb, index1, index2, val);
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
//		System.out.println(maxScore);
		if(bestComb != null){
			bestComb.use[bestPlusIndex] += val;
			bestComb.use[bestMinusIndex] -= val;
			resetRemAndClassCnt();
			resetCellError();
		}
	}
	
	public int getChangeCombScore(Comb comb, int plusIndex, int minusIndex, int val){
		int score = 0;
		if(comb.use[minusIndex] < val){
			return -1;
		}
		comb.use[plusIndex] += val;
		comb.use[minusIndex] -= val;
		
		resetRemAndClassCnt();
		// 检查teamError
		if(checkTeamError()){
			comb.use[plusIndex] -= val;
			comb.use[minusIndex] += val;
			resetRemAndClassCnt();
			return -1;
		}
		
		for (int t = 0; t < rowLength; t++) {
			for (int s = 0; s < colLength; s++) {
				int tmp = Math.abs(oldAllOffset[t][s]) - Math.abs(cellAllOffset[t][s]);
				if(isError[t][s]){
					if(tmp > 0){
						score += 20;
					}else if(tmp < 0){
						score -= 20;
					}
				}else{
					if (tmp > 0) {
						if (oldAllOffset[t][s] > 0) {
							score += oldAvgOffset[t][s];
						} else {
							score += 1;
						}
					} else if (tmp < 0) {
						if (oldAllOffset[t][s] > 0) {
							score -= oldAvgOffset[t][s];
						} else {
							score -= 1;
						}
					}
				}
			}
		}
		comb.use[plusIndex] -= val;
		comb.use[minusIndex] += val;
		resetRemAndClassCnt();
		return score;
	}
	
	public void clear(){
		DataUtil.clearArrayInt(cellAvgOffset);
		DataUtil.clearArrayInt(cellAllOffset);
		DataUtil.clearArrayInt(cellClassCnt);
		DataUtil.clearArrayInt(cellNeedCnt);
		DataUtil.clearArrayInt(oldAvgOffset);
		DataUtil.clearArrayInt(oldAllOffset);
		DataUtil.clearArrayInt(oldClassCnt);
		DataUtil.clearArrayBl(isError);
		DataUtil.clearListData(teamCells);
		cellAvgOffset = null;
		cellAllOffset = null;
		cellClassCnt = null;
		cellNeedCnt = null;
		oldAvgOffset = null;
		oldAllOffset = null;
		oldClassCnt = null;
		teamClassCnt = null;
		subClassCnt = null;
		teamError = null;
		isError = null;
	}
	
}
