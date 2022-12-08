package com.sachin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrudApp {

	public static void main(String[] args) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Select the operations");
		System.out.println("=====================");
		System.out.println("1. Read");
		System.out.println("2. Insert");
		System.out.println("3. Edit");
		System.out.println("4. Delete");
		
		System.out.println();
		System.out.println("Please Select,,,,,");
		int operationSelected = scanner.nextInt();
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resultset = null;
		int value;
		
		
		// 2. Establish the connection
		String url = "jdbc:mysql://localhost:3306/jdbc_app";
		String user = "root";
		String pass = "";
		
		try 
		{
			conn = DriverManager.getConnection(url, user, pass);
			
			if(conn != null)
			{
				statement = conn.createStatement();
				
				if(statement != null)
				{
					if (operationSelected == 1) 
					{
						System.out.println("Enter the Id To get the Values");
						value = scanner.nextInt();
						
						String sqlQuery = "select name,fathers_name,mothers_name from students where student_id = "+value+" "; 
						resultset = statement.executeQuery(sqlQuery);
						
						if(resultset != null)
						{	
							if (resultset.next()) {
								System.out.println("name\tfathers_name\tmothers_name");
								
								String name = resultset.getString("name");
								String fathers_name = resultset.getString("fathers_name");
								String mothers_name = resultset.getString("mothers_name");
								
								System.out.println(name + "\t" + fathers_name + "\t" + mothers_name);
							} else {
								System.out.println("No Data Found for values " + value);
							}
							
						} 
					} else if (operationSelected == 2) 
					{
						System.out.println("Enter the Name");
						String name = scanner.next();
						System.out.println("Enter the Father Name");
						String fathers_name = scanner.next();
						System.out.println("Enter the Mother Name");
						String mothers_name = scanner.next();
						
						String insertQuery = String.format("insert into students(`name`,`fathers_name`,`mothers_name`) values('%s','%s','%s')",name, fathers_name, mothers_name);
						int noOfRowsInserted = statement.executeUpdate(insertQuery);
						System.out.println("Successfully Inserted " + noOfRowsInserted + " Rows");
						
					}
					else if (operationSelected == 3) 
					{
						System.out.println("Enter the Id To Update the Values");
						value = scanner.nextInt();
						
						System.out.println("Give the New Name to Update the Existing Name");
						String name = scanner.next();
						name = "'"+ name +"'";
						
						String updateQuery = "update students set name = "+ name +" where student_id = "+value+" "; 
						int noOfRowsUpdated = statement.executeUpdate(updateQuery);
						System.out.println("Successfully Updated");
					} 
					else if (operationSelected == 4) 
					{
						System.out.println("Enter the Id To Update the Values");
						value = scanner.nextInt();
						
						String deleteQuery = "delete from students where student_id = "+value+" "; 
						int noOfRowsDeleted = statement.executeUpdate(deleteQuery);
						System.out.println("Successfully Deleted " + noOfRowsDeleted + " Row");
					} 
					else 
					{
						System.out.println("Don't be Too Smart!! Please Select The Option 1 to 4");
					}
					
				}
			}
			
		} catch (SQLException se)
		{
			se.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			
			// 6. Closing the resoures used
			if(resultset != null)
			{
				resultset.close();
			}
			
			if(statement != null)
			{
				statement.close();
			}
			
			if(conn != null)
			{
				conn.close();
			}
		}

	}

}
