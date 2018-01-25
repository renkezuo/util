package com.renke.paike.util;

import java.util.List;

import com.renke.paike.model.Comb;
import com.renke.paike.model.LayerUserInfo;
import com.renke.paike.model.SplitKlass;

public class TeamCell {

	public Integer teamId;
	public Integer subIndex;
	public int[] blockIndexes;
	public int userCnt = 0;
	public int[] scoreCnt;
	public int[] sexCnt;
	public List<LayerUserInfo> users;
	public List<SplitKlass> klasses;
	public boolean isChoice;
	
	public TeamCell(int teamId, int subIndex, boolean isChoice, int[] blockIndexes){
		this.teamId = teamId;
		this.subIndex = subIndex;
		this.blockIndexes = blockIndexes;
		this.isChoice = isChoice;
	}

	public void calculateCnt(Comb[] combs) {
		userCnt = 0;
		for (Comb comb : combs){
			for (int w = 0; w < blockIndexes.length; w++) {
				if (this.subIndex == comb.subIndexes[w]) {
					userCnt += comb.use[blockIndexes[w]];
				}
			}
		}
	}
	
	public int getUserCnt(){
		return userCnt;
	}

	public int getSubIndex() {
		return subIndex == null ? -1 : subIndex;
	}
	
	public int getClassCnt(int classUserCnt, int offset) {
		return LayerUtil.getAvg(userCnt, classUserCnt + offset);
	}
	
	public int[] getBlockIndexes(){
		return blockIndexes;
	}
}
