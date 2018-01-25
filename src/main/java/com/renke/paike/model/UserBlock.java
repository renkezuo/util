package com.renke.paike.model;

public class UserBlock implements Clear{
	public int[] combUse;
	public int[] subNeedClassCnt;
	public int[] studySubNeedClassCnt;
	
	public int[] subNeedUserCnt;
	public int[] studySubNeedUserCnt;
	public int[] subUserCnt;
	public int[] studySubUserCnt;
	
	@Override
	public void clear(){
		combUse = null;
		subNeedClassCnt = null;
		studySubNeedClassCnt = null;
		subNeedUserCnt = null;
		studySubNeedUserCnt = null;
		subUserCnt = null;
		studySubUserCnt = null;
	}
	
}
