package com.renke.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DBMain {
	
	public static void main(String[] args) {
		int i=1;
		BlobObj bo = new BlobObj();
		bo.setDt(new Date());
		bo.setId(i);
		bo.setName("name:"+i);
		bo.setValue("val:"+i);
		
		/**
		 * @TODO properties文件已经定义好，但是读取方法没写
		 */
		DBHelper dbh = new DBHelper("com.mysql.jdbc.Driver"
									,"jdbc:mysql://test4xdm.mysql.rds.aliyuncs.com:3356/xdm_dev?characterEncoding=utf8"
									,"xdmadmin"
									,"xdm1qa2ws");
		
		/************************************************
		 * 												*
		 *					insert						*
		 * 												*
		 ************************************************/
//		String sql = "INSERT INTO t_test_blob (name,object) values(?,?)";
//		DBConstants[] types = {DBConstants.SET_STRING,DBConstants.SET_OBJECT};
//		dbh.update(sql, types, new Object[]{"myName",bo});
		
		
		
		/************************************************
		 * 												*
		 * 					select						*
		 * 												*
		 ************************************************/
		String sql = "select * from t_test_blob";
		List<Map<String,Object>> result = dbh.select(sql, null, null);
		for(Map<String,Object> map : result){
			Blob blob = (Blob)map.get("OBJECT");
			try {
				InputStream is = blob.getBinaryStream();
				ObjectInputStream bis = new ObjectInputStream(is);
				bo = (BlobObj)bis.readObject();
				System.out.println(bo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
//		DBConstants[] types = {DBConstants.SET_STRING,DBConstants.SET_OBJECT};
//		dbh.update(sql, types, new Object[]{"myName",bo});
		
	}
}
