package com.renke.paike.util;

import java.util.ArrayList;
import java.util.List;

import com.renke.paike.model.LayerData;

public class LayerTeamUtil {

	public static int[][] getSubTeamClassCnt(LayerData data, int teamSize) {
		int[][] subTeamClassCnt = new int[4][teamSize];
		int[][] result = new int[4][teamSize];
		int[] offset = new int[4];
		int[] teamClassCnt = new int[teamSize];
		int countCnt = 0;
		for (int i = 0; i < 4; i++) {
			int classCnt = data.subClassCnt[data.topSubs[i]];
			subTeamClassCnt[i] = LayerUtil.getSegments(classCnt, teamSize);
			countCnt += classCnt;
		}
		
		int minCnt = countCnt / teamSize;
		int maxCnt = minCnt;
		if(countCnt % teamSize > 0){
			maxCnt += 1;
		}
		// 分两块统计
		while(true){
			boolean valid = true;
			// 重排科目班级数顺序
			for (int s = 0; s < 4; s++) {
				result[s] = DataUtil.getArrayByOffset(subTeamClassCnt[s], offset[s]);
			}
			
			for (int t = 0; t < teamSize; t++) {
				teamClassCnt[t] = 0;
				for (int s = 0; s < 4; s++) {
					teamClassCnt[t] += result[s][t];
				}
			}
			
			for (int t = 0; t < teamSize; t++) {
				// 班级数小于最小，或大于最大，则重新安排顺序
				if (teamClassCnt[t] < minCnt || teamClassCnt[t] > maxCnt) {
					valid = false;
				}
			}
			
			if(valid){
				 return result;
			}
			
			valid = false;
			for (int s = 3; s > 0; s--) {
				// 如果所有的tmp[t]都为3时，表示全部循环完毕，则跳出大循环
				if (offset[s] >= teamSize) {
					offset[s] = 0;
				} else {
					offset[s] += 1;
					valid = true;
					break;
				}
			}
			
			if(!valid){
				break;
			}
		}
		return null;
	}
	
	

	/**
	 * A1B2C3D4/B1C2D3A4/C1D2A3B4/D1A2B3C4
	 * A1B2C3/B1C2A3/C1A2B3
	 * 若需要修改cell的顺序，需要处理teamTable代码，使其顺序相同
	 * @param teamSize
	 * @param subLength
	 * @return
	 * @author Z.R.K
	 */
	public static List<List<TeamCell>> getTeamCellsAlone(int teamSize, int subLength, boolean isChoice) {
		List<List<TeamCell>> teamCells = new ArrayList<>(teamSize);
		for (int t = 0; t < teamSize; t++) {
			List<TeamCell> cells = new ArrayList<>(subLength);
			for (int s = 0; s < subLength; s++) {
				int[] blockIndexes = new int[teamSize];
				cells.add(new TeamCell(t, s, isChoice, blockIndexes));
				for (int w = 0; w < teamSize; w++) {
					blockIndexes[w] = (t + w) % teamSize;
				}
				blockIndexes = null;
			}
			teamCells.add(cells);
			cells = null;
		}
		return teamCells;
	}

	public static List<List<ITeamCell>> getTeamCells(int startTeamId, int length) {
		List<List<ITeamCell>> teamCells = new ArrayList<>(length);
		if(length == 3){
			List<ITeamCell> cells = new ArrayList<>(6);
			// A1 B2 C3
			cells.add(new TeamCellBlendChoice(0, 0, 0));
			cells.add(new TeamCellBlendChoice(0, 1, 1));
			cells.add(new TeamCellBlendChoice(0, 2, 2));
			cells.add(new TeamCellBlendStudy(0, 0, 0));
			cells.add(new TeamCellBlendStudy(0, 1, 1));
			cells.add(new TeamCellBlendStudy(0, 2, 2));
			teamCells.add(cells);
			cells = new ArrayList<>(6);
			// B1 C2 A3
			cells.add(new TeamCellBlendChoice(1, 1, 0));
			cells.add(new TeamCellBlendChoice(1, 2, 1));
			cells.add(new TeamCellBlendChoice(1, 0, 2));
			cells.add(new TeamCellBlendStudy(1, 1, 0));
			cells.add(new TeamCellBlendStudy(1, 2, 1));
			cells.add(new TeamCellBlendStudy(1, 0, 2));
			teamCells.add(cells);
			cells = new ArrayList<>(6);
			// C1 A2 B3
			cells.add(new TeamCellBlendChoice(2, 2, 0));
			cells.add(new TeamCellBlendChoice(2, 0, 1));
			cells.add(new TeamCellBlendChoice(2, 1, 2));
			cells.add(new TeamCellBlendStudy(2, 2, 0));
			cells.add(new TeamCellBlendStudy(2, 0, 1));
			cells.add(new TeamCellBlendStudy(2, 1, 2));
			teamCells.add(cells);
		}else{
			List<ITeamCell> cells = new ArrayList<>(6);
			// A1 B2 C3
			cells.add(new TeamCellBlendChoice(0, 0, 0));
			cells.add(new TeamCellBlendChoice(0, 1, 1));
			cells.add(new TeamCellBlendChoice(0, 2, 2));
			cells.add(new TeamCellBlendStudy(0, 0, 0));
			cells.add(new TeamCellBlendStudy(0, 1, 1));
			cells.add(new TeamCellBlendStudy(0, 2, 2));
			teamCells.add(cells);
			cells = new ArrayList<>(6);
			// A2 B3 C4
			cells.add(new TeamCellBlendChoice(1, 0, 1));
			cells.add(new TeamCellBlendChoice(1, 1, 2));
			cells.add(new TeamCellBlendChoice(1, 2, 3));
			cells.add(new TeamCellBlendStudy(1, 0, 1));
			cells.add(new TeamCellBlendStudy(1, 1, 2));
			cells.add(new TeamCellBlendStudy(1, 2, 3));
			teamCells.add(cells);
			cells = new ArrayList<>(6);
			// B1 C2 A4
			cells.add(new TeamCellBlendChoice(2, 1, 0));
			cells.add(new TeamCellBlendChoice(2, 2, 1));
			cells.add(new TeamCellBlendChoice(2, 0, 3));
			cells.add(new TeamCellBlendStudy(2, 1, 0));
			cells.add(new TeamCellBlendStudy(2, 2, 1));
			cells.add(new TeamCellBlendStudy(2, 0, 3));
			teamCells.add(cells);
			cells = new ArrayList<>(6);
			// C1 A3 B4
			cells.add(new TeamCellBlendChoice(3, 2, 0));
			cells.add(new TeamCellBlendChoice(3, 0, 2));
			cells.add(new TeamCellBlendChoice(3, 1, 3));
			cells.add(new TeamCellBlendStudy(3, 2, 0));
			cells.add(new TeamCellBlendStudy(3, 0, 2));
			cells.add(new TeamCellBlendStudy(3, 1, 3));
			teamCells.add(cells);
			cells = null;
		}
		return teamCells;
	}
}
