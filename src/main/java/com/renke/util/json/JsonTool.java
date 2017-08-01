package com.renke.util.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class JsonTool {
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException, UnsupportedEncodingException {
//		File file = new File("F:/work/model/json/宓屽.json");
//		Element e = new Gson().fromJson(new FileReader(file),Element.class);
//		System.out.println(e);
		
		File file = new File("F:/work/workspace_uml/鐢ㄦ埛缁勭粐鏉冮檺鍥�.mdj");
		StarUML uml = new Gson().fromJson(new FileReader(file),StarUML.class);
		for(StarUML u : uml.ownedElements){
			for(StarUML m : u.ownedElements){
				if("ERDEntity".equals(m._type)){
//					System.out.println("tableName:"+m.name);
					String tableName = "t_"+m.name;
					String columns = "";
					for(StarUML l: m.columns){
						columns +=l.name+" "+l.type;
						if(!l.type.equals("int")){
							columns+="("+l.length+")";
						}
						if(l.primaryKey){
							columns +=" primary key";
						}
						columns+=",";
					}
					String createTable = "create table "+tableName+" (";
					createTable += columns.substring(0, columns.length()-1)+");";
					System.out.println(createTable);
				}
			}
		}
//		System.out.println(new String(uml.toString().getBytes("UTF-8"),"UTF-8"));
		
	}
}
