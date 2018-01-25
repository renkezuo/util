package com.renke.paike.util;

import com.renke.paike.model.Comb;

public interface ITeamCell {
	public void calculateCnt(Comb[] combs);
	public int getTeamId();
	public int getSubIndex();
	public int getClassCnt(int classUserCnt, int offset);
	public int getQuotCnt(int classUserCnt);
	public int getBlockIndex();
	public int getRemCnt(int classUserCnt, int offset);
}
