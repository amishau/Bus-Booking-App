import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Bookings {

/*	+----------------+---------+------+-----+---------+-------+
	| Field          | Type    | Null | Key | Default | Extra |
	+----------------+---------+------+-----+---------+-------+
	| user_id        | int(11) | YES  |     | NULL    |       |
	| bus_id         | int(11) | YES  |     | NULL    |       |
	| seat_number    | int(11) | YES  |     | NULL    |       |
	| departure_date | date    | YES  |     | NULL    |       |
	+----------------+---------+------+-----+---------+-------+
*/
	
	Scanner sc = new Scanner(System.in);
	
	public boolean bookingexists(int userid, int busid) {
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from bookings where user_id = "+userid+" and bus_id = "+busid);  
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
	
	public HashSet<Integer> bookedseats(int busid, String date) {
		
		int len = 0;
		
		HashSet<Integer> res = new HashSet<>();
		
		date = "'"+date+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select seat_number from bookings where bus_id = "+busid+" and departure_date = "+date);  
			while(rs.next()) {
				res.add(rs.getInt(1));
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return res;
		
	}
	
	public String[][] getbookings(int user_id){
		
		int len = 0;
		
		ArrayList<String> busid = new ArrayList<String>();
		ArrayList<String> seatno = new ArrayList<String>();
		ArrayList<String> depdate = new ArrayList<String>();
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select bus_id, seat_number, departure_date from bookings where user_id = "+user_id);  
			while(rs.next()) {
				busid.add(rs.getString(1));
				seatno.add(rs.getString(2));
				depdate.add(rs.getString(3));
				len++;
			}
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		String res[][] = new String[len][3];
		
		for(int i=0;i<len;i++) {
			res[i][0] = busid.get(i);
			res[i][1] = seatno.get(i);
			res[i][2] = depdate.get(i);
		}
		
		return res;
		
	}
	
	public int total_rows() {
		
		int ans = 0;
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select count(*) from bookings"); 
			while(rs.next())
			ans = rs.getInt(1);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
		return ans;
	}
	
	public void addRow(int userid, int busid, int sno, String depdate) {
		
		depdate = "'"+depdate+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();
			
			stmt.executeUpdate("insert into bookings values ("
			+userid+","+busid+","+sno+","+depdate+")");  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public void updateRow(int userid, int busid, int sno, String depdate) {
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("update bookings set seat_number = "+sno+", departure_date = "+depdate+" where user_id = "+userid+" and bus_id = "+busid);  
			
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
	}
	
	public void deleteRow(int userid, int busid, int sno, String depdate) {
			
		depdate = "'"+depdate+"'";
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("delete from bookings where user_id = "+userid+" and bus_id = "+busid+" and seat_number = "+sno+" and departure_date = "+depdate);
			con.close();  
		}
		
		catch(Exception e){
			System.out.println(e);
		}
				
	}
	
	public void displayAllRows() {
		
		String data[][] = new String[total_rows()][4];
		
		String column[] = new String[4];
		
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/redbus","root","2711");  
			//here redbus is database name, root is username and password is 2711 
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("describe ratings_and_reviews");
			int n = 0;
			while(rs.next()) {
				column[n] = rs.getString(1);
				n++;
			}
			
			n = 0;
			rs=stmt.executeQuery("select * from ratings_and_reviews");  
			while(rs.next()) {  
				for(int i=1;i<=4;i++) {
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
