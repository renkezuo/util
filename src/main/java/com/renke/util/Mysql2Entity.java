package com.renke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class Mysql2Entity {

	private String packageOutPath = "cn.strong.leke.paike.model";//指定实体生成所在包的路径
	private String authorName = "Z.R.K";//作者名字
	private String tablename = "ls_paike_time";//表名
	private String[] colnames; // 列名数组
	private String[] colTypes; //列名类型数组
	private int[] colSizes; //列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
    //数据库连接
	private static final String URL ="jdbc:mysql://192.168.20.21:3306/lesson?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	private static final String NAME = "exschool_test";
	private static final String PASSWORD = "exschool2012";
	private static final String DRIVER ="com.mysql.jdbc.Driver";

	/*
	 * 构造函数
	 */
	public Mysql2Entity(Connection con,String tableName){
		this.tablename = tableName;
    	//创建连接
		//查要生成实体类的表
    	String sql = "select * from " + tablename;
    	PreparedStatement pStemt = null;
    	try {
    		
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount();	//统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				
				if(colTypes[i].equalsIgnoreCase("datetime")){
					f_util = true;
				}
				if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			
			String content = parse(colnames,colTypes,colSizes);
			
			try {
				String dir = "E:/autoModel"+ "/src/"+this.packageOutPath.replace(".", "/");
				File file = new File(dir);
				if(!file.exists()) file.mkdirs();
				String outputPath = dir +"/"+parseName(tablename) + ".java";
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
		}
    }

	/**
	 * 功能：生成实体类主体代码
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("package " + this.packageOutPath + ";\r\n");
		sb.append("\r\n");
		//判断是否导入工具包
		if(f_util){
			sb.append("import java.util.Date;\r\n");
		}
		if(f_sql){
			sb.append("import java.sql.*;\r\n");
		}
//		sb.append("import javax.persistence.Column;\r\n");
//		sb.append("import javax.persistence.Entity;\r\n");
//		sb.append("import javax.persistence.GeneratedValue;\r\n");
//		sb.append("import javax.persistence.GenerationType;\r\n");
//		sb.append("import javax.persistence.Id;\r\n");
//		sb.append("import javax.persistence.Table;\r\n");
//		sb.append("import javax.persistence.Temporal;\r\n");
//		sb.append("import javax.persistence.TemporalType;\r\n");
//		sb.append("import org.springframework.format.annotation.DateTimeFormat;\r\n");
		Calendar cld = Calendar.getInstance();
		//注释部分
		sb.append("/**\r\n");
		sb.append(" * "+tablename+" \r\n");
		sb.append(" * @author "+this.authorName+"\r\n");
		sb.append(" * @time "+cld.get(Calendar.YEAR)+"-"+cld.get(Calendar.MONTH)+"-"+cld.get(Calendar.DAY_OF_MONTH)+" "
							+cld.get(Calendar.HOUR_OF_DAY)+":"+cld.get(Calendar.MINUTE)+":"+cld.get(Calendar.SECOND)+"\r\n");
		sb.append(" */ \r\n");
		//实体部分
//		sb.append("@Entity\r\n");
//		sb.append("@Table(name = \""+tablename+"\")\r\n");
		sb.append("public class " + parseName(tablename) + " extends BaseModel{\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;");
		processAllAttrs(sb);//属性
		processAllMethod(sb);//get set方法
		sb.append("}\r\n");
		
    	//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 功能：生成所有属性
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {
		
		for (int i = 0; i < colnames.length; i++) {
			String column = parseNameNotFirst(colnames[i]);
			if(column.equals("fileSize")){
				System.out.println("here");
			}
			sb.append("\r\n");
//			if("id".equals(column)){
//				sb.append("\t@Id\r\n");
//				sb.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
//				sb.append("\t@Column(name = \""+colnames[i]+"\", unique = true, nullable = false)\r\n");
//			}else{
//				sb.append("\t@Column(name = \""+colnames[i]+"\")\r\n");
//			}
//			if("Date".equals(sqlType2JavaType(colTypes[i]))){
//				sb.append("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
//				sb.append("\t@Temporal(value = TemporalType.TIMESTAMP)\r\n");
//			}
			if(column.equals("isDeleted"))break;
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " " + column + ";\r\n");
		}
		
	}

	/**
	 * 功能：生成get、set方法
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
		
		for (int i = 0; i < colnames.length; i++) {
			if(colnames[i].equals("isDeleted")) break;
			sb.append("\r\n");
			sb.append("\tpublic void set" + parseName(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + 
					parseNameNotFirst(colnames[i]) + "){\r\n");
			sb.append("\t\tthis." + parseNameNotFirst(colnames[i]) + "=" + parseNameNotFirst(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + parseName(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + parseNameNotFirst(colnames[i]) + ";\r\n");
			sb.append("\t}\r\n");
		}
		
	}
	
	
	/**
	 * 描述：第一个首字母不大写，后面大写
	 * @param name
	 * @return
	 * String
	 */
	public String parseNameNotFirst(String name){
		StringBuffer sb = new StringBuffer();
		String[] array = name.split("_");
		if(array.length > 0){
			for (int i = 0; i < array.length; i++) {
				if (i>0) {
					sb.append(initcap(array[i]));
				}else{
					sb.append(array[i]);
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 描述：全部首字母大写
	 * @param name
	 * @return
	 * String
	 */
	public String parseName(String name){
		StringBuffer sb = new StringBuffer();
		String[] array = name.split("_");
		if(array.length > 0){
			for (String string : array) {
				if(!string.equals("ls"))
					sb.append(initcap(string));
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 功能：将输入字符串的首字母改成大写（修改成驼峰法）
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		
		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}
		
		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {
		
		if(sqlType.equalsIgnoreCase("bit")){
			return "Boolean";
		}else if(sqlType.equalsIgnoreCase("tinyint")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("smallint")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")){
			return "Integer";
		}else if(sqlType.equalsIgnoreCase("bigint")){
			return "Long";
		}else if(sqlType.equalsIgnoreCase("float")){
			return "Float";
		}else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
				|| sqlType.equalsIgnoreCase("smallmoney")){
			return "Double";
		}else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
				|| sqlType.equalsIgnoreCase("text")){
			return "String";
		}else if(sqlType.equalsIgnoreCase("datetime")){
			return "Date";
		}else if(sqlType.equalsIgnoreCase("image")){
			return "Blob";
		}
		
		return null;
	}
	
	/**
	 * 出口
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> tableList = new ArrayList<String>();
		Connection con;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,NAME,PASSWORD);
			DatabaseMetaData  dm = con.getMetaData();
			ResultSet rs  = dm.getTables(con.getCatalog(), "root", null, new String[]{"TABLE"});
			while(rs.next()) {
				tableList.add(rs.getString("TABLE_NAME"));
			    System.out.println("得到表名："+rs.getString("TABLE_NAME"));
			    new Mysql2Entity(con,rs.getString("TABLE_NAME"));
			}
			/*if (tableList!=null) {
				for (String tableName : tableList) {
					new GenEntityMysql(con,tableName);
				}
			}*/
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
