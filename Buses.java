import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Buses {
	
/*	+----------------+-------------+------+-----+---------+-------+
	| Field          | Type        | Null | Key | Default | Extra |
	+----------------+-------------+------+-----+---------+-------+
	| bus_id         | tinyint(4)  | YES  |     | NULL    |       |
	| from_city      | varchar(20) | YES  |     | NULL    |       |
	| to_city        | varchar(20) | YES  |     | NULL    |       |
	| departure_time | time        | YES  |     | NULL    |       |
	| arrival_time   | time        | YES  |     | NULL    |       |
	| fare           | float       | YES  |     | NULL    |       |
	| rating         | float       | YES  |     | NULL    |       |
	| image          | varchar(20) | YES  |     | NULL    |       |
	+----------------+-------------+------+-----+---------+-------+
*/
	
	Scanner sc = new Scanner(System.in);
	
	public boolean busIDexists(int id) {
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from buses where bus_id = "+id);  
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
			ResultSet rs=stmt.executeQuery("select count(*) from buses"); 
			while(rs.next())
			ans = rs.getInt(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return ans;
	}
	
	public String[] uniq_values(String col) {
		
		ArrayList<String> cities = new ArrayList<String>();	
		int len = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct "+col+" from buses"); 
			while(rs.next()) {
			cities.add(rs.getString(1));
			len++;
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		String res[] = new String[len];
		
		for(int i=0;i<len;i++) {
			res[i] = cities.get(i);
		}
		
		return res;
		
	}
	
	public int[] getbusids(String from_city, String to_city, String from_time, String to_time, int fare) {
		
		from_city = "'"+from_city+"'";
		to_city = "'"+to_city+"'";
		from_time = "'"+from_time+"'";
		to_time = "'"+to_time+"'";
				
		ArrayList<Integer> ids = new ArrayList<Integer>();	
		int len = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select bus_id from buses where from_city = "+from_city+" and to_city = "+to_city+" and departure_time between "+from_time+" and "+to_time+" and fare <= "+fare); 
			while(rs.next()) {
			ids.add(rs.getInt(1));
			len++;
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
		int res[] = new int[len];
		
		for(int i=0;i<len;i++) {
			res[i] = ids.get(i);
		}
		
		return res;
		
	}
	
	public String[] getbusRow(int id) {
				
		String res[] = new String[7];
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from buses where bus_id = "+id); 
			while(rs.next()) {
			for(int i=2;i<=8;i++) {
				res[i-2] = rs.getString(i);
			}
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
		return res;
		
	}
	
	public void addRow() {
		
		System.out.println("Enter source city:");
		String from_city = "'"+sc.next()+"'";
		System.out.println("Enter destination city:");
		String to_city = "'"+sc.next()+"'";
		System.out.println("Enter departure time (hh:mm:ss):");
		String departure_time = "'"+sc.next()+"'";
		System.out.println("Enter arrival time (hh:mm:ss):");
		String arrival_time = "'"+sc.next()+"'";
		System.out.println("Enter fare (float):");
		float fare = sc.nextFloat();
		System.out.println("Enter image name (Eg. Image.jpg):");
		String image = "'"+sc.next()+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery("select bus_id from buses order by bus_id desc limit 1");
			int lastid = 0;
			while(rs.next())
			lastid = rs.getInt(1);
			stmt.executeUpdate("insert into buses values ("
			+(lastid+1)+","+from_city+","+to_city+","+departure_time+","
			+arrival_time+","+fare+","+0+","+image+")");  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("Record added");
		
	}
	
	public void updateRow() {
		
		System.out.println("Enter Bus ID:");
		int busid = sc.nextInt();
		if(!busIDexists(busid)) {
			System.out.println("Sorry! That bus ID does not exist");
			return;
		}
		System.out.println("Enter the column name you wish to change:");
		String colname = sc.next();
		System.out.println("Enter updated value:");
		
		String val;
		
		if(colname=="fare"||colname=="rating") {
			val = sc.next();
		}
		else {
			val = "'"+sc.next()+"'";
		}
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("update buses set "+colname+" = "+val+" where bus_id = "+busid);  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("Bus with ID="+busid+" updated");
		
	}
	
	public void deleteRow() {
		
		System.out.println("Enter Bus ID:");
		int busid = sc.nextInt();
		if(!busIDexists(busid)) {
			System.out.println("Sorry! That bus ID does not exist");
			return;
		}
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("delete from buses where bus_id = "+busid);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("Bus with ID="+busid+" deleted");
		
	}
	
	public void displayAllRows() {
		
		String data[][] = new String[total_rows()][8];
		
		String column[] = new String[8];
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			//here redbus is database name, root is username and password is 2711 
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("describe buses");
			int n = 0;
			while(rs.next()) {
				column[n] = rs.getString(1);
				n++;
			}
			
			n = 0;
			rs=stmt.executeQuery("select * from buses");  
			while(rs.next()) {  
				for(int i=1;i<=8;i++) {
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
