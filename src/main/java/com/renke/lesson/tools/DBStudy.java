package com.renke.lesson.tools;

import java.sql.Timestamp;
import java.util.Random;

import com.renke.util.db.DBConstants;

public class DBStudy {
	public static String[] center = {"beijing","shanghai","guangzhou"};
	
	public static void main(String[] args) {
		DBHelper test = new DBHelper("com.mysql.jdbc.Driver"
				,"jdbc:mysql://192.168.20.21:3306/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
				,"exschool_test"
				,"exschool2012");
		String sql = "insert into product (prod_name,supplier_id,supplier_name,prod_price,prod_status,supplier_center,last_order_date)"
							+ " values(?,?,?,?,?,?,?)";
		DBConstants[] types = {DBConstants.SET_STRING,DBConstants.SET_INT, DBConstants.SET_STRING, DBConstants.SET_DOUBLE
					, DBConstants.SET_INT, DBConstants.SET_STRING, DBConstants.SET_TIMESTAMP};
		Random random = new Random();
		int cnt = 0;
		for(int i=0;i<1000;i++){
			Object[][] objs = new Object[1000][7];
			Long time = System.currentTimeMillis();
			for(int m=0;m < 1000; m++){
				int s_id = random.nextInt(5000);
				objs[m][0] = "商品："+(i*1000 + m) ;
//				System.out.println(objs[m][0]);
				objs[m][1] = s_id;
				objs[m][2] = "供货商"+s_id;
				objs[m][3] = (double)random.nextInt(10000) + random.nextInt(100) / 100d;
				objs[m][4] = random.nextInt(10) <= 1 ? 0 : 1;
				objs[m][5] = center[random.nextInt(3)];
				objs[m][6] = new Timestamp(time - random.nextInt(365 * 24 * 60) * 60 * 1000L);
			}
			test.updateBatch(sql, types, objs);
			cnt += 1000;
			System.out.println("插入："+(cnt-1000) + "-"+cnt+";花费："+ (System.currentTimeMillis() - time) + "ms");
		}
	}
	
//	public static void main(String[] args) {
//		Random random = new Random();
//		for(int i=0;i<30;i++){
//			System.out.println(random.nextInt(3));
//		}
//	}
}
