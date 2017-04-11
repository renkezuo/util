package com.renke.lesson.pojo;

/**
 * 基本类
 * 编号，名称，简称
 * @author Z.R.K
 * @time 2017-04-07 11:43:54
 */
public class Base {
	private Long id;
	private String name;
	private String shortName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
