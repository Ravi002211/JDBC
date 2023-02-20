package com.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class Taking_Input_from_user_to_insert_in_car {
	public static void main(String[] args) {
		String path="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String un="system";
		String pw="system";
		Connection con=null;
		PreparedStatement pstmt=null;
		//step 4: creating incomplete Query//
		String query="insert into car values(?,?)";
		try {
			//step 1: Driver loaded//
			Class.forName(path);
			System.out.println("Driver loaded successfully");
			//step 2: Establishing Connection//
			con=DriverManager.getConnection(url, un, pw);
			Scanner in=new Scanner(System.in);
			System.out.println("Connection Established");
			System.out.println("Enter Brand Name");
			String brand=in.next();
			System.out.println("Enter price");
			int price=in.nextInt();
			//step 3:creating prepared Statement//
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,brand);
			pstmt.setInt(2, price);
			//step 5: Executing Query//
			pstmt.executeUpdate();
			System.out.println("Query has been Executed");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
