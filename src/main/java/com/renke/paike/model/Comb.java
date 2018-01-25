package com.renke.paike.model;

import java.util.List;

import com.renke.paike.util.DataUtil;

public class Comb implements Clear{
	public int combIndex;
	public Integer combKey;
	public int[] subIndexes;
	public int[] studySubExists;
	public int cnt;
	public int use[];
	public List<List<LayerUserInfo>> blockUsers; 
	public List<LayerUserInfo> users;
	@Override
	public void clear() {
		subIndexes = null;
		studySubExists = null;
		use = null;
		if (users != null) {
			users.clear();
			users = null;
		}
		DataUtil.clearListData(blockUsers);
		blockUsers = null;
	}
}