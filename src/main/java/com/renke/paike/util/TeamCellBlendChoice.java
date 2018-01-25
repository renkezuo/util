package com.renke.paike.util;

import com.renke.paike.model.Comb;

public class TeamCellBlendChoice extends TeamCellBlendAbstract{
	
	public TeamCellBlendChoice(int teamId, int blockIndex, int subIndex){
		this.teamId = teamId;
		this.blockIndex = blockIndex;
		this.subIndex = subIndex;
	}
	
	@Override
	public void calculateCnt(Comb[] combs){
		userCnt = 0;
		for (Comb comb : combs){
			for(int subIndex : comb.subIndexes){
				if(this.subIndex == subIndex){
					userCnt += comb.use[blockIndex];
				}
			}
		}
	}

	@Override
	public String toString() {
		return (blockIndex + 1) + "-" + (subIndex + 1) + "选："+ userCnt;
	}
}
