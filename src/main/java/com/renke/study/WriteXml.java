package com.renke.study;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class WriteXml {
	String xmlName = "workflow.xml";
	private Document document;
	
	/**
	 * 更换节点名称
	 * @param tagName		标签名称
	 * @param newNodeName	新的节点名称
	 */
	public void editNode(String tagName,String newName){
		XMLWriter xw = null;
		OutputFormat format = new OutputFormat();
		SAXReader sr = new SAXReader();	
		String oldName = "";
		try {
			sr.setEncoding("UTF-8");
			setDocument(sr.read(this.getClass().getResource(xmlName)));
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			System.out.println(root.getName());
			for (int i = 0; i < list.size(); i++) {
				Element e = list.get(i);
				if(tagName.equals(e.getName())){
					oldName = e.attributeValue("name");
//					e.setName(newName);
					e.addAttribute("name", newName);
				}
			}
//			List<Element> flow = e.elements("flow");
//			flow = flow.get(0).elements("task");
//			flow.get(0).setName("task1");
			format.setEncoding("UTF-8");//此处改为GBK  无乱码。
			File file = new File(this.getClass().getResource(xmlName).toURI());
			xw = new XMLWriter(new FileOutputStream(file),format);
			xw.write(document);
			xw.close();
			System.out.println("节点"+tagName+" 由  "+oldName+" 修改为 "+newName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更换节点名称
	 * @param tagName		标签名称
	 * @param newNodeName	新的节点名称
	 */
	public void editText(String name,String newText){
		XMLWriter xw = null;
		OutputFormat format = new OutputFormat();
		SAXReader sr = new SAXReader();	
		String oldName = "";
		try {
			sr.setEncoding("UTF-8");
			setDocument(sr.read(this.getClass().getResource(xmlName)));
			Element root = document.getRootElement();
			Element e = printE(root,name);
			List<Element> list = e.elements();
			Element e2 = list.get(2);
			System.out.println(e2.getText());
			e2.setText("他好我也好");
			System.out.println(e2.getText());
//			List<Element> flow = e.elements("flow");
//			flow = flow.get(0).elements("task");
//			flow.get(0).setName("task1");
			format.setEncoding("UTF-8");//此处改为GBK  无乱码。
			File file = new File(this.getClass().getResource(xmlName).toURI());
			xw = new XMLWriter(new FileOutputStream(file),format);
			xw.write(document);
			xw.close();
			System.out.println("节点"+name+" 中  内容被"+oldName+" 修改为 "+newText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Element printE(Element e,String name){
		List<Element> list = e.elements();
		Element reE = null;
		if(list!=null&&list.size()>0){
			for(Element ment : list){
				if(name.equals(ment.attributeValue("name"))){
					reE = ment;
					break;
				}else reE =  printE(ment,name);
			}
		}
		return reE;
	}
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document){
		this.document = document;
	}
}
