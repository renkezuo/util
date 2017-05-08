package com.renke.lesson.pojo;

import java.util.List;

public class User {
	private Long userId ;
	private String userName;
	private List<Long> checkSubs;
	private double score1;
	private double score2;
	private double score3;
	private double score4;
	private double score5;
	private double score6;
	private double score7;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Long> getCheckSubs() {
		return checkSubs;
	}
	public void setCheckSubs(List<Long> checkSubs) {
		this.checkSubs = checkSubs;
	}
	
	public double getScore1() {
		return score1;
	}
	public void setScore1(double score1) {
		this.score1 = score1;
	}
	public double getScore2() {
		return score2;
	}
	public void setScore2(double score2) {
		this.score2 = score2;
	}
	public double getScore3() {
		return score3;
	}
	public void setScore3(double score3) {
		this.score3 = score3;
	}
	public double getScore4() {
		return score4;
	}
	public void setScore4(double score4) {
		this.score4 = score4;
	}
	public double getScore5() {
		return score5;
	}
	public void setScore5(double score5) {
		this.score5 = score5;
	}
	public double getScore6() {
		return score6;
	}
	public void setScore6(double score6) {
		this.score6 = score6;
	}
	public double getScore7() {
		return score7;
	}
	public void setScore7(double score7) {
		this.score7 = score7;
	}
	
	@Override
	public String toString() {
		String str = "User [userId=" + userId + ", userName=" + userName + ", score1=" + score1
				+ ", score2=" + score2 + ", score3=" + score3 + ", score4=" + score4 + ", score5=" + score5
				+ ", score6=" + score6 + ", score7=" + score7 + ", checkSubs='";
		if(checkSubs!=null && !checkSubs.isEmpty()){
			for(int i=0;i<checkSubs.size();i++){
				str += checkSubs.get(i)+",";
			}
			str = str.substring(0,str.length()-1);
		}
		str += "']";
		return str;
	}
}
