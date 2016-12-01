package com.renke.util.json;

public class Element {
	public String _id;
	public String name;
	public Element _parent;
	public Element[] ownedElements;
	
	@Override
	public String toString() {
		String str = "{_id:"+_id+",name:"+name;
		if(_parent!=null) str+= ",_parent:"+_parent.toString();
		if(ownedElements!=null){
			str += ",ownedElements:[";
			for(Element e : ownedElements){
				str += e.toString()+",";
			}
			if(!str.endsWith("["))
				str = str.substring(0,str.length()-1);
			str += "]";
		}
		str += "}";
		return str;
	}
}
