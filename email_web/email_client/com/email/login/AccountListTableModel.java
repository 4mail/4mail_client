package com.email.login;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * ��ȡ�˻��б�
 * @author zhujuan
 *
 */
public class AccountListTableModel extends AbstractTableModel  {
	private static final long serialVersionUID = 1L;
	private final String[] COLUMNS = new String[] { "pop������", "smtp������", "�û���","����" };
	private static Vector<Vector<String>> v = new Vector<Vector<String>>();

	public AccountListTableModel() {
	}

	@Override
	public int getColumnCount() {// �õ�������
		return COLUMNS.length;
	}

	@Override
	public int getRowCount() {// �õ�����
		return v.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {// �õ�ĳ���е�ֵ
		return ((Vector<String>) (v.get(rowIndex))).get(columnIndex);
	}

	public String getColumnName(int column) {// �õ�����
		return COLUMNS[column];
	}

	// �õ�Vector<Vector<String>> ����
	public static Vector<Vector<String>> getVector() {
		return v;
	}

}
