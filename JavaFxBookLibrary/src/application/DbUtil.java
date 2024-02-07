package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	Connection connection;
	String url = "jdbc:mysql://localhost/db";
	String username = "root";
	String password = "";
	
	public Connection getConnection() {
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			
		}catch(Exception e) {
			System.out.println("Error connection to database " + e.getMessage());
			return null;
		}
		return connection;
		
	}

}
