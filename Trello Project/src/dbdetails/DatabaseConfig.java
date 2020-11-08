package dbdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
	public static String jdbcurl="jdbc:mysql://localhost:3306/workhandler";
	public static String jdbcusername="root";
	public static String jdbcpassword="mysql";
	
	public static Connection con ;
	public static Statement stmt;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static void initialize() throws SQLException	{	
		con= DriverManager.getConnection(jdbcurl,jdbcusername, jdbcpassword);
		stmt=con.createStatement();		
	}
}
