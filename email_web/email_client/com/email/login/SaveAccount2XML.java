package com.email.login;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class SaveAccount2XML {
	private Document document = null;// xml�ĵ�����
	private Element accountsElement = null;// ������Ԫ��

	// ������ϵ��Ϊxml�ĵ�
	public boolean saveAccountXml(String fileName,
			Vector<Vector<String>> accountVector) {
		boolean isSave = false;
		if (accountVector.size() > 0) {
			initXml();// ��ʼ��xml�ĵ�
			Iterator<Vector<String>> iterator = accountVector.iterator();
			while (iterator.hasNext()) {
				Vector<String> vector = iterator.next();
				String pop_server = vector.get(0);
				String smtp_server = vector.get(1);
				String username = vector.get(2);
				String password = vector.get(3);
				saveAccountInfor(pop_server, smtp_server, username, password);
			}
			saveXMLFile(fileName);// ������ϵ��xml�ļ�
			isSave = true;// ����ɹ�
		}
		return isSave;

	}

	// ��ʼ��xml�ĵ�
	private void initXml() {
		// ʹ�� DocumentHelper �ഴ��һ���ĵ�ʵ���� DocumentHelper ������ XML �ĵ��ڵ�� dom4j API
		// �����ࡣ
		document = DocumentHelper.createDocument();
		// ʹ�� addElement() ����������Ԫ�� catalog ��addElement() ������ XML �ĵ�������Ԫ�ء�
		accountsElement = document.addElement("accounts");
		// �� accounts Ԫ����ʹ�� addComment() �������ע�͡�An XML catalog����
		accountsElement.addComment("�ҵ��˺��б�");
	}

	private void saveAccountInfor(String pop_server, String smtp_server,
			String username, String password) {
		// �� accounts Ԫ����ʹ�� addElement() �������� account Ԫ�ء�
		Element accountElement = accountsElement.addElement("account");

		// ��ӽڵ�account���ӽڵ�pop_server��
		Element popElement = accountElement.addElement("pop_server");
		popElement.setText(pop_server);
		
		// ��ӽڵ�account���ӽڵ�smtp_server��
		Element smtpElement = accountElement.addElement("smtp_server");
		smtpElement.setText(smtp_server);

		// ��ӽڵ�account���ӽڵ�username
		Element usernameElement = accountElement.addElement("username");
		usernameElement.setText(username);

		// ��ӽڵ�account���ӽڵ�password
		Element passwordElement = accountElement.addElement("password");
		passwordElement.setText(password);
	}

	// ����ͨѶ¼xml�ļ�
	private void saveXMLFile(String fileName) {
		XMLWriter output;
		try {
			OutputFormat format = new OutputFormat();
			format.setEncoding("gbk");
			String path = System.getProperty("user.dir");
			fileName = path + "/" + fileName;
			output = new XMLWriter(new FileWriter(new File(fileName)), format);
			output.write(document);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
