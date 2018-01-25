package com.renke.paike.model;

public class KlassUser{

	// 班级学生关联ID
	private Long classStuId;
	// 班级ID
	public Long classId;
	//班级名称
	private String className;
	// 学生ID
	private Long userId;
	// 学生姓名
	public String userName;
	// 学校ID
	private Long schoolId;
	//学校名称
	private String schoolName;
	//行政班级名称
	private String klassName;
	//乐号
	private String loginName;
	//性别
	private String sex;
	//电话
	private String phone;
	
	public Long getClassStuId() {
		return classStuId;
	}
	public void setClassStuId(Long classStuId) {
		this.classStuId = classStuId;
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
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getKlassName() {
		return klassName;
	}
	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}
	public String getSex() {
		return sex;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
