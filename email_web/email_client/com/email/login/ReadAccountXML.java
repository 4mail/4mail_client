package com.email.login;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.email.login.AccountListTableModel;

/**
 * 读取账户列表
 * @author zhujuan
 *
 */
public class ReadAccountXML {
	private static Vector<Vector<String>> accounts = AccountListTableModel
			.getVector();

	// 读取联系人信息
	public void readXMl(String fileName, Vector<Vector<String>> accountVector) {
		try {
			String path = System.getProperty("user.dir");
			fileName = path + "/" + fileName;
			File f = new File(fileName);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);// 读取联系人列表
			Element root = doc.getRootElement();
			Element foo = null;
			for (@SuppressWarnings("rawtypes")
			Iterator i = root.elementIterator("account"); i.hasNext();) {// 遍历每一个结点
				foo = (Element) i.next();
				Vector<String> vector = new Vector<String>();
				vector.add(foo.elementText("pop_server"));
				vector.add(foo.elementText("smtp_server"));
				vector.add(foo.elementText("username"));
				vector.add(foo.elementText("password"));
				accountVector.add(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	static DefaultListModel dl = new DefaultListModel();// 联系人列表模型

	// 联系人列表 用于主页面显示
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JList makeList() {
		JList accountList = null;

		try {
			accounts.removeAllElements();
			readXMl("account.xml", accounts);
			int accountsCount = accounts.size();
			if (accountsCount != 0) {
				for (int i = 0; i < accountsCount; i++) {
					String name = accounts.get(i).get(2);// 得到第i个账户的用户名
					if (name != null && !"".equals(name)) {
						dl.addElement(name);// 将用户名添加到列表名模型中
					}
				}
				accountList = new JList(dl);
			} else {
				dl.addElement("暂时没有账户");
				accountList = new JList(dl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountList;// 返回联系人列表
	}

	// 根据用户名查找联系人
	public String findaccount(int index) {
		String account = "";
		String name = (String) dl.get(index);// 得到联系人模型中的联系人姓名
		int accountsCount = accounts.size();
		for (int i = 0; i < accountsCount; i++) {
			String username = accounts.get(i).get(2);// 得到第i个联系人的姓名
			if (username.equals(name)) {
				String password = accounts.get(i).get(3);// 得到联系人的email
				account = name + " <" + password + ">;";
				break;
			}
		}
		return account;
	}
	
	// 根据用户名查找联系人
		public static boolean findaccount(String myname) {
			boolean has = false;
			int accountsCount = accounts.size();
			for (int i = 0; i < accountsCount; i++) {
				String username = accounts.get(i).get(2);// 得到第i个联系人的姓名
				if (username.equals(myname)) {
					has = true;
					break;
				}
			}
			return has;
		}
		
}
