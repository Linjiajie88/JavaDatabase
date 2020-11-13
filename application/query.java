package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class query {
	public void test() {
		System.out.println("Hello World");
	    Connection conn = null;
	    Statement stmt = null;
	    CallableStatement cStmt;
	    ResultSet rs = null;
	    int numRowsModified = 0;
	    String password;
	    String connectionString = "jdbc:mysql://localhost:3306?" +
	                                       "user=root&password=";

	    try {
			//System.out.print("Enter password: ");
			//Scanner input = new Scanner(System.in);
			password = "Qq532797625";
			//password = input.nextLine();
			connectionString = connectionString + password;

	        conn =
	           DriverManager.getConnection(connectionString);

	        // Do something with the Connection
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("SHOW Databases;");
	        stmt.executeQuery("USE NBA;");
	        //numRowsModified = stmt.executeUpdate("insert into Student VALUES(70000, 'Amelia', 'Finance', 1);");
			//System.out.println(numRowsModified + " rows inserted.");

	        rs = stmt.executeQuery("SELECT PlayerName,Age,G,GS FROM nbanew where Playername = 'James Harden';");
	        //rs = stmt.executeQuery("SHOW Databases;");

			System.out.println("Students\n ID\tName\tdept_name\tcred_hours");
			
	        while(rs.next())
	        {
	        	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
	        }
	    

		
		


    } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
	
    

}
