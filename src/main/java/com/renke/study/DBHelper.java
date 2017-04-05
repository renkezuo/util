package com.renke.study;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBHelper {
	private ConnectionDB cdb = ConnectionDB.getConnectionDB();
	/**
	 * Access查询列表，字段大写
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> queryForList(String sql,String dbName) throws SQLException{
		Connection con = cdb.getConnection(dbName);
		ResultSet rs = null;
		Statement stmt = null;
		List list = new ArrayList();
		Map<String,String> map = new HashMap();
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				map = new HashMap();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for(int i=1;i<=count;i++){
					String key = rsmd.getColumnLabel(i);
					Object obj = rs.getObject(key);
					String value = obj==null?"":obj.toString().trim();
					map.put(key.toUpperCase(), value);
					
				}
				list.add(map);
			}
			System.out.println(sql+";");
			con.commit();
			return list;
		}catch(Exception e){
			if(con!=null){
				con.rollback();
			}
			System.out.println("查询数据库失败！错误sql："+sql);
			return new ArrayList();
		}finally{
			closeAll(con,rs,stmt);
		}
	}
	
	/**
	 * 返回第一行纪录的第一个整数值
	 * @param sql
	 * @param dbName
	 * @return
	 * @throws SQLException
	 */
	public int queryForInt(String sql,String dbName) throws SQLException{
		Connection con = cdb.getConnection(dbName);
		ResultSet rs = null;
		Statement stmt = null;
		int result = 0;
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for(int i=1;i<=count;i++){
					String key = rsmd.getColumnLabel(i);
					Object obj = rs.getObject(key);
					String value = obj==null?"":obj.toString().trim();
					try{
						result = Integer.parseInt(value);
						break;
					}catch(NumberFormatException e){
						continue;
					}
				}
			}
			System.out.println(sql);
			con.commit();
			return result;
		}catch(Exception e){
			if(con!=null){
				con.rollback();
			}
			System.out.println("查询数据库失败！错误sql："+sql);
			return -1;
		}finally{
			closeAll(con,rs,stmt);
		}
	}
	
	public void exeucteUpdate(String sql,String dbName) throws SQLException{
		Connection con = cdb.getConnection(dbName);
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println(sql);
			con.commit();
		}catch(Exception e){
			if(con!=null){
				con.rollback();
			}
			System.out.println("查询数据库失败！错误sql："+sql);
		}finally{
			closeAll(con,rs,stmt);
		}
	}
	
	public void updateBatch(String[] sqls,String dbName) throws SQLException{
		Connection con = cdb.getConnection(dbName);
		Statement stmt = null;
		try{
			stmt = con.createStatement();
			for(String sql : sqls){
				stmt.addBatch(sql);
				System.out.println(sql);
			}
			System.out.println(stmt.executeBatch());
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
			if(con!=null){
				con.rollback();
			}
		}finally{
			closeAll(con,null,stmt);
		}
	}
	
	public static void main(String[] args) throws SQLException {
//		DBHelper db = new DBHelper();
//		String sql = "select count(1) from teller";
//		int count =db.queryForInt(sql, "DB2"); 
//		List<Map<String, String>> list = null;
//		for(int i=0,j=1000;i<count;i+=1000,j+=1000){
//			sql = "select * from (select teller_id,ctc_tel_no,rpt_ou_ip_id,ep_id_card_no,em_nm,ep_lcs_tp_id"
//					+ ",rownumber() over(order by teller_id desc) as rowid from teller) a "
//					+ " where a.rowid>"+i+" and rowid<="+j;
//			list = db.queryForList(sql,"DB2");
//			System.out.println(sql);
//		}
		DBHelper db = new DBHelper();
		String[] sqls = {"insert into t_zrk_user values('2013073102','你好111','44')","insert into t_zrk_user values('2013073103','你好111','d好f0')"};
		db.updateBatch(sqls, "");
//		List<Map<String,String>> list = db.queryForList("select * from t_ck_plan a where a.plan_no='89600020120001'", "DB2");
//		String modify_t = list.get(0).get("MODIFY_T");
//		System.out.println(format.format(modify_t));
//		System.out.println(modify_t);
//		db.exeucteUpdate(sql, "");
//		for(int i=0;i<=2000;i++){
//			try{
//				db.exeucteUpdate("INSERT INTO T_BATCH_LOG(TASK_ID,TYPE,TYPE_VALUE,BATCH_YEAR,RUN_DATE,STATE,START_TIME)VALUES(8,'00','20120810','2012','2012-08-10',0,'2012-08-10 08:38:29')"
//				, "DB2");
//			}catch(Exception e){
//				continue;
//			}
//			
//		}
		
	}
	/**
	 * 关闭资源
	 * @throws SQLException
	 */
	public static void closeAll(Connection con,ResultSet rs,Statement stmt){
		if(stmt!=null){
			try{
				stmt.close();
			}catch(SQLException e){
				System.out.println("stmt资源已经关闭！");
			}
		}
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				System.out.println("rs资源已经关闭！");
			}
		}
		if(con!=null){
			try{
				con.close();
			}catch(SQLException e){
				System.out.println("con资源已经关闭！");
			}
		}
	}
}
