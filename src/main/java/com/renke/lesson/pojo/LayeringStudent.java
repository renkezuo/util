package com.renke.lesson.pojo;


public class LayeringStudent{
	//�û�Id
	private Long userId;
	
	//������
	private String className;
	
	//�ֺ�
	private String loginName;
	
	//�û���
	private String userName;
	
	// �༶ѧ������ID
	private Long classStuId;
	
	// �༶ID
	private Long classId;
	
	// ѧУID
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
