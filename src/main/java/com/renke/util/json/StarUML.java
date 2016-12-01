package com.renke.util.json;

public class StarUML {
	public String _type;
	public String _id;
	public String name;
	public String type;
	public int length;
	public boolean primaryKey;
	public boolean foreignKey;
	public boolean nullable;
	public boolean unique;
	public StarUML[] ownedElements;
	public StarUML[] columns;
	
	@Override
	public String toString() {
		String str = "{_id:"+_id+",name:"+name+",_type:"+_type;
		if(ownedElements!=null){
			str += ",ownedElements:[";
			for(StarUML uml : ownedElements){
				str += uml.toString()+",";
			}
			if(!str.endsWith("["))
				str = str.substring(0,str.length()-1);
			str += "]";
		}
		if(columns!=null){
			str += ",columns:[";
			for(StarUML uml : columns){
				str += uml.toString()+",";
			}
			if(!str.endsWith("["))
				str = str.substring(0,str.length()-1);
			str += "]";
		}
		str += "}";
		return str;
	}
}
