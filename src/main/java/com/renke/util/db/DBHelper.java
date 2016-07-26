package com.renke.util.db;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	private String driver,url,username,password;
	private Connection conn = null;
	public DBHelper(String driver,String url,String username, String password){
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public Connection getConn() throws SQLException{
		if(conn == null || conn.isClosed())
			conn = MyConnection.getConn(driver, url, username, password);
		return conn;
	}
	
	public void setParams(PreparedStatement ps,DBConstants[] types,Object[] objs) throws SQLException{
		if(types==null || types.length ==0) return;
		for(int i=0;i<types.length;i++){
			DBConstants type = types[i];
			switch(type){
				case SET_STRING :	
					ps.setString(i+1, (String)objs[i]);
					break;
				case SET_BIGDECIMAL :	
					ps.setBigDecimal(i+1, (BigDecimal)objs[i]);
					break;
				case SET_STREAM :	
					ps.setBlob(i+1, (InputStream)objs[i]);
					break;
				case SET_DATE :	
					ps.setDate(i+1, (Date)objs[i]);
					break;
				case SET_DOUBLE :	
					ps.setDouble(i+1, (Double)objs[i]);
					break;
				case SET_LONG :	
					ps.setLong(i+1, (Long)objs[i]);
					break;
				case SET_OBJECT :
					ps.setObject(i+1, objs[i]);
					break;
				case SET_TIMESTAMP :	
					ps.setTimestamp(i+1, (Timestamp)objs[i]);
					break;
				default :
					ps.setString(i+1, (String)objs[i]);
					break;
			}
			
		}
	}
	
	
	public Map<String,Object> getValues(ResultSet rs) throws SQLException{
		Map<String,Object> result = new HashMap<String,Object>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
//		rs.getMetaData().getColumnType(column)
		for(int columnIndex=1;columnIndex<=count;columnIndex++){
			int type = rsmd.getColumnType(columnIndex);
			String col_name = rsmd.getColumnName(columnIndex).toUpperCase();
			switch(type){
				case Types.BLOB :
					result.put(col_name, rs.getBlob(columnIndex));
					break;
				case Types.BIGINT :
					result.put(col_name, rs.getLong(columnIndex));
					break;
				case Types.CLOB :
					result.put(col_name, rs.getClob(columnIndex));
					break;
				case Types.CHAR :
					result.put(col_name, rs.getString(columnIndex));
					break;
				case Types.LONGVARBINARY :
					result.put(col_name, rs.getBlob(columnIndex));
					break;
				case Types.DATE :
					result.put(col_name, rs.getDate(columnIndex));
					break;
				case Types.DECIMAL :
					result.put(col_name, rs.getBigDecimal(columnIndex));
					break;
				case Types.DOUBLE:
					result.put(col_name, rs.getDouble(columnIndex));
					break;
				case Types.FLOAT:
					result.put(col_name, rs.getFloat(columnIndex));
					break;
				case Types.INTEGER:
					result.put(col_name, rs.getInt(columnIndex));
					break;
				case Types.TIMESTAMP:
					result.put(col_name, rs.getTimestamp(columnIndex));
					break;
				default :
					result.put(col_name, rs.getString(columnIndex));
					break;
			}
		}
		return result;
	}
	
	public int update(String sql,DBConstants[] types,Object[] objs){
		int result = -1;
		PreparedStatement ps = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			setParams(ps,types,objs);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
				if(ps!=null) ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Map<String,Object>> select(String sql,DBConstants[] types,Object[] objs){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			setParams(ps,types,objs);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(getValues(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
