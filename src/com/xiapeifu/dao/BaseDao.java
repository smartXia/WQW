package com.xiapeifu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xiapeifu.bean.Mall;

public class BaseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver"; // 定义驱动字符串
	private final static String URL = "jdbc:mysql://xiapeifu.mysql.rds.aliyuncs.com:3306/robot?useUnicode=true&characterEncoding=utf-8";// 定义url路径
	private final static String USERNAME = "root"; // 数据库名称,根据自己情况修改
	private final static String PASSWORD = "xia@qq123"; // 数据库密码,根据自己情况修改
	public Connection conn = null; // 定义连接数据库对象
	static {
		try {
			Class.forName(DRIVER);// 注册驱动
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	// 创建数据库连接对象
	public Connection getConn() {
		try {
			if (conn == null) { // 如果数据库连接对象为null，创建Connection对象
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} // 创建数据库连接对象

		return conn;
	}

	// 添加学生到数据库
	public int add(Mall mall) {
		int i = 0;
		conn = getConn(); // 获取数据库连接对象

		String sql = "insert into mall(id,name,discount,price,location,number) values(?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // 创建sql语句预处理对象
			ps.setString(1, mall.getId());
			ps.setString(2, mall.getName());
			ps.setString(3, mall.getDiscount());
			ps.setString(4, mall.getPrice());
			ps.setString(5, mall.getLocaltion());		
			ps.setString(6, mall.getnumber());
			System.out.println(mall.toString());
			i = ps.executeUpdate(); // 在此 PreparedStatement 对象中执行 SQL 语句
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // 关闭书库资源对象
		}

		return i;
	}

	// 修改学生信息
	public int update(Mall mall) {
		int i = 0;
		conn = getConn(); // 获取数据库连接对象

		String sql = "update mall set name=?,discount=?,price=?,location=?,number=?  where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // 创建sql语句预处理对象
			//ps.setString(1, mall.getId());
			ps.setString(1, mall.getName());
			ps.setString(2, mall.getDiscount());
			ps.setString(3, mall.getLocaltion());
			ps.setString(4, mall.getPrice());
			ps.setString(5, mall.getnumber());
			ps.setString(6, mall.getId());
			i = ps.executeUpdate(); // 在此 PreparedStatement 对象中执行 SQL 语句
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // 关闭书库资源对象
		}

		return i;
	}

	// 删除学生信息
	public int del(String id) {
		int i = 0;
		conn = getConn(); // 获取数据库连接对象

		String sql = "delete from  mall where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // 创建sql语句预处理对象
			ps.setString(1, id);
			i = ps.executeUpdate(); // 在此 PreparedStatement 对象中执行 SQL 语句
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // 关闭书库资源对象
		}

		return i;
	}

	// 查询学生信息
	public Mall getMall(String id) {
		Mall mall = null;
		conn = getConn(); // 获取数据库连接对象

		String sql = "select name,discount,location,price,number from mall where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // 创建sql语句预处理对象
			ps.setString(1, id);// 中的1是指这个语句的第一个问号占位符
			ResultSet rs = ps.executeQuery(); // 在此 PreparedStatement 对象中执行 SQL
			// 语句,获取结果集对象
			
			if (rs.next()) {
				mall = new Mall();
				mall.setName(rs.getString(1));
				mall.setDiscount(rs.getString(2));
				mall.setLocaltion(rs.getString(3));
				mall.setPrice(rs.getString(4));
				mall.setnumber(rs.getString(5));
				System.out.println(mall.toString());

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // 关闭书库资源对象
		}

		return mall;
	}

	// 关闭数据库操作
	public void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
