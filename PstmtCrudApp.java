package com.sachin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbcUtil.JdbcConnection;
import com.mysql.cj.xdevapi.PreparableStatement;

public class PstmtCrudApp {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		ResultSet resultSet = null;
		Statement statement = null;
		
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
		
		try 
		{
			conn = JdbcConnection.getJdbcConnection();
			
			if (operationSelected == 1) {
				System.out.print("Enter The Id to Get Values - ");
				int value = scanner.nextInt();
				
				String selectQuery = "select name,fathers_name,mothers_name from students where student_id = ? ";
				
				PreparedStatement pstmt = conn.prepareStatement( selectQuery );
				
				if (pstmt != null) {
					pstmt.setInt(1, value);
					
					resultSet = pstmt.executeQuery();
				}
				
				if (resultSet != null) {
					
					if (resultSet.next()) {
						System.out.println("name\tfathersname\tmothersname");
						System.out.println(resultSet.getString(1) +"\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
					}
				}
			} else if (operationSelected == 2 ) {
				
				System.out.println("Enter the Name");
				String name = scanner.next();
				System.out.println("Enter the Mother Name");
				String fatherName = scanner.next();
				System.out.println("Enter the Father Name");
				String motherName = scanner.next();
				
				String insertQuery = "insert into students (name, fathers_name, mothers_name) values(?, ?, ?) ";
				
				PreparedStatement pstmt = conn.prepareStatement( insertQuery );
				
				if (pstmt != null) {
					pstmt.setString(1, name);
					pstmt.setString(2, fatherName);
					pstmt.setString(3, motherName);
					
					int noOfRowsInserted = pstmt.executeUpdate();
					
					if (noOfRowsInserted > 0) {
						System.out.println("Inserted Successfully");
					} else {
						System.out.println("Not Inserted");
					}
				}
				
			} 
			else if (operationSelected == 3) {
				
				System.out.println("Enter Id To Update Name");
				int value = scanner.nextInt();
				
				System.out.println("Enter The Name to Update");
				String upateName = scanner.next();
				
				String updateQuery = "update students set name = ? where student_id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement( updateQuery );
				
				if (pstmt != null) {
					pstmt.setString(1, upateName);
					pstmt.setInt(2, value);
					
					int noOfRowsUpdated = pstmt.executeUpdate();
					
					if (noOfRowsUpdated > 0) {
						System.out.println("Updated Successfully");
					} else {
						System.out.println("Not Updated");
					}
				}
				
			}
			else if (operationSelected == 4) {
				
				
				System.out.println("Enter the Id To Delete");
				int vlaue = scanner.nextInt();
				
				
				String deleteQuery = " DELETE FROM students where student_id = ? ";
				
				PreparedStatement pstmt = conn.prepareStatement( deleteQuery );
				
				if (pstmt != null) {
					pstmt.setInt(1, vlaue);
					
					int noOfRowsDeleted = pstmt.executeUpdate();
					
					if (noOfRowsDeleted > 0) {
						System.out.println("Deleted Successfully");
					} else {
						System.out.println("Not Deleted");
					}
				}
			}
			
			
		} catch (SQLException se)
		{
			se.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally 
		{
			JdbcConnection.closeConnection(resultSet, statement, conn);
			
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}
