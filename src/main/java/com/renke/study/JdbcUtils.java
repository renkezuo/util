package com.renke.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {

    private static String driver="com.ibm.db2.jcc.DB2Driver";
    private static String url="jdbc:db2://158.222.31.134:50000/acms";
    private static String user="acms";
    private static String psw="acms1234";
    
    private JdbcUtils(){}
    
    private static JdbcUtils instanse=new JdbcUtils();
    
    public static JdbcUtils getInstanse()
    {
        return instanse;
    }
    
    static{
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e)
        {
            throw new ExceptionInInitializerError(e);
        }
        
    }
    
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,user,psw);
     }
    
    
    public static void free(ResultSet rs,Statement st,Connection conn)
    {
        if(rs!=null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally
            {
                if(st!=null)
                    try {
                        st.close();
                    } catch (SQLException e) {
                        
                        e.printStackTrace();
                    }
                    finally
                    {
                        if(conn!=null)
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                
                                e.printStackTrace();
                            }
                    }
            }
    }
    public static void main(String[] args) {
		try {
			Connection con = JdbcUtils.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
}
