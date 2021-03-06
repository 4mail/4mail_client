package com.email.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.email.login.LoginAction;
import com.email.manage.CheckNewMail;



/**
 * 登录页面
 */
public class AddCountFrame 
  extends JInternalFrame implements ActionListener,
MouseListener, MouseMotionListener, FocusListener{

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	private static final long serialVersionUID = 1L;
//	@SuppressWarnings("rawtypes")
//	private JComboBox pop3CB;// 收邮件服务器下拉列表
//	@SuppressWarnings("rawtypes")
//	private JComboBox smtpCB;// 发邮件服务器下拉列表
//	private JTextField nameTF;
//	private JPasswordField passwordTF;
//	private JButton loginButton = null, resetButton = null;
//	private String username = null, password = null, popHost = null,
//			smtpHost = null;// SMTP服务器
//	private JProgressBarFrame progressBar = null;// 进度条实例
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public AddCountFrame() {
//		super();
//		this.setFrameIcon(EditorUtils.createIcon("image.png"));
//		getContentPane().setLayout(null);
//		jFrameValidate();
//		setTitle("登录邮箱");
//		JLabel backgroundLabel = new JLabel();
//		backgroundLabel.setBounds(0, 0, 768, 540);
//		backgroundLabel.setText("<html><img width=776 height=574 src='"
//				+ this.getClass().getResource("/res/loginBg1.jpg") + "'></html>");
//		backgroundLabel.setLayout(null);
//
//		final JLabel smtpLable = new JLabel();
//		smtpLable.setText("SMTP 服务器：");
//		smtpLable.setBounds(230, 203, 100, 18);
//		backgroundLabel.add(smtpLable);
//
//		final JLabel pop3Label = new JLabel();
//		pop3Label.setText("POP3 服务器：");
//		pop3Label.setBounds(230, 243, 100, 18);
//		backgroundLabel.add(pop3Label);
//
//		final JLabel nameLabel = new JLabel();
//		nameLabel.setText("邮箱名称：");
//		nameLabel.setBounds(230, 283, 100, 18);
//		backgroundLabel.add(nameLabel);
//
//		final JLabel passwordLable = new JLabel();
//		passwordLable.setText("密码：");
//		passwordLable.setBounds(230, 323, 100, 18);
//		backgroundLabel.add(passwordLable);
//
//		// 发件箱服务器地址列表
//		String[] smtpAdd = { "smtp.pku.edu.cn","smtp.163.com", "smtp.126.com", "smtp.yeah.net",
//				"smtp.qq.com", "smtp.sina.com", "smtp.sohu.com",
//				"smtp.139.com", "smtp.mail.yahoo.cn", "smtp.sogou.com",
//				"smtp.tom.com", "smtp.189.cn", "smtp.live.com",
//				"smtp.gmail.com", "smtp.21cn.com", "smtp.188.com" };
//		smtpCB = new JComboBox(smtpAdd);
//		smtpCB.setSelectedIndex(0);
//		smtpCB.setEditable(true);
//		smtpCB.addItemListener(this);
//		smtpCB.setBounds(370, 203, 150, 22);
//		backgroundLabel.add(smtpCB);
//
//		// 收件箱服务器地址列表
//		String[] pop3Add = { "pop3.pku.edu.cn","pop.163.com", "pop.126.com", "pop.yeah.net",
//				"pop.qq.com", "pop.sina.com", "pop3.sohu.com", "pop.139.com",
//				"pop.mail.yahoo.cn", "pop3.sogou.com", "pop.tom.com",
//				"pop.189.cn", "pop3.live.com", "pop.gmail.com", "pop.21cn.com",
//				"pop.188.com" };
//		pop3CB = new JComboBox(pop3Add);
//		pop3CB.setSelectedIndex(0);
//		pop3CB.addItemListener(this);
//		pop3CB.setEditable(true);
//		pop3CB.setBounds(370, 243, 150, 22);
//		backgroundLabel.add(pop3CB);
//
//		nameTF = new JTextField();
//		nameTF.setBounds(370, 283, 150, 22);
//		backgroundLabel.add(nameTF);
//
//		passwordTF = new JPasswordField();
//		passwordTF.setBounds(370, 323, 150, 22);
//		backgroundLabel.add(passwordTF);
//
//		loginButton = new JButton("登录");
//		resetButton = new JButton("重置");
//		backgroundLabel.add(loginButton);
//		backgroundLabel.add(resetButton);
//		loginButton.setBounds(280, 360, 80, 30);
//		resetButton.setBounds(400, 360, 80, 30);
//		loginButton.addActionListener(this);
//		resetButton.addActionListener(this);
//		getContentPane().add(backgroundLabel);
//
//		progressBar = new JProgressBarFrame(this, "登录", "登录中，请稍后...");
//		reset();// 默认初始值
//	}
//
//	public void jFrameValidate() {
//		Toolkit tk = getToolkit();// 获得屏幕的宽和高
//		Dimension dim = tk.getScreenSize();
//		this.setResizable(false);
//		this.setBounds(dim.width / 2 - 380, dim.height / 2 - 270, 776, 574);
//		validate();
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	// 登录 和重置事件的处理
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == loginButton) {// 登录
//			progressBar.setVisible(true);// 设置进度条可见
//			new Thread() {
//				public void run() {
//					getValues();// 得到界面中的所有项的值
//					checkUser();// 登录验证
//				}
//			}.start();
//		} else if (e.getSource() == resetButton) {// 重置
//			reset();// 重新设置各项的值
//		}
//	}
//
//	// 得到界面中的所有项的值
//	@SuppressWarnings("deprecation")
//	private void getValues() {
//		smtpHost = (String) smtpCB.getSelectedItem();
//		popHost = (String) pop3CB.getSelectedItem();
//		username = nameTF.getText().trim();
//		password = passwordTF.getText().trim();
//	}
//
//	// 重新设置各项的值
//	private void reset() {
//		smtpCB.setSelectedIndex(0);
//		pop3CB.setSelectedIndex(0);
//		nameTF.setText("username@pku.edu.cn");
//		passwordTF.setText("password");
//	}
//
//	// 登录验证
//	private void checkUser() {
//		LoginAction login = new LoginAction(smtpHost, popHost, username,
//				password);
//		if (login.isLogin()) {// 登录成功
//			progressBar.dispose();
//			new CheckNewMail().start();// 开始检测新邮件
//			this.dispose();// 释放本窗口资源
//			new MainFrame().setVisible(true);
//		} else {// 登录失败
//			progressBar.setVisible(false);
//			JOptionPane.showMessageDialog(this, "<html><h4>"
//					+ "登录失败，请检查主机、用户名、密码是否正确！" + "<html><h4>", "警告",
//					JOptionPane.WARNING_MESSAGE);
//		}
//	}
//
//	// 下拉列表改变时的事件处理
//	public void itemStateChanged(ItemEvent e) {
//		if (e.getSource() == smtpCB) {
//			if (e.getStateChange() == ItemEvent.SELECTED
//					&& smtpCB.getSelectedIndex() != -1)
//				pop3CB.setSelectedIndex(smtpCB.getSelectedIndex());
//		} else if (e.getSource() == pop3CB) {
//			if (e.getStateChange() == ItemEvent.SELECTED
//					&& pop3CB.getSelectedIndex() != -1)
//				smtpCB.setSelectedIndex(pop3CB.getSelectedIndex());
//		}
//
//	}
}
