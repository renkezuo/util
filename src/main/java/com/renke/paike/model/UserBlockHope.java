package com.renke.paike.model;

import com.renke.paike.util.DataUtil;

public class UserBlockHope implements Clear{
	public int[][] wishSubNeedClassCnt;
	public int[][] wishSubNeedUserCnt;
	public int[][] wishSubUserCnt;
	@Override
	public void clear() {
		DataUtil.clearArrayInt(wishSubNeedClassCnt);
		DataUtil.clearArrayInt(wishSubNeedUserCnt);
		DataUtil.clearArrayInt(wishSubUserCnt);
	}
}
