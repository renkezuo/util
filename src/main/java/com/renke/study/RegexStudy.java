package com.renke.study;

public class RegexStudy {
	private String firstName;
	public boolean check(String str,String regex){
		return str.matches(regex);
	}
	public boolean equals(Object obj){
		if(obj instanceof RegexStudy){
			RegexStudy rs = (RegexStudy)obj;
			return firstName.equals(rs.firstName);
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		String regex = "^[\\w|@|\\-|\\.]{6,16}$";
		regex = "^201[0-9]-((0?[0-9])|(1[0-2]))- (([0-1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]$";
//		String regex = "^[\\w@\\-\\.]{6,16}$";
		regex = "^1 2$";
		System.out.println("1 2".matches(regex));
	}
}
