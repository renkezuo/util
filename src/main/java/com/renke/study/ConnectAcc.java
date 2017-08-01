package com.renke.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectAcc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = new ArrayList();
		Map<String,String> map = new HashMap();
//		try{
//			Class.forName("com.ibm.db2.jcc.DB2Driver");
//		}catch(ClassNotFoundException e){
//			System.out.println("未发现驱动包！");
//		}
//		try{
//			con = DriverManager.getConnection("jdbc:db2://158.222.31.243:50000/acms","acms","acms1234");
//			con.setAutoCommit(false);
//			stmt = con.createStatement();
//			rs = stmt.executeQuery("select * from t_sys_user_Info where usercode='9990005'");
//			while(rs.next()){
//				map = new HashMap();
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int count = rsmd.getColumnCount();
//				for(int i=1;i<=count;i++){
//					String key = rsmd.getColumnLabel(i);
//					Object obj = rs.getObject(key);
//					String value = obj==null?"":obj.toString();
//					map.put(key.toUpperCase(), value);
//				}
//				list.add(map);
//			}
//			for(int i=0;i<list.size();i++){
//				Map map2 = (Map)list.get(i);
//			}
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}finally{
//			try {
//				rs.close();
//				stmt.close();
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
		
		
		String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E://测试//DataBase//db.mdb";
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// 加载JDBC-ODBC驱动
		}catch (ClassNotFoundException e) {
			System.out.println("Error connected!");
		}
		try{
			con = DriverManager.getConnection(url, "db", "acms_123");
			con.setAutoCommit(false);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from t_sys_user_info");
			if(rs.next()){
				Object obj = rs.getObject(1);
				String value = obj==null?"":obj.toString();
				System.out.println(value);
//				map = new HashMap();
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int count = rsmd.getColumnCount();
//				for(int i=1;i<=count;i++){
//					String key = rsmd.getColumnLabel(i);
//					Object obj = rs.getObject(key);
//					String value = obj==null?"":obj.toString();
//					map.put(key.toUpperCase(), value);
//				}
				list.add(map);
			}
//			for(int i=0;i<list.size();i++){
//				Map map2 = (Map)list.get(i);
//				System.out.println("ID："+map2.get("ID"));
//			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
