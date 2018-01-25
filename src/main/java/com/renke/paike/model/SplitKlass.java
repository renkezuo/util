package com.renke.paike.model;

import java.util.List;

public class SplitKlass extends Klass{
	private List<Long> userIds;
	private List<KlassUser> students;
	private boolean isChoice;
	private int level;
	private Long subId;
	private String subName;
	private int teamType;
	private int teamId;
	// 分段需要人数
	private int[] scoreNeedCnt;
	private int[] sexNeedCnt;
	// 分段使用人数
	private int[] scoreCnt;
	private int[] sexCnt;
	
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public boolean isChoice() {
		return isChoice;
	}
	public List<KlassUser> getStudents() {
		return students;
	}
	public void setStudents(List<KlassUser> students) {
		this.students = students;
	}
	public void setChoice(boolean isChoice) {
		this.isChoice = isChoice;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public int getTeamType() {
		return teamType;
	}
	public void setTeamType(int teamType) {
		this.teamType = teamType;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int[] getScoreNeedCnt() {
		return scoreNeedCnt;
	}
	public void setScoreNeedCnt(int[] scoreNeedCnt) {
		this.scoreNeedCnt = scoreNeedCnt;
	}
	public int[] getSexNeedCnt() {
		return sexNeedCnt;
	}
	public void setSexNeedCnt(int[] sexNeedCnt) {
		this.sexNeedCnt = sexNeedCnt;
	}
	public int[] getScoreCnt() {
		return scoreCnt;
	}
	public void setScoreCnt(int[] scoreCnt) {
		this.scoreCnt = scoreCnt;
	}
	public int[] getSexCnt() {
		return sexCnt;
	}
	public void setSexCnt(int[] sexCnt) {
		this.sexCnt = sexCnt;
	}
}
