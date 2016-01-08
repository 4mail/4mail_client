package com.email.login;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.email.login.AccountListTableModel;
import com.email.login.SaveAccount2XML;
import com.email.manage.GetMail;
import com.email.outbox.SendAttachMail;

/**
 * 账号操作事件
 * @author zhujuan
 *
 */
public class AccountEventAction {
	Vector<Vector<String>> accountVectors = AccountListTableModel.getVector();
	private JTextField pop_server = null, smtp_server = null, username = null, password = null;
	private JTable accountList = null;
	private GetMail getMail = null;
	private SendAttachMail sendMail = null;

	public AccountEventAction(JTextField pop_serverTF, JTextField smtp_serverTF,
			JTextField usernameTF, JTextField passwordTF, JTable accountListTF) {
		pop_server = pop_serverTF;
		smtp_server = smtp_serverTF;
		username = usernameTF;
		password = passwordTF;
		this.accountList = accountListTF;
	}
	
	public AccountEventAction(){
		
	}

	// 添加联系人
	public void addaccount() {
		if (checkLogin(pop_server.getText().trim(),smtp_server.getText().trim(),username.getText().trim(),password.getText().trim()))// 如果email地址不重复
		{
			add();// 添加
		}
		else {
			JOptionPane.showMessageDialog(null, "你添加的账号无法登陆", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void addaccount(String pop,String smtp,String username, String password) {
		if (!ReadAccountXML.findaccount(username))// 如果email地址不重复
		{
			add(pop,smtp,username,password);// 添加
		}
		else {
			JOptionPane.showMessageDialog(null, "账号已经存在,无需添加到账号列表", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// 删除联系人
		public void changeAccount(int selectRow) {
			if (selectRow < accountVectors.size() && selectRow != -1) {// 选中一行删除
				Vector<String> selected_account = accountVectors.get(selectRow);
				// 实例化收邮件对象
				getMail = GetMail.getMailInstantiate();
				getMail.setPOP3Host(selected_account.get(0));
				getMail.setUser(selected_account.get(2));
				getMail.setPassword(selected_account.get(3));
				// 实例化发邮件件对象
				sendMail = SendAttachMail.getSendMailInstantiate();
				sendMail.setSMTPHost(selected_account.get(1));
				sendMail.setUser(selected_account.get(2));
				sendMail.setPassword(selected_account.get(3));
				accountList.updateUI();
				JOptionPane.showMessageDialog(null, "切换成功", "提示",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "你没有选中任何一行不能切换账号！", "警告",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	
	// 删除联系人
	public void deleteaccount(int selectRow) {
		if (selectRow < accountVectors.size() && selectRow != -1) {// 选中一行删除
			accountVectors.remove(selectRow);
			accountList.updateUI();
		} else {
			JOptionPane.showMessageDialog(null, "你没有选中任何一行不能删除！", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// 确定修改联系人并将联系人保存为xml格式的文档
	public void ok() {
		SaveAccount2XML saveaccountsXML = new SaveAccount2XML();
		saveaccountsXML.saveAccountXml("account.xml", accountVectors);
		JOptionPane.showMessageDialog(null, "操作成功", "提示",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// 添加账号
	private void add() {
		Vector<String> accountVector = new Vector<String>();
		accountVector.add(pop_server.getText().trim());
		accountVector.add(smtp_server.getText().trim());
		accountVector.add(username.getText().trim());
		accountVector.add(password.getText().trim());
		accountVectors.add(accountVector);
		accountList.updateUI();
	}
	
	private void add(String pop,String smtp,String username, String password) {
		Vector<String> accountVector = new Vector<String>();
		accountVector.add(pop);
		accountVector.add(smtp);
		accountVector.add(username);
		accountVector.add(password);
		accountVectors.add(accountVector);
	}

	// 检测是否可以登陆
	private boolean checkLogin(String pop_server,String smtp_server,String username,String password) {
		LoginAction log = new LoginAction(smtp_server,pop_server,username,password);
		boolean isLogin = log.isLogin();
		return isLogin;
	}
}
