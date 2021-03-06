import java.sql.*;
import java.util.*;
public class week7q1{

	public static void main(String[] args) {
		Connection con=null;
		Scanner input=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			String sql="create table employee (id varchar(15) not null,name varchar(255),age integer,salary integer,primary key(id))";
			stmt.executeUpdate(sql);
			ResultSet rs;
			String query="insert into employee values(?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			int age,salary;
			String name,id;
			int n;
			System.out.println("Number of tuples to be entered: ");
			n=input.nextInt();
			for(int i=0;i<n;i++) {
				System.out.println("Enter Id : ");
				input.nextLine();
				id=input.nextLine();
				System.out.println(id);
				System.out.println("Enter Name : ");
				name=input.nextLine();
				System.out.println("Enter Age : ");
				age=input.nextInt();
				System.out.println("Enter Salary : ");
				salary=input.nextInt();
				pstmt.setInt(3, age);
				pstmt.setInt(4, salary);
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.execute();
				}
			sql="select id,name,age,salary from employee";
			rs=stmt.executeQuery(sql);
			rs.next();
			int newsalary=rs.getInt("salary")+10000;
			System.out.println("Salary: "+rs.getInt("salary"));
			rs.updateInt("salary", newsalary);
			rs.updateRow();
			System.out.println("Updated Salary: "+rs.getInt("salary"));
			rs.absolute(2);
			String newname="Hello";
			System.out.println("Name: "+rs.getString("name"));
			rs.updateString("name",newname);
			rs.updateRow();
			System.out.println("Updated Name: "+rs.getString("name"));
			
			rs.moveToInsertRow();
			rs.updateString(1, "1ms16cs000");
			rs.updateString(2,"Raplh");
			rs.updateInt(3,10);
			rs.updateInt(4,100000);
			rs.insertRow();
			rs.absolute(0);
			while(rs.next()) {
				if(rs.getString("id").equals("10")) {
					System.out.println(rs.getString("name")+" Deleted");
					rs.deleteRow();
				}
			}
		}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		catch(SQLException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
			con.close();
			}catch(Exception e) {}
		}
	}



}//cs021
