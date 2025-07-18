package SMSUsingMySQL;

import java.sql.*;
import java.util.Scanner;

public class SMS {
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
	 private static final String USER = "root";
	 private static final String PASS = "phani2127"; 

	 public static void main(String[] args) {
	     try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	          Scanner sc = new Scanner(System.in)) {

	         while (true) {
	             System.out.println("\nStudent Management System");
	             System.out.println("1. Add Student\n2. View All Students\n3. Update Student\n4. Delete Student\n5. Search Students by ID\n6. Search Students by Name\n7. Count Total Students\n8. Exit");
	             System.out.print("Enter your choice: ");
	             int choice = sc.nextInt();
	             sc.nextLine();
	             switch (choice) {
	                 case 1 : 
	                	 addStudent(conn, sc);
	                	 break;
	                 case 2 : 
	                	 viewAllstudents(conn, sc);
	                	 break;
	                 case 3 :
	                	 updateStudent(conn, sc);
	                	 break;
	                 case 4 :
	                	 deleteStudent(conn, sc);
	                	 break;
	                 case 5 : 
	                	 searchStudentById(conn,sc);
	                	 break;
	                 case 6 : 
	                	 searchStudentByName(conn,sc);
	                	 break;
	                 case 7 :
	                	 totalStudents(conn);
	                	 break;
	                 case 8 : {
	                     System.err.println("Exiting...");
	                     return;
	                 }
	                 default : System.err.println("Invalid choice.");
	             }
	         }
	     } catch (SQLException e) {
	         System.out.println("Database error: " + e.getMessage());
	     }
	 }
 public static void viewAllstudents(Connection conn,Scanner sc) throws SQLException {
		 String sql="select * from student;";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			boolean hasStudent=false;
			while(rs.next()) {
				hasStudent=true;
				System.out.println("Id: "+rs.getInt(1)+", Name: "+rs.getString(2)+", Age: "+rs.getInt(3)+", Grade: "+rs.getString(4)+", Email: "+rs.getString(5));
			}
			if(!hasStudent) {
				System.err.println("No Students are there !");
			}
			
		}
	 }
 public static void addStudent(Connection conn,Scanner sc) throws SQLException{
	 String sql="insert into student values(?,?,?,?,?);";
	 System.out.println("Enter student id : ");
	 int id=sc.nextInt();
	 sc.nextLine();
	 System.out.println("Enter student name : ");
	 String name=sc.nextLine();
	 System.out.println("Enter student age : ");
	 int age=sc.nextInt();
	 sc.nextLine();
	 System.out.println("Enter student grade : ");
	 String grade=sc.nextLine();
	 System.out.println("Enter student email : ");
	 String email=sc.nextLine();
	 
	 try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
		 pstmt.setInt(1, id);
		 pstmt.setString(2, name);
		 pstmt.setInt(3, age);
		 pstmt.setString(4, grade);
		 pstmt.setString(5, email);
		 pstmt.executeUpdate();
		 System.err.println("Student is added successfully ");
	 }
 }
 public static void updateStudent(Connection conn,Scanner sc) throws SQLException{
	 String sql="update student set Sgrade=?,  email =? where sid =?";
	 System.out.println("Enter student id : ");
	 int id=sc.nextInt();
	 sc.nextLine();
	 System.out.println("Enter new grade : ");
	 String grade=sc.nextLine();
	 System.out.println("Enter new email : ");
	 String email=sc.nextLine();
	 try(PreparedStatement pstmt = conn.prepareStatement(sql)){
		 pstmt.setString(1, grade);
		 pstmt.setString(2,email);
		 pstmt.setInt(3, id);
		 int effectedRows=pstmt.executeUpdate();
		 System.err.println(effectedRows>0?"Student updated successfully":"Failed to update");
	 }
 }
 public static void deleteStudent(Connection conn,Scanner sc) throws SQLException{
	 String sql="delete from student where Sid=?;";
	 try(PreparedStatement pstmt=conn.prepareStatement(sql)){
		 System.out.println("Enter student id to delete : ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 pstmt.setInt(1, id);
		 int effectedRows=pstmt.executeUpdate();
		 System.out.println(effectedRows>0?"Deleted successfully":"Failed to delete!");
	 }
	 
 }
 public static void searchStudentById(Connection conn,Scanner sc) throws SQLException{
	 String sql="select * from student where sid=?";
	 try(PreparedStatement pstmt = conn.prepareStatement(sql)){
		 System.out.println("Enter student id : ");
		 int id=sc.nextInt();
		 sc.nextLine();
		 pstmt.setInt(1, id);
		 ResultSet rs = pstmt.executeQuery();
		 boolean hasStudent=false;
			while(rs.next()) {
				hasStudent=true;
				System.out.println("Student Found: Id: "+rs.getInt(1)+", Name: "+rs.getString(2)+", Age: "+rs.getInt(3)+", Grade: "+rs.getString(4)+", Email: "+rs.getString(5));
			}
			if(!hasStudent) {
				System.err.println("Student not found !");
			}
		 
	 }
 }
 public static void searchStudentByName(Connection conn,Scanner sc) throws SQLException{
	 String sql="select * from student where sname=?";
	 try(PreparedStatement pstmt = conn.prepareStatement(sql)){
		 System.out.println("Enter student name : ");
		 String name=sc.nextLine();
		 pstmt.setString(1, name);
		 ResultSet rs = pstmt.executeQuery();
		 boolean hasStudent=false;
			while(rs.next()) {
				hasStudent=true;
				System.out.println("Student Found: Id: "+rs.getInt(1)+", Name: "+rs.getString(2)+", Age: "+rs.getInt(3)+", Grade: "+rs.getString(4)+", Email: "+rs.getString(5));
			}
			if(!hasStudent) {
				System.err.println("Student not found !");
			}
		 
	 }
 }
 public static void totalStudents(Connection conn) throws SQLException {
	 String sql="select count(*) from student";
	 try(PreparedStatement pstmt=conn.prepareStatement(sql)){
		 ResultSet rs =pstmt.executeQuery();
		 while(rs.next()) {
			 System.out.println("Total Students : "+rs.getInt(1));
		 }
	 }
 }
}