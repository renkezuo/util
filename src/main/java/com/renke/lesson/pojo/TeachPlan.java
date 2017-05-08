package com.renke.lesson.pojo;

import java.util.List;

public class TeachPlan {
	//年级
	private Long gradeId;
	private String gradeName;
	//班级
	private Long classId;
	private String className;
	//场地
	private Long areaId;
	private String areaName;
	//科目
	private Long subId;
	private String subName;
	//老师
	private Long teacherId;
	private String teacherName;
	//周课时数
	private int weekCnt;
	//连堂数
	private int seriesCnt;
	//常规1，单周2，双周3
	private int weekType;
	//用户列表
	private List<User> user;
	
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(int weekCnt) {
		this.weekCnt = weekCnt;
	}
	public int getSeriesCnt() {
		return seriesCnt;
	}
	public void setSeriesCnt(int seriesCnt) {
		this.seriesCnt = seriesCnt;
	}
	public int getWeekType() {
		return weekType;
	}
	public void setWeekType(int weekType) {
		this.weekType = weekType;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
}
