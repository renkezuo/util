package com.renke.lesson.pojo;


public class LayeringStudent{
	//用户Id
	private Long userId;
	
	//行政班
	private String className;
	
	//乐号
	private String loginName;
	
	//用户名
	private String userName;
	
	// 班级学生关联ID
	private Long classStuId;
	
	// 班级ID
	private Long classId;
	
	// 学校ID
	private Long schoolId;
	
	private String message;
	
	private String master;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	
}
