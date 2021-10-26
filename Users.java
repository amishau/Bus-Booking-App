import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Users {

/*	+-----------+--------------+------+-----+---------+-------+
	| Field     | Type         | Null | Key | Default | Extra |
	+-----------+--------------+------+-----+---------+-------+
	| user_id   | int(11)      | YES  |     | NULL    |       |
	| firstname | varchar(100) | YES  |     | NULL    |       |
	| lastname  | varchar(100) | YES  |     | NULL    |       |
	| username  | varchar(100) | YES  |     | NULL    |       |
	| password  | varchar(100) | YES  |     | NULL    |       |
	+-----------+--------------+------+-----+---------+-------+
*/
	
	Scanner sc = new Scanner(System.in);
	
	public boolean usernameexists(String uname) {
		
		uname = "'"+uname+"'";
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from users where username = "+uname);  
			while(rs.next())  
			ans = rs.getInt(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		if(ans>0) {
			return true;
		}
		
		return false;

	}
	
	public boolean correctpassword(String uname, String pword) {
		
		String ans = "";
		uname = "'"+uname+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select password from users where username = "+uname);  
			while(rs.next())  
			ans = rs.getString(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		if(ans.equals(pword)) {
			return true;
		}
		
		return false;

	}
	
	public boolean userIDexists(int id) {
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from users where user_id = "+id);  
			while(rs.next())  
			ans = rs.getInt(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		if(ans>0) {
			return true;
		}
		
		return false;

	}
	
	public int total_rows() {
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from users"); 
			while(rs.next())
			ans = rs.getInt(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return ans;
	}
	
	public String[] getUser(String uname) {
		
		uname = "'"+uname+"'";
		
		String res[] = new String[5];
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users where username = "+uname);  
			while(rs.next())  
			for(int i=0;i<5;i++) {
				res[i]= rs.getString(i+1);
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return res;
		
	}
	
	public void addRow(String fname, String lname, String uname, String pword) {
		
		fname = "'"+fname+"'";
		lname = "'"+lname+"'";
		uname = "'"+uname+"'";
		pword = "'"+pword+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select user_id from users order by user_id desc limit 1");
			int lastid = 0;
			while(rs.next())
			lastid = rs.getInt(1);
			stmt.executeUpdate("insert into users values ("
			+(lastid+1)+","+fname+","+lname+","+uname+","
			+pword+")");  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void updateRow(int userid, String colname, String val) {
		
		val = "'"+val+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("update users set "+colname+" = "+val+" where user_id = "+userid);  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
	}
	
	public void deleteRow(int userid) {
				
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("delete from users where user_id = "+userid);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
	}
	
	public void displayAllRows() {
		
		String data[][] = new String[total_rows()][5];
		
		String column[] = new String[5];
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			//here redbus is database name, root is username and password is 2711 
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("describe users");
			int n = 0;
			while(rs.next()) {
				column[n] = rs.getString(1);
				n++;
			}
			
			n = 0;
			rs=stmt.executeQuery("select * from users");  
			while(rs.next()) {  
				for(int i=1;i<=5;i++) {
					data[n][i-1] = rs.getString(i);
				}
				n++;
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		} 
		
		JFrame f=new JFrame();
	    JTable jt=new JTable(data,column);    
	    jt.setBounds(100,100,100,100);          
	    JScrollPane sp=new JScrollPane(jt);    
	    f.add(sp);          
	    f.setSize(500,500);    
	    f.setVisible(true);
	    
	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	    
	}

}
