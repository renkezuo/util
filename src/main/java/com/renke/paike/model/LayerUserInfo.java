package com.renke.paike.model;

public class LayerUserInfo extends KlassUser{
	public String lekeNo;
	public String stuNo;
	public Integer isMan = 0;
	public double[] scores = new double[7];
	public int[] scoreSegIndex = new int[7];
	public boolean[] choices = new boolean[7];
//	public int choice = 0;
	
	@Override
	public String toString() {
		return lekeNo + "-" + super.getUserName();
	}
}
