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
	private Document document = null;// xml文档对象
	private Element accountsElement = null;// 创建根元素

	// 保存联系人为xml文档
	public boolean saveAccountXml(String fileName,
			Vector<Vector<String>> accountVector) {
		boolean isSave = false;
		if (accountVector.size() > 0) {
			initXml();// 初始化xml文档
			Iterator<Vector<String>> iterator = accountVector.iterator();
			while (iterator.hasNext()) {
				Vector<String> vector = iterator.next();
				String pop_server = vector.get(0);
				String smtp_server = vector.get(1);
				String username = vector.get(2);
				String password = vector.get(3);
				saveAccountInfor(pop_server, smtp_server, username, password);
			}
			saveXMLFile(fileName);// 保存联系人xml文件
			isSave = true;// 保存成功
		}
		return isSave;

	}

	// 初始化xml文档
	private void initXml() {
		// 使用 DocumentHelper 类创建一个文档实例。 DocumentHelper 是生成 XML 文档节点的 dom4j API
		// 工厂类。
		document = DocumentHelper.createDocument();
		// 使用 addElement() 方法创建根元素 catalog 。addElement() 用于向 XML 文档中增加元素。
		accountsElement = document.addElement("accounts");
		// 在 accounts 元素中使用 addComment() 方法添加注释“An XML catalog”。
		accountsElement.addComment("我的账号列表！");
	}

	private void saveAccountInfor(String pop_server, String smtp_server,
			String username, String password) {
		// 在 accounts 元素中使用 addElement() 方法增加 account 元素。
		Element accountElement = accountsElement.addElement("account");

		// 添加节点account的子节点pop_server；
		Element popElement = accountElement.addElement("pop_server");
		popElement.setText(pop_server);
		
		// 添加节点account的子节点smtp_server；
		Element smtpElement = accountElement.addElement("smtp_server");
		smtpElement.setText(smtp_server);

		// 添加节点account的子节点username
		Element usernameElement = accountElement.addElement("username");
		usernameElement.setText(username);

		// 添加节点account的子节点password
		Element passwordElement = accountElement.addElement("password");
		passwordElement.setText(password);
	}

	// 保存通讯录xml文件
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
