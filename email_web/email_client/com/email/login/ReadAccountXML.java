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
 * ��ȡ�˻��б�
 * @author zhujuan
 *
 */
public class ReadAccountXML {
	private static Vector<Vector<String>> accounts = AccountListTableModel
			.getVector();

	// ��ȡ��ϵ����Ϣ
	public void readXMl(String fileName, Vector<Vector<String>> accountVector) {
		try {
			String path = System.getProperty("user.dir");
			fileName = path + "/" + fileName;
			File f = new File(fileName);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);// ��ȡ��ϵ���б�
			Element root = doc.getRootElement();
			Element foo = null;
			for (@SuppressWarnings("rawtypes")
			Iterator i = root.elementIterator("account"); i.hasNext();) {// ����ÿһ�����
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
	static DefaultListModel dl = new DefaultListModel();// ��ϵ���б�ģ��

	// ��ϵ���б� ������ҳ����ʾ
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JList makeList() {
		JList accountList = null;

		try {
			accounts.removeAllElements();
			readXMl("account.xml", accounts);
			int accountsCount = accounts.size();
			if (accountsCount != 0) {
				for (int i = 0; i < accountsCount; i++) {
					String name = accounts.get(i).get(2);// �õ���i���˻����û���
					if (name != null && !"".equals(name)) {
						dl.addElement(name);// ���û�����ӵ��б���ģ����
					}
				}
				accountList = new JList(dl);
			} else {
				dl.addElement("��ʱû���˻�");
				accountList = new JList(dl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountList;// ������ϵ���б�
	}

	// �����û���������ϵ��
	public String findaccount(int index) {
		String account = "";
		String name = (String) dl.get(index);// �õ���ϵ��ģ���е���ϵ������
		int accountsCount = accounts.size();
		for (int i = 0; i < accountsCount; i++) {
			String username = accounts.get(i).get(2);// �õ���i����ϵ�˵�����
			if (username.equals(name)) {
				String password = accounts.get(i).get(3);// �õ���ϵ�˵�email
				account = name + " <" + password + ">;";
				break;
			}
		}
		return account;
	}
	
	// �����û���������ϵ��
		public static boolean findaccount(String myname) {
			boolean has = false;
			int accountsCount = accounts.size();
			for (int i = 0; i < accountsCount; i++) {
				String username = accounts.get(i).get(2);// �õ���i����ϵ�˵�����
				if (username.equals(myname)) {
					has = true;
					break;
				}
			}
			return has;
		}
		
}
