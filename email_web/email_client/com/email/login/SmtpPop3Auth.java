package com.email.login;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * 保存账号和密码的类
 * @author zhujuan
 *
 */
public class SmtpPop3Auth extends Authenticator {
	String user, password;

	// 设置帐号信息
	public void setAccount(String user, String password) {
		this.user = user;
		this.password = password;
	}

	// 取得PasswordAuthentication对象
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
}