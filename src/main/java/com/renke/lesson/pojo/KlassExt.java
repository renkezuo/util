package com.renke.lesson.pojo;

import java.util.List;

public class KlassExt {
	private Klass klass;
	private Integer level;
	private Long subId;
	private List<User> users;
	public Klass getKlass() {
		return klass;
	}
	public void setKlass(Klass klass) {
		this.klass = klass;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		String str = "KlassExt [klass=" + klass + ", level=" + level +", subId=" + subId +",userCnt="+(users==null?0:users.size())+ ",users='";
		if(users!=null && !users.isEmpty()){
			for(int i=0;i<users.size();i++){
				str += users.get(i).getUserName()+",";
			}
			str = str.substring(0,str.length()-1);
		}
		str += "']";
		return str;
	}
	
}
