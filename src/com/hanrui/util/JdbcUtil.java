package com.hanrui.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	//���ݿ��û���
	private static final String username = "root";
	//���ݿ�����
	private static final String password = "123456";
	//���ݿ��ַ
	private static final String url = "jdbc:mysql://localhost:3306/book_info?Unicode=true&characterEncoding=utf8";
	//���ݿ�����������
	private static final String className = "com.mysql.jdbc.Driver";
	static {
		try {
			//�������ݿ�����
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * �ͷ���Դ
	 * @param rs	�����
	 * @param stmt	ִ��SQL������
	 * @param conn	�������ݿ����
	 */
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
