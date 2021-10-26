import java.io.IOException;
import java.sql.*;
import java.util.Scanner;  

public class Server{ 
	
	static Scanner sc = new Scanner(System.in);
	
	public static void bus_func() {
		System.out.println("**************************************************");
		Buses bus_table = new Buses();
		while(true) {
			System.out.println("You can do these operations to the buses table\n");
			System.out.println("1.Add Record\n2.Edit Record\n3.Delete Record\n4.Display all Records\n5.Quit\n");
			System.out.println("Select your choice:");
			int choice = sc.nextInt();
			if(choice==5) {
				break;
			}
			else if(choice==1) {
				bus_table.addRow();
			}
			else if(choice==2) {
				bus_table.updateRow();
			}
			else if(choice==3) {
				bus_table.deleteRow();
			}
			else if(choice==4) {
				bus_table.displayAllRows();
			}

			System.out.println("**************************************************");
		}
	}
	
	public static void user_func() {
		System.out.println("**************************************************");
		Users user_table = new Users();
		while(true) {
			System.out.println("You can do these operations to the users table\n");
			System.out.println("1.Add Record\n2.Edit Record\n3.Delete Record\n4.Display all Records\n5.Quit\n");
			System.out.println("Select your choice:");
			int choice = sc.nextInt();
			if(choice==5) {
				break;
			}
			else if(choice==1) {
				System.out.println("Enter first name:");
				String fname = sc.next();
				System.out.println("Enter last name:");
				String lname = sc.next();
				System.out.println("Enter username:");
				String uname = sc.next();
				System.out.println("Enter password:");
				String pword = sc.next();
				user_table.addRow(fname, lname, uname, pword);
				System.out.println("Record added");
			}
			else if(choice==2) {
				System.out.println("Enter User ID:");
				int userid = sc.nextInt();
				if(!user_table.userIDexists(userid)) {
					System.out.println("Sorry! That user ID does not exist");
					System.out.println("**************************************************");
					continue;
				}
				System.out.println("Enter the column name you wish to change:");
				String colname = sc.next();
				System.out.println("Enter updated value:");
				
				String val = sc.next();
				user_table.updateRow(userid, colname, val);
				System.out.println("User with ID="+userid+" updated");
			}
			else if(choice==3) {
				System.out.println("Enter User ID:");
				int userid = sc.nextInt();
				if(!user_table.userIDexists(userid)) {
					System.out.println("Sorry! That user ID does not exist");
					System.out.println("**************************************************");
					continue;
				}
				user_table.deleteRow(userid);
				System.out.println("User with ID="+userid+" deleted");
			}
			else if(choice==4) {
				user_table.displayAllRows();
			}

			System.out.println("**************************************************");
		}
	}
	
	public static void main(String args[]){
		
		while(true) {
			System.out.println("You can read from or write to any of these tables\n");
			System.out.println("1.Buses\n2.Users\n3.Quit\n");
			System.out.println("Select the table you wish to go to:");
			int choice = sc.nextInt();
			if(choice==3) {
				break;
			}
			else if(choice==1) {
				bus_func();
			}
			else if(choice==2) {
				user_func();
			}
			System.out.println("**************************************************");
		}
		
	}  
}  