package com.renke.mail;

import java.util.Properties;

/**
 * @author Z.R.K
 * @description
 * @create 2018-08-15 13:40:56
 **/
public class MailConfig {
	public final static Properties conf = new Properties();
	
	static{
		conf.put("mail.smtp.auth", true);
		conf.put("mail.smtp.host", "smtp.cnstrong.cn");
		conf.put("mail.user", "zuorenke@cnstrong.cn");
		conf.put("mail.password", "MzXxqSV7p6Ej4QPw");
	}
}
