package com.renke.lesson.pojo;

public class Klass {
	// 班级ID
	private Long classId;
	// 班级名称
	private String className;
	// 班级类型
	private Integer type;
	// 关联ID
	private Long relationId;
	// 年级ID
	private Long gradeId;
	// 年级名称
	private String gradeName;
	// 学段ID
	private Long schoolStageId;
	// 学校ID
	private Long schoolId;
	// 班级人数
	private Integer headCount;
	
	private Integer teamId;
	
	private Long subId;
	
	private String subName;
	
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
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
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
	public Long getSchoolStageId() {
		return schoolStageId;
	}
	public void setSchoolStageId(Long schoolStageId) {
		this.schoolStageId = schoolStageId;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getHeadCount() {
		return headCount;
	}
	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}
	@Override
	public String toString() {
		return "Klass [className=" + className + "]";
	}
}
