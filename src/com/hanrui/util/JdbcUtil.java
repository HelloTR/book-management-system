package com.hanrui.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	//数据库用户名
	private static final String username = "root";
	//数据库密码
	private static final String password = "123456";
	//数据库地址
	private static final String url = "jdbc:mysql://localhost:3306/book_info?Unicode=true&characterEncoding=utf8";
	//数据库驱动类名称
	private static final String className = "com.mysql.jdbc.Driver";
	static {
		try {
			//加载数据库驱动
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 释放资源
	 * @param rs	结果集
	 * @param stmt	执行SQL语句的类
	 * @param conn	连接数据库的类
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
