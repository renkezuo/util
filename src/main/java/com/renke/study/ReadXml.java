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
	 * ��ȡ�����ļ�����ʼ�����̲�����
	 * @throws Exception
	 */
	public void initWorkFlow() throws Exception {
		SAXReader reader = new SAXReader();
		//��xml�ļ����뵽document�����У�ע����xml��class�ļ�ͬ��
		reader.read(this.getClass().getResource("") + "test.xml");
		setDocument(reader.read(this.getClass().getResource("") +"test.xml"));
		//ȡ���ڵ�
		Element root = document.getRootElement();
		//�����ڵ㣬���
		printE(root);
	}
	//ȡ���нڵ�
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
