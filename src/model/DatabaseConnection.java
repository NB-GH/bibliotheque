package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost/bibliotheque";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException("Driver MySQL introuvable", e);
			}
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		return connection;
	};
	
	public static void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
