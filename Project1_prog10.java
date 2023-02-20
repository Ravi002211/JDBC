package com.JDBC;
import java.sql.*;
import java.util.Scanner;	 	
public class Project1_prog10 {
	private static final String path="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String un="system";
	private static final String pw="system";
	private static  Connection con=null;
	private static  Statement stmt=null;
	private static  PreparedStatement pstmt=null;
	private static  ResultSet rs=null;
	public static void main(String[] args) {
		try {
		Project1_prog10 p=new Project1_prog10();
		p.loadDriver();
		p.estabConn();
		while(true) {
			System.out.println("Press 1 to Create Table");
			System.out.println("Press 2 to Insert Values");
			System.out.println("Press 3 to Update Values");
			System.out.println("Press 4 to Delete values");
			System.out.println("Press 5 to Display The Table");
			System.out.println("Press 6 to Display Specific Row Of Table");
			System.out.println("Press 7 to STOP");
			System.out.println("Enter your choice");
			Scanner in=new Scanner(System.in);
			int ch=in.nextInt();
			switch(ch) {
			case 1:{
				p.createTable();
				break;
			}
			case 2:{
					p.insertValues();
					break;
			}
			case 3:{
				 p.updateData();
				 break;
			}
			case 4:{
				p.deleteData();
				break;
			}
			case 5:{
				p.displayTable();
				break;
			}
			case 6:{
				p.displaySpecificRow();
				break;
			}
			default:{
				System.out.println("Project Stop");
				System.exit(0);
			}
			}
		}
	}
		catch (Exception e) {
		 e.printStackTrace();
		}
		finally {
			try {
				if (stmt!=null) {
					stmt.close();
				}
				if (pstmt!=null) {
					pstmt.close();
				}
				if (con!=null) {
					con.close();
				}
				if (rs!=null) {
					rs.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 public  void loadDriver() throws ClassNotFoundException {
		 Class.forName(path);
		 System.out.println("Driver loaded Successfully");
	 }
	 public void estabConn() throws SQLException {
		 con=DriverManager.getConnection(url,un,pw);
		 System.out.println("Connection Established successfully");
	 }
	 public void createTable() throws SQLException{
		 String query="create table course(Name varchar2(25),price number,duration number)";
		 stmt=con.createStatement();
		 stmt.executeUpdate(query);
		 System.out.println("Table created Successfully");
	 }
	 public void insertValues() throws SQLException {
		 String query="insert into course values(?,?,?)";
		 Scanner in=new Scanner(System.in);
		 System.out.println("Enter course Name");
		 String course=in.next();
		 System.out.println("Enter Course Price");
		 int price=in.nextInt();
		 System.out.println("Enter Duration of Course");
		 int dur=in.nextInt();
		 pstmt=con.prepareStatement(query);
		 pstmt.setString(1, course);
		 pstmt.setInt(2, price);
		 pstmt.setInt(3, dur);
		 pstmt.executeUpdate();
		 System.out.println("Insertion Completed");
	 }
	 public void updateData() throws SQLException {
		 String query="update course set price=? where name=?";
		 Scanner in=new Scanner(System.in);
		 System.out.println("Enter course name to update price");
		 String name=in.next();
		 System.out.println("Enter New Price");
		 int price=in.nextInt();
		 pstmt=con.prepareStatement(query);
		 pstmt.setInt(1, price);
		 pstmt.setString(2,name);
		 pstmt.executeUpdate();
		 System.out.println("Update Successfully");
	 }
	 public void deleteData() throws SQLException {
		 String query="delete from course where name=?";
		 Scanner in=new Scanner(System.in);
		 System.out.println("Enter course name to delete data");
		 String name=in.next();
		 pstmt=con.prepareStatement(query);
		 pstmt.setString(1, name);
		 pstmt.executeUpdate();
		 System.out.println("Data Deleted Successfully");
	 }
	 public void displayTable() throws SQLException {
		 String query=" select * from course";
		 stmt=con.createStatement();
		 rs=stmt.executeQuery(query);
		 while(rs.next()==true) {
			 String name=rs.getString("name");
			 int price=rs.getInt("price");
			 int dur=rs.getInt("duration");
			 System.out.println(name+" "+price+" "+dur);
		 }
		 System.out.println("Table Details:");
	 }
	 public void displaySpecificRow() throws SQLException {
		 String query=" select * from course where name=?";
		 System.out.println("Enter Course Name to Display its Data");
		 Scanner in=new Scanner(System.in);
		 String name=in.next();
		 pstmt=con.prepareStatement(query);
		 pstmt.setString(1, name);
		 rs=pstmt.executeQuery();
		 while(rs.next()==true) {
			 String name1=rs.getString("name");
			 int price=rs.getInt("price");
			 int dur=rs.getInt("duration");
			 System.out.println(name+" "+price+" "+dur);
		 }
		 System.out.println("Display Successfully");
	 }
}
