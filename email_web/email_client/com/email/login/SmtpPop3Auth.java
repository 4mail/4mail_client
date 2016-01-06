package com.email.login;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * ��˵����SMTP/POP3��Ȩ��֤��
 * 
 * @author ����: LiuJunGuang
 * @version ����ʱ�䣺2011-4-22 ����06:42:08
 */

public class SmtpPop3Auth extends Authenticator {
	String user, password;

	// �����ʺ���Ϣ
	public void setAccount(String user, String password) {
		this.user = user;
		this.password = password;
	}

	// ȡ��PasswordAuthentication����
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
}