package club.dao.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String dbUrl = "jdbc:mysql://localhost:3306/club";
	private static final String dbUserName = "root";
	private static final String dbPassword = "password";
	
	private static final String dbDriver = "com.mysql.jdbc.Driver";

	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return conn;
	}

}
