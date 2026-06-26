package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connexion à la base de données MySQL
 */
public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost/bibliotheque";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private static Connection connection = null;

	/**
	 * Retourne une connexion active à la base de données
	 * 
	 * @return connexion à la base de données
	 * @throws SQLException erreur lors de la connexion
	 */
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

	/**
	 * Ferme la connexion à la base de données
	 * 
	 * @throws SQLException erreur lors de la fermeture
	 */
	public static void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
