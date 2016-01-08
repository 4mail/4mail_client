package com.email.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.email.login.AccountEventAction;
import com.email.login.AccountListTableModel;

@SuppressWarnings("serial")
public class AddAccountFrame extends JInternalFrame implements ActionListener, DocumentListener, ItemListener{
	private JTextField popTF = null, smtpTF = null, nameTF = null;
	private JPasswordField passwordTF = null;
	private JLabel popLabel = null, smtpLabel = null, nameLabel = null, passwordLabel = null;
	private JButton add = null, delete = null, shift = null, ok = null, cancel = null;
	@SuppressWarnings("rawtypes")
	private JComboBox pop3CB;// 收邮件服务器下拉列表
	@SuppressWarnings("rawtypes")
	private JComboBox smtpCB;// 发邮件服务器下拉列表
	private JScrollPane AccountJSP;
	private JTable accountList = null;// 账户列表
	private JPanel editorJPanel = null;// 编辑面板
	private JPanel AccountListJPanel = null;// 账户列表面板
	private Box baseBox = null, boxV1 = null, boxV2 = null;
	private AccountEventAction accountEvent = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddAccountFrame() {
		super("添加账户");
		this.setFrameIcon(EditorUtils.createIcon("image.png"));
		setClosable(true);
		setIconifiable(true);
		setBounds(20, 10, 500, 385);
		getContentPane().setLayout(new BorderLayout());
		
		//pop
		popLabel = new JLabel();
		popLabel.setText("pop3：");
		getContentPane().add(popLabel);
		popTF = new JTextField(50);
		getContentPane().add(popTF);
		
		//smtp
		smtpLabel = new JLabel();
		smtpLabel.setText("smtp：");
		getContentPane().add(smtpLabel);
		smtpTF = new JTextField(50);
		getContentPane().add(smtpTF);
		
		// 账户名
		nameLabel = new JLabel();
		nameLabel.setText("账户名：");
		getContentPane().add(nameLabel);
		nameTF = new JTextField(50);
		getContentPane().add(nameTF);

		// 密码
		passwordLabel = new JLabel();
		passwordLabel.setText("密码：");
		getContentPane().add(passwordLabel);
		passwordTF = new JPasswordField(50);
		passwordTF.setEchoChar('*');
		getContentPane().add(passwordTF);
		

	    accountList = new JTable();// 账户列表
		accountList.setModel(new AccountListTableModel());
		accountList.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        accountList.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        AccountJSP = new JScrollPane(accountList);
		AccountJSP.setPreferredSize(new Dimension(400, 150));

		add = new JButton("添加");
		add.addActionListener(this);
		add.setEnabled(true);
		delete = new JButton("删除");
		delete.addActionListener(this);
		shift = new JButton("切换");
		shift.addActionListener(this);
		ok = new JButton("确定");
		ok.addActionListener(this);
		cancel = new JButton("取消");
		cancel.addActionListener(this);
		// 添加账户面板
		editorJPanel = new JPanel();
		editorJPanel.setBorder(BorderFactory.createTitledBorder("添加账户："));
		editorJPanel.add(box());
		this.add(editorJPanel, BorderLayout.NORTH);
		
		// 发件箱服务器地址列表
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

		// 收件箱服务器地址列表
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

		// 添加删除账户面板
		JPanel addAadDeletePanel = new JPanel();
		addAadDeletePanel.setPreferredSize(new Dimension(60, 150));
		addAadDeletePanel.add(add);
		addAadDeletePanel.add(new JLabel("   "));
		addAadDeletePanel.add(shift);
		addAadDeletePanel.add(new JLabel("   "));
		addAadDeletePanel.add(delete);
		// 账户列表面板
		JPanel accountPanel = new JPanel();
		accountPanel.add(AccountJSP);

		AccountListJPanel = new JPanel(new BorderLayout());
		AccountListJPanel.setBorder(BorderFactory.createTitledBorder("账户列表："));
		AccountListJPanel.add(accountPanel, BorderLayout.CENTER);
	    AccountListJPanel.add(addAadDeletePanel, BorderLayout.EAST);
		this.add(AccountListJPanel, BorderLayout.CENTER);

		// 确定取消面板
		JPanel okAndCancelPanel = new JPanel();
		okAndCancelPanel.setPreferredSize(new Dimension(500, 35));
		okAndCancelPanel.add(ok);
		okAndCancelPanel.add(new JLabel("   "));
		okAndCancelPanel.add(cancel);
		this.add(okAndCancelPanel, BorderLayout.SOUTH);
		setVisible(true);
		accountEvent = new AccountEventAction(popTF, smtpTF, nameTF, passwordTF, accountList);
	}

	private Box box() {
		// 创建标签box
		boxV1 = Box.createVerticalBox();
		boxV1.add(popLabel);
		boxV1.add(Box.createVerticalStrut(10));
		boxV1.add(smtpLabel);
		boxV1.add(Box.createVerticalStrut(10));
		boxV1.add(nameLabel);
		boxV1.add(Box.createVerticalStrut(10));
		boxV1.add(passwordLabel);
		boxV1.add(Box.createVerticalStrut(10));

		// 创建文本框box
		boxV2 = Box.createVerticalBox();
		boxV2.add(popTF);	
		boxV2.add(Box.createVerticalStrut(5));
		boxV2.add(smtpTF);	
		boxV2.add(Box.createVerticalStrut(5));
		boxV2.add(nameTF);	
		boxV2.add(Box.createVerticalStrut(5));
		boxV2.add(passwordTF);	
		boxV2.add(Box.createVerticalStrut(5));

		// 创建基本box
		baseBox = Box.createHorizontalBox();
		baseBox.add(boxV1);
		baseBox.add(Box.createHorizontalStrut(30));
		baseBox.add(boxV2);
		boxV2.add(Box.createVerticalStrut(5));

		return baseBox;
	}

	public void changedUpdate(DocumentEvent e) {
		checkInput();// 文本更新时调用
	}

	public void insertUpdate(DocumentEvent e) {
		checkInput();// 插入更新
	}

	public void removeUpdate(DocumentEvent e) {
		checkInput();// 移除更新
	}

	// 检测输入数据的有效性
	private void checkInput() {
		System.out.println(nameTF.getText().trim());
		boolean checkEmail = EditorUtils.checkEmailAdress(nameTF.getText().trim());
		if (checkEmail) {
			add.setEnabled(true);
		} else {
			add.setEnabled(false);
		}
	}

	// 事件的处理
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			accountEvent.addaccount();;// 添加账户
		} else if (e.getSource() == delete) {
			accountEvent.deleteaccount(accountList.getSelectedRow());// 删除联系人
		} else if (e.getSource() == shift) {
			accountEvent.changeAccount(accountList.getSelectedRow());// 切换账户
		} else if (e.getSource() == ok) {
			accountEvent.ok();
			this.dispose();
		} else if (e.getSource() == cancel) {
			this.dispose();// 关闭窗口
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
