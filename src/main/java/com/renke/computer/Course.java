package com.renke.computer;

public class Course {
	private String cNumber;
	private String cName;
	private int cUnit;
	
	public Course(String number,String name,int unit){
		cNumber = number;
		cUnit = unit;
		cName = name;
	}
	
	public void printInfo(){
		System.out.println(cNumber + "-" + cName + "-" + cUnit);
	}
	
	public static void main(String[] args) {
		Course c = new Course("101","asP",100);
		c.printInfo();
	}
	
}

class CourseTest{
	
}