package com.renke.classloader;

public class ClassTest {
	public static int i=123;
	public static void main(String[] args) {
		try {
			//����--��֤--׼��--����--��ʼ��--ʹ��--ж��
			//���ػ����class
			//��֤
			ClassLoader.getSystemClassLoader().loadClass("com.renke.classloader.StaticSegment");//�����ص������
//			Class.forName("com.renke.classloader.StaticSegment");//��ִ�о�̬������[��ʼ��]
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
