package com.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection 
{
	private JdbcConnection()
	{
		
	}
	
	public static Connection getJdbcConnection() throws SQLException
	{
		Connection conn = null;
		
		// 2. Establish the connection
		String url = "jdbc:mysql://localhost:3306/jdbc_app";
		String user = "root";
		String pass = "";
		
		conn = DriverManager.getConnection(url, user, pass);
			
		if (conn != null) 
		{
			return conn;
		} 
		
		return conn;
		
	}
	
	public static void closeConnection(ResultSet resultSet, Statement statement, Connection conn) throws SQLException
	{
		if(resultSet != null)
		{
			resultSet.close();
		}
		
		if (statement != null) 
		{
			statement.close();
		}
		
		if (conn != null) 
		{
			conn.close();
		}
	}
}
