package com.renke.util.db;

import com.google.gson.Gson;

public class Obj {
	public int total;
	public int total1;
	public int total2;
	public Sub _id;
	public static void main(String[] args) {
		Gson gs = new Gson();
		Obj obj = gs.fromJson("{  \"_id\" : {  \"aa\" : NumberLong(918739),  \"bb\" : NumberLong(20786) },  \"total1\" : 5,  \"total\" : 2,  \"total2\" : 2 }", Obj.class);
		System.out.println(obj._id.aa);
	}
}
class Sub{
	public String aa;
	public String bb;
}