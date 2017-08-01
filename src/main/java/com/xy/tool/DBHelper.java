package com.xy.tool;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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

import com.mysql.jdbc.Statement;

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
					if(objs[i] == null){ ps.setNull(i+1, Types.VARCHAR);break;}
					ps.setString(i+1, (String)objs[i]);
					break;
				case SET_BIGDECIMAL :	
					if(objs[i] == null){ ps.setNull(i+1, Types.DECIMAL);break;}
					ps.setBigDecimal(i+1, (BigDecimal)objs[i]);
					break;
				case SET_STREAM :	
					if(objs[i] == null){ ps.setNull(i+1, Types.BLOB);break;}
					ps.setBlob(i+1, (InputStream)objs[i]);
					break;
				case SET_DATE :	
					if(objs[i] == null){ ps.setNull(i+1, Types.DATE);break;}
					ps.setDate(i+1, (Date)objs[i]);
					break;
				case SET_DOUBLE :	
					if(objs[i] == null){ ps.setNull(i+1, Types.DOUBLE);break;}
					ps.setDouble(i+1, (Double)objs[i]);
					break;
				case SET_LONG :	
					if(objs[i] == null){ ps.setNull(i+1, Types.BIGINT);break;}
					ps.setLong(i+1, (Long)objs[i]);
					break;
				case SET_INT :	
					if(objs[i] == null){ ps.setNull(i+1, Types.INTEGER);break;}
					ps.setInt(i+1, (Integer)objs[i]);
					break;
				case SET_OBJECT :
					if(objs[i] == null){ ps.setNull(i+1, Types.VARCHAR);break;}
					ps.setObject(i+1, objs[i]);
					break;
				case SET_TIMESTAMP :	
					if(objs[i] == null){ ps.setNull(i+1, Types.TIMESTAMP);break;}
					ps.setTimestamp(i+1, (Timestamp)objs[i]);
					break;
				default :
					if(objs[i] == null){ ps.setNull(i+1, Types.VARCHAR);break;}
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
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
				if(ps!=null) ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Long updateReturnId(String sql,DBConstants[] types,Object[] objs){
		Long result = 0L;
		PreparedStatement ps = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParams(ps,types,objs);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
				if(ps!=null) ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int[] updateBatch(String sql,DBConstants[] types,Object[][] objs){
		int[] result = null;
		PreparedStatement ps = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<objs.length;i++){
				setParams(ps,types,objs[i]);
				ps.addBatch();
			}
			result = ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.commit();
				if(ps!=null) ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Long[] updateReturnIds(String sql,DBConstants[] types,Object[] objs,int size){
		Long[] ids = null;
		int result = -1;
		PreparedStatement ps = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParams(ps,types,objs);
			result = ps.executeUpdate();
			ids = new Long[result];
			ResultSet rs = ps.getGeneratedKeys();
			int i=0;
			while(rs.next()){
				ids[i++] = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(result != size) conn.rollback();
				else conn.commit();
				if(ps!=null) ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ids;
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
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}

class MyConnection {
	public static Connection getConn(String driver,String url,String username, String password){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
