package com.util;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPConn {
	
	private static Connection conn = null;
	
	private DBCPConn()
	{
		
	}
	
	public static Connection getConnection()
	{
		if(conn==null){
			try{				
				Context init = new InitialContext();
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle");
				conn = ds.getConnection(); 				
			}catch (Exception e){
				System.out.println(e.toString());
			}
		}
		
		return conn;
	}
	
	public static void close()
	{
		try{
			if(conn!=null){
				if(! conn.isClosed())
					conn.close();
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		conn = null;
	}
	

}
