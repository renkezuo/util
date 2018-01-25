package com.renke.paike.util;

import com.renke.paike.model.Comb;

public class TeamCellBlendStudy  extends TeamCellBlendAbstract{
	
	public TeamCellBlendStudy(int teamId, int blockIndex, int subIndex){
		this.teamId = teamId;
		this.blockIndex = blockIndex;
		this.subIndex = subIndex;
	}
	
	@Override
	public void calculateCnt(Comb[] combs){
		userCnt = 0;
		for (Comb comb : combs){
			if(comb.studySubExists[subIndex] != -1){
				userCnt += comb.use[blockIndex];
			}
		}
	}
	
	@Override
	public String toString() {
		return (blockIndex + 1) + "-" + (subIndex + 1) + "学："+ userCnt;
	}

}
