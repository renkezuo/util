package com.renke.mail;


import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Z.R.K
 * @description
 * @create 2018-08-15 13:40:36
 **/
public class Sender {
	public static void main(String[] args) {
		
		final String userName =  MailConfig.conf.getProperty("mail.user");
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String password = MailConfig.conf.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(MailConfig.conf, authenticator);
		
		try{
			// 创建邮件消息
			MimeMessage message = new MimeMessage(mailSession);
			// 设置邮件标题
			message.setSubject("邮件的标题");
			// 设置邮件的内容体
			message.setContent("自己发送邮件的内容体<br>测试换行邮件", "text/html;charset=UTF-8");
			// 设置发件人
			InternetAddress from = new InternetAddress(userName);
			message.setFrom(from);
			// 设置收件人
			InternetAddress[] to = new InternetAddress[]{
					new InternetAddress("zuorenke@cnstrong.cn")
			};
			message.setRecipients(RecipientType.TO, to);
			message.setRecipients(RecipientType.CC, to);
			
			// 发送邮件
			Transport.send(message);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
