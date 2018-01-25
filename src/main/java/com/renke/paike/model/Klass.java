package com.renke.paike.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	// 是否过期
	private Boolean isExpired;

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

	public String getShortName() {
		return cutoutShortName(this.className);
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

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	public static String cutoutShortName(String name) {
		if (name != null) {
			Matcher matcher = Pattern.compile("\\S+\\((\\S+)\\)$").matcher(name);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return name;
	}
}
