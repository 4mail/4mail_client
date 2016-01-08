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
 * �˺Ų����¼�
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

	// �����ϵ��
	public void addaccount() {
		if (checkLogin(pop_server.getText().trim(),smtp_server.getText().trim(),username.getText().trim(),password.getText().trim()))// ���email��ַ���ظ�
		{
			add();// ���
		}
		else {
			JOptionPane.showMessageDialog(null, "����ӵ��˺��޷���½", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void addaccount(String pop,String smtp,String username, String password) {
		if (!ReadAccountXML.findaccount(username))// ���email��ַ���ظ�
		{
			add(pop,smtp,username,password);// ���
		}
		else {
			JOptionPane.showMessageDialog(null, "�˺��Ѿ�����,������ӵ��˺��б�", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// ɾ����ϵ��
		public void changeAccount(int selectRow) {
			if (selectRow < accountVectors.size() && selectRow != -1) {// ѡ��һ��ɾ��
				Vector<String> selected_account = accountVectors.get(selectRow);
				// ʵ�������ʼ�����
				getMail = GetMail.getMailInstantiate();
				getMail.setPOP3Host(selected_account.get(0));
				getMail.setUser(selected_account.get(2));
				getMail.setPassword(selected_account.get(3));
				// ʵ�������ʼ�������
				sendMail = SendAttachMail.getSendMailInstantiate();
				sendMail.setSMTPHost(selected_account.get(1));
				sendMail.setUser(selected_account.get(2));
				sendMail.setPassword(selected_account.get(3));
				accountList.updateUI();
				JOptionPane.showMessageDialog(null, "�л��ɹ�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "��û��ѡ���κ�һ�в����л��˺ţ�", "����",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	
	// ɾ����ϵ��
	public void deleteaccount(int selectRow) {
		if (selectRow < accountVectors.size() && selectRow != -1) {// ѡ��һ��ɾ��
			accountVectors.remove(selectRow);
			accountList.updateUI();
		} else {
			JOptionPane.showMessageDialog(null, "��û��ѡ���κ�һ�в���ɾ����", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// ȷ���޸���ϵ�˲�����ϵ�˱���Ϊxml��ʽ���ĵ�
	public void ok() {
		SaveAccount2XML saveaccountsXML = new SaveAccount2XML();
		saveaccountsXML.saveAccountXml("account.xml", accountVectors);
		JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// ����˺�
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

	// ����Ƿ���Ե�½
	private boolean checkLogin(String pop_server,String smtp_server,String username,String password) {
		LoginAction log = new LoginAction(smtp_server,pop_server,username,password);
		boolean isLogin = log.isLogin();
		return isLogin;
	}
}
