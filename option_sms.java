import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.HashMap;
import java.util.Scanner;
//import java.util.Map.Entry;
public class option_sms {
	Scanner menuInput=new Scanner(System.in);
//	HashMap<Integer,String> data=new HashMap<Integer,String>();
//	public void getLogin() throws IOException
//	{
//		int x=1;
//		do {
//			try {
//				data.put(18,"bohemia");
//				
//				System.out.println("Welcome! to the ATM");
//				
//				System.out.println("Enter your ID");
//				setID(menuInput.nextInt());
//				
//				System.out.println("Enter your Password");
//				setPassword(menuInput.next());
//				
//			}catch(Exception e) {
//				System.out.println("\n"+"Invalid Characters"+"\n");
//				x=2;
//			}
//			for(Entry<Integer,String> entry:data.entrySet())
//			{
//				if(entry.getKey()==getID() && entry.getValue()==getPassword())
//				{
//					menu();
//				}
//				else
//				{
//					System.out.println("\n"+"Wrong ID or Password");
//				}
//			}
//		}while(x==1);
		
		
	// Main Menu
	public void menu() throws Exception
	{
//		 String url="jdbc:mysql://localhost:3306/sms";
//	      String uname="root";
//	      String pass="qwerty1234";
//	      Class.forName("com.mysql.jdbc.Driver");
//		  Connection con=DriverManager.getConnection(url,uname,pass);
//		  Statement st=con.createStatement();
		int selection;
		System.out.println("Welcome to the Main Menu");
		System.out.println("Type 1- Display all student records");
		System.out.println("Type 2- Display specific student");
		System.out.println("Type 3- Delete specific student ");
		System.out.println("Type 4- Add a new student");
		System.out.println("Type 5- Exit");
		System.out.println("Choice: ");
		selection=menuInput.nextInt();
		String url="jdbc:mysql://localhost:3306/sms";
	      String uname="root";
	      String pass="qwerty1234";
		switch(selection)
		{
		
		case 1:			
			displayStudents(url,uname,pass);
			break;
			
		case 2:
			displayStudent(url,uname,pass);
			break;
			
		case 3:
			deleteStudent(url,uname,pass);
			break;
			
		case 4:
			newStudent(url,uname,pass);
			break;
			
		case 5:
			System.out.println("EXIT!!");
			break;
			
			
		default:
			System.out.println("\n"+"Invalid Choice"+"\n");
			menu();
		}
	}
	public void displayStudents(String url,String uname,String pass) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="select * from student";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query); 			
//		rs.next();
		String userdata="";
		System.out.println("NAME  "+" RollNo. "+"  AGE");
 		while(rs.next())
		{
			userdata=rs.getString(1)+" : "+rs.getInt(2)+" : "+rs.getInt(3);
			System.out.println(userdata);
		}
 		menu();
	}
	public void displayStudent(String url,String uname,String pass) throws Exception
	{
		int id;
		System.out.println("Enter the Roll No. of the Student ");
		id=menuInput.nextInt();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="select * from student where rno='"+id+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query); 			
		String userdata="";
 		if(rs.next())
		{
			userdata=rs.getString(1)+" : "+rs.getInt(2)+" : "+rs.getInt(3);
			System.out.println(userdata);
		}
 		menu();
	}
	public void deleteStudent(String url,String uname,String pass) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		int id;
		System.out.println("Enter the Roll No. of the Student that you want to delete ");
		id=menuInput.nextInt();
		String query="delete from student where rno='"+id+"'";
		PreparedStatement st=con.prepareStatement(query);
		int count=st.executeUpdate();   // For Prepared statement
		System.out.println(count+" row/s affected");
 		menu();
	}
	public void newStudent(String url,String uname,String pass) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		String query="insert into student values(?,?,?)";
 		System.out.println("Enter name of the Student ");
 		String n=menuInput.next();
 		System.out.println("Enter the rno of the Student ");
 		int rn=menuInput.nextInt();
 		System.out.println("Enter the age of the Student ");
 		int ag=menuInput.nextInt();
 		PreparedStatement st=con.prepareStatement(query);
		st.setString(1, n);
		st.setInt(2, rn);
		st.setInt(3, ag);
		int count=st.executeUpdate();   // For Prepared statement
		System.out.println(count+" row/s affected");
		st.close();
		con.close();
		menu();
	}
	
}
