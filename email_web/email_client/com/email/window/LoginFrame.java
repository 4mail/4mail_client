package com.email.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.email.login.AccountEventAction;
import com.email.login.AccountListTableModel;
import com.email.login.LoginAction;
import com.email.login.SaveAccount2XML;
import com.email.manage.CheckNewMail;



/**
 * ��¼ҳ��
 */
public class LoginFrame extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JComboBox pop3CB;// ���ʼ������������б�
	@SuppressWarnings("rawtypes")
	private JComboBox smtpCB;// ���ʼ������������б�
	private JTextField nameTF;
	private JPasswordField passwordTF;
	private JButton loginButton = null, resetButton = null;
	private String username = null, password = null, popHost = null,
			smtpHost = null;// SMTP������
	private JProgressBarFrame progressBar = null;// ������ʵ��

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginFrame() {
		super();
		this.setIconImage(EditorUtils.createIcon("icon.png").getImage());
		getContentPane().setLayout(null);
		jFrameValidate();
		setTitle("��¼����");
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 768, 540);
		backgroundLabel.setText("<html><img width=776 height=574 src='"
				+ this.getClass().getResource("/res/loginBg1.jpg") + "'></html>");
		backgroundLabel.setLayout(null);

		final JLabel smtpLable = new JLabel();
		smtpLable.setText("SMTP ��������");
		smtpLable.setBounds(230, 203, 100, 18);
		backgroundLabel.add(smtpLable);

		final JLabel pop3Label = new JLabel();
		pop3Label.setText("POP3 ��������");
		pop3Label.setBounds(230, 243, 100, 18);
		backgroundLabel.add(pop3Label);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("�������ƣ�");
		nameLabel.setBounds(230, 283, 100, 18);
		backgroundLabel.add(nameLabel);

		final JLabel passwordLable = new JLabel();
		passwordLable.setText("���룺");
		passwordLable.setBounds(230, 323, 100, 18);
		backgroundLabel.add(passwordLable);

		// �������������ַ�б�
		String[] smtpAdd = { "smtp.pku.edu.cn","smtp.163.com", "smtp.126.com", "smtp.yeah.net",
				"smtp.qq.com", "smtp.sina.com", "smtp.sohu.com",
				"smtp.139.com", "smtp.mail.yahoo.cn", "smtp.sogou.com",
				"smtp.tom.com", "smtp.189.cn", "smtp.live.com",
				"smtp.gmail.com", "smtp.21cn.com", "smtp.188.com" };
		smtpCB = new JComboBox(smtpAdd);
		smtpCB.setSelectedIndex(0);
		smtpCB.setEditable(true);
		smtpCB.addItemListener(this);
		smtpCB.setBounds(370, 203, 150, 22);
		backgroundLabel.add(smtpCB);

		// �ռ����������ַ�б�
		String[] pop3Add = { "pop3.pku.edu.cn","pop.163.com", "pop.126.com", "pop.yeah.net",
				"pop.qq.com", "pop.sina.com", "pop3.sohu.com", "pop.139.com",
				"pop.mail.yahoo.cn", "pop3.sogou.com", "pop.tom.com",
				"pop.189.cn", "pop3.live.com", "pop.gmail.com", "pop.21cn.com",
				"pop.188.com" };
		pop3CB = new JComboBox(pop3Add);
		pop3CB.setSelectedIndex(0);
		pop3CB.addItemListener(this);
		pop3CB.setEditable(true);
		pop3CB.setBounds(370, 243, 150, 22);
		backgroundLabel.add(pop3CB);

		nameTF = new JTextField();
		nameTF.setBounds(370, 283, 150, 22);
		backgroundLabel.add(nameTF);

		passwordTF = new JPasswordField();
		passwordTF.setBounds(370, 323, 150, 22);
		backgroundLabel.add(passwordTF);

		loginButton = new JButton("��¼");
		resetButton = new JButton("����");
		backgroundLabel.add(loginButton);
		backgroundLabel.add(resetButton);
		loginButton.setBounds(280, 360, 80, 30);
		resetButton.setBounds(400, 360, 80, 30);
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		getContentPane().add(backgroundLabel);

		progressBar = new JProgressBarFrame(this, "��¼", "��¼�У����Ժ�...");
		reset();// Ĭ�ϳ�ʼֵ
	}

	public void jFrameValidate() {
		Toolkit tk = getToolkit();// �����Ļ�Ŀ�͸�
		Dimension dim = tk.getScreenSize();
		this.setResizable(false);
		this.setBounds(dim.width / 2 - 380, dim.height / 2 - 270, 776, 574);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ��¼ �������¼��Ĵ���
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {// ��¼
			progressBar.setVisible(true);// ���ý������ɼ�
			new Thread() {
				public void run() {
					getValues();// �õ������е��������ֵ
					checkUser();// ��¼��֤
				}
			}.start();
		} else if (e.getSource() == resetButton) {// ����
			reset();// �������ø����ֵ
		}
	}

	// �õ������е��������ֵ
	@SuppressWarnings("deprecation")
	private void getValues() {
		smtpHost = (String) smtpCB.getSelectedItem();
		popHost = (String) pop3CB.getSelectedItem();
		username = nameTF.getText().trim();
		password = passwordTF.getText().trim();
	}

	// �������ø����ֵ
	private void reset() {
		smtpCB.setSelectedIndex(0);
		pop3CB.setSelectedIndex(0);
		nameTF.setText("username@pku.edu.cn");
		passwordTF.setText("password");
	}

	// ��¼��֤
	private void checkUser() {
		LoginAction login = new LoginAction(smtpHost, popHost, username,
				password);
		if (login.isLogin()) {// ��¼�ɹ�
			progressBar.dispose();
			new CheckNewMail().start();// ��ʼ������ʼ�
			this.dispose();// �ͷű�������Դ
			JTable accountList = new JTable();// �˻��б�
		    accountList.setModel(new AccountListTableModel());
		    AccountEventAction accountEvent = new AccountEventAction();
		    accountEvent.addaccount(popHost, smtpHost, username, password);
//			SaveAccount2XML saveAccount = new SaveAccount2XML();
//			Vector<String> myVector = new Vector<String>();
//			myVector.addElement(popHost);
//			myVector.addElement(smtpHost);
//			myVector.addElement(username);
//			myVector.addElement(password);
//			Vector<Vector<String>> myVector2 = new Vector<Vector<String>>();
//			myVector2.add(myVector);
//			saveAccount.saveAccountXml("account.xml", myVector2);
			
			new MainFrame().setVisible(true);
		} else {// ��¼ʧ��
			progressBar.setVisible(false);
			JOptionPane.showMessageDialog(this, "<html><h4>"
					+ "��¼ʧ�ܣ������������û����������Ƿ���ȷ��" + "<html><h4>", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// �����б�ı�ʱ���¼�����
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == smtpCB) {
			if (e.getStateChange() == ItemEvent.SELECTED
					&& smtpCB.getSelectedIndex() != -1)
				pop3CB.setSelectedIndex(smtpCB.getSelectedIndex());
		} else if (e.getSource() == pop3CB) {
			if (e.getStateChange() == ItemEvent.SELECTED
					&& pop3CB.getSelectedIndex() != -1)
				smtpCB.setSelectedIndex(pop3CB.getSelectedIndex());
		}

	}
}
