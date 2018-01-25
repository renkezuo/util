package com.renke.paike.model;

public class LayerUserInfo {
	public Long userId;
	public String lekeNo;
	public String stuNo;
	public String userName;
	public Long classId;
	public boolean man;
	public double[] scores = new double[7];
	public boolean[] choices = new boolean[7];
	public int choice = 0;
	
	@Override
	public String toString() {
		return lekeNo + "-" + userName;
	}
}
