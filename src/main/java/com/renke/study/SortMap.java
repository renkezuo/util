package com.renke.study;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class SortMap extends JFrame implements ActionListener {
	/**
	* @param args
	*/
	JButton button = new JButton("=");
	JTextField textField1 = new JTextField(5);
	JTextField textField2 = new JTextField(5);
	JTextField textField3 = new JTextField(5);
	JLabel label1 = new JLabel("请输入: ");
	JLabel label2 = new JLabel("+");

	public static void main(String[] args) {
		SortMap com = new SortMap();
		com.init();
		com.setVisible(true);
	}
	public void init() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("简单计算程序");
		this.setLocation(400, 250);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.white);
		jPanel.setPreferredSize(new Dimension(300, 300));
		this.getContentPane().add(jPanel, BorderLayout.CENTER);
		jPanel.add(label1);
		jPanel.add(textField1);
		jPanel.add(label2);
		jPanel.add(textField2);
		jPanel.add(button);
		button.addActionListener(this);
		jPanel.add(textField3);
	}
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == button) {
			String string1 = textField1.getText();
			String string2 = textField2.getText();
			if (string1.trim().length() == 0 || string2.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "请先输入值!", "系统提示",
				JOptionPane.ERROR_MESSAGE);
			} else {
			int a, b, c;
				a = Integer.parseInt(string1);
				b = Integer.parseInt(string2);
				c = a + b;
				textField3.setText(String.valueOf(c));
			}
		}
	}
}
