package com.renke.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionDB {

	private static ConnectionDB cdb;
	
	private String dbUrl;
	
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	/**
	 * 获取数据库路径
	 */
	private ConnectionDB(){
		String classPath = "";
		try {
//			classPath = java.net.URLDecoder.decode(this.getClass().getResource("").toString(),"utf-8");
//			String dbUrl = classPath.toString().replaceAll("file:/", "");
//			dbUrl = dbUrl.substring(0,dbUrl.lastIndexOf("apache-tomcat-6.0.20"));
			dbUrl = "E:/db.mdb";
			setDbUrl(dbUrl);
		} catch (Exception e) {
			System.out.println("获取数据库路径失败");
		}
	}
	
	/**
	 * 单例模式
	 * @return
	 */
	public static ConnectionDB getConnectionDB(){
		if(cdb==null){
			cdb = new ConnectionDB();
		} 
		return cdb;
	}
	
	/**
	 * 获取数据库连接
	 * @param db	有两个数据库，根据数据库名称不同，获取不同链接
	 * access 和 DB2
	 * @return
	 */
	public Connection getConnection(String db){
		Connection con = null;
		if(db==null||db.equals("")) db="access";
		if(db.equals("DB2")){
			try {
				Class.forName("com.ibm.db2.jcc.DB2Driver");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("无法连接DB2数据库(驱动问题)!");
			}
			try {
//				con = DriverManager.getConnection("jdbc:db2://154.233.15.51:50000/acms","acms","acms1234");
				con = DriverManager.getConnection("jdbc:db2://158.222.65.28:50000/acms","acms","acms1234");
				con.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("无法连接DB2数据库(数据库问题)!");
			}
		}else if(db.equals("MYSQL")){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("无法连接MYSQL数据库(驱动问题)!");
			}
			try {
//				con = DriverManager.getConnection("jdbc:db2://154.233.15.51:50000/acms","acms","acms1234");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zrk_test","root","1234");
				con.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("无法连接MYSQL数据库(数据库问题)!");
			}
		}else{
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("无法连接Access数据库(驱动问题)!");
			}
			try {
				String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+dbUrl;
				con = DriverManager.getConnection(url, "db", "acms_123");
				con.setAutoCommit(false);
			} catch (SQLException e) {
				System.out.println("无法连接Access数据库(数据库问题)!");
			}
		}
		return con;
	}
}
