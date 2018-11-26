package HelloGreet;

import java.sql.*;  
import java.io.*;  
class jdbc{  
public static void main(String args[])throws Exception{  
	System.out.println("MySQL Connect Example.");
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "mydb";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root"; 
	String password = "";
	Class.forName(driver).newInstance();
	conn = DriverManager.getConnection(url+dbName,userName,password);
	System.out.println("Connected to the database");
	String myusn,myname;
PreparedStatement ps=conn.prepareStatement("insert into stud values(?,?)");  
Statement stmt = conn.createStatement();  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
do{  
	//int id=Integer.parseInt(br.readLine());
	//float salary=Float.parseFloat(br.readLine()); 
	//ps.setFloat(2, salary);
	System.out.println("enter usn:");  
	myusn=br.readLine(); 
	System.out.println("enter name:");  
	myname=br.readLine();  
	
	ps.setString(1,myusn);  
	ps.setString(2,myname);  
	
	int i=ps.executeUpdate();  
	System.out.println(i+" records added");  
	System.out.println("Do you want to continue: y/n");  
	String s=br.readLine();  
	if(s.startsWith("n")){  
		break;  
	}  
}while(true); 
String sql = "SELECT * from stud";
ResultSet rs = stmt.executeQuery(sql);
System.out.println("The records are :");
while (rs.next())
	{
		myusn = rs.getString(1);
		myname=rs.getString(2);
		
		System.out.println(rs.getRow()+"-"+myusn+"  "+myname);
	} //end while 
conn.close();  
}}  