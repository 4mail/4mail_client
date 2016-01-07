package com.email.login;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * 获取账户列表
 * @author zhujuan
 *
 */
public class AccountListTableModel extends AbstractTableModel  {
	private static final long serialVersionUID = 1L;
	private final String[] COLUMNS = new String[] { "pop服务器", "smtp服务器", "用户名","密码" };
	private static Vector<Vector<String>> v = new Vector<Vector<String>>();

	public AccountListTableModel() {
	}

	@Override
	public int getColumnCount() {// 得到总行数
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {// 得到列数
		return v.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {// 得到某行列的值
		return ((Vector<String>) (v.get(rowIndex))).get(columnIndex);
	}

	public String getColumnName(int column) {// 得到行名
		return COLUMNS[column];
	}

	// 得到Vector<Vector<String>> 对象
	public static Vector<Vector<String>> getVector() {
		return v;
	}

}
