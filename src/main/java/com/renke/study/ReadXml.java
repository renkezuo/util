package com.renke.study;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author Joker
 * @vision $vision 1.00 $
 */
public class ReadXml {
	private Document document;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	/**
	 * 读取配置文件，初始化流程操作类
	 * @throws Exception
	 */
	public void initWorkFlow() throws Exception {
		SAXReader reader = new SAXReader();
		//把xml文件读入到document对象中，注测试xml和class文件同级
		reader.read(this.getClass().getResource("") + "test.xml");
		setDocument(reader.read(this.getClass().getResource("") +"test.xml"));
		//取根节点
		Element root = document.getRootElement();
		//遍历节点，输出
		printE(root);
	}
	//取所有节点
	public void printE(Element e){
		List<Element> list = e.elements();
		if(list!=null&&list.size()>0){
			for(Element ment : list){
				System.out.println(ment.getPath() + ":" + ment.getTextTrim());
				printE(ment);
			}
		}
	}
	public static void main(String[] args) {
		ReadXml rx = new ReadXml();
		try {
			rx.initWorkFlow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
