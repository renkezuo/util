package com.renke.util.db;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlobObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id ;
	private String name ;
	private String value ; 
	private Date dt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "id:["+this.id+"],name:["+this.name+"],value:["+this.value+"],date:["+sdf.format(this.dt)+"]";
	}
	
	
}
