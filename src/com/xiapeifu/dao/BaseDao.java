package com.xiapeifu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xiapeifu.bean.Mall;

public class BaseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver"; // ���������ַ���
	private final static String URL = "jdbc:mysql://xiapeifu.mysql.rds.aliyuncs.com:3306/robot?useUnicode=true&characterEncoding=utf-8";// ����url·��
	private final static String USERNAME = "root"; // ���ݿ�����,�����Լ�����޸�
	private final static String PASSWORD = "xia@qq123"; // ���ݿ�����,�����Լ�����޸�
	public Connection conn = null; // �����������ݿ����
	static {
		try {
			Class.forName(DRIVER);// ע������
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	// �������ݿ����Ӷ���
	public Connection getConn() {
		try {
			if (conn == null) { // ������ݿ����Ӷ���Ϊnull������Connection����
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} // �������ݿ����Ӷ���

		return conn;
	}

	// ���ѧ�������ݿ�
	public int add(Mall mall) {
		int i = 0;
		conn = getConn(); // ��ȡ���ݿ����Ӷ���

		String sql = "insert into mall(id,name,discount,price,location,number) values(?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // ����sql���Ԥ�������
			ps.setString(1, mall.getId());
			ps.setString(2, mall.getName());
			ps.setString(3, mall.getDiscount());
			ps.setString(4, mall.getPrice());
			ps.setString(5, mall.getLocaltion());		
			ps.setString(6, mall.getnumber());
			System.out.println(mall.toString());
			i = ps.executeUpdate(); // �ڴ� PreparedStatement ������ִ�� SQL ���
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // �ر������Դ����
		}

		return i;
	}

	// �޸�ѧ����Ϣ
	public int update(Mall mall) {
		int i = 0;
		conn = getConn(); // ��ȡ���ݿ����Ӷ���

		String sql = "update mall set name=?,discount=?,price=?,location=?,number=?  where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // ����sql���Ԥ�������
			//ps.setString(1, mall.getId());
			ps.setString(1, mall.getName());
			ps.setString(2, mall.getDiscount());
			ps.setString(3, mall.getLocaltion());
			ps.setString(4, mall.getPrice());
			ps.setString(5, mall.getnumber());
			ps.setString(6, mall.getId());
			i = ps.executeUpdate(); // �ڴ� PreparedStatement ������ִ�� SQL ���
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // �ر������Դ����
		}

		return i;
	}

	// ɾ��ѧ����Ϣ
	public int del(String id) {
		int i = 0;
		conn = getConn(); // ��ȡ���ݿ����Ӷ���

		String sql = "delete from  mall where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // ����sql���Ԥ�������
			ps.setString(1, id);
			i = ps.executeUpdate(); // �ڴ� PreparedStatement ������ִ�� SQL ���
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeConn(conn, ps, null); // �ر������Դ����
		}

		return i;
	}

	// ��ѯѧ����Ϣ
	public Mall getMall(String id) {
		Mall mall = null;
		conn = getConn(); // ��ȡ���ݿ����Ӷ���

		String sql = "select name,discount,location,price,number from mall where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql); // ����sql���Ԥ�������
			ps.setString(1, id);// �е�1��ָ������ĵ�һ���ʺ�ռλ��
			ResultSet rs = ps.executeQuery(); // �ڴ� PreparedStatement ������ִ�� SQL
			// ���,��ȡ���������
			
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
			closeConn(conn, ps, null); // �ر������Դ����
		}

		return mall;
	}

	// �ر����ݿ����
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
