package com.renke.paike.util;

public abstract class TeamCellBlendAbstract implements ITeamCell{
	public Integer teamId;
	public Integer subIndex;
	public int blockIndex;
	public int userCnt = 0;
	@Override
	public int getClassCnt(int classUserCnt, int offset){
		return LayerUtil.getAvg(userCnt, classUserCnt + offset);
	}

	@Override
	public int getRemCnt(int classUserCnt, int offset) {
		return LayerUtil.getRemByOffset(userCnt, classUserCnt, offset);
	}
	
	@Override
	public int getQuotCnt(int classUserCnt) {
		if((userCnt % classUserCnt) < (classUserCnt / 2)){
			return userCnt % classUserCnt;
		}
		return 0;
	}
	
	@Override
	public int getTeamId() {
		return teamId == null ? 0 : teamId;
	}

	@Override
	public int getSubIndex() {
		return subIndex == null ? -1 : subIndex;
	}
	
	@Override
	public int getBlockIndex() {
		return blockIndex;
	}
	
}
