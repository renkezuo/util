package com.renke.lesson.pojo;

public class School {
	// 标识学校类型[小，中，高中，大学]
	private SchoolType type;
	// 学校名称
	private String name;
	// 连续课时长度
	// 比如:上午4节，下午4节，则seg[0]=4,seg[1]=4，其他0
	private short[] seg;
}
