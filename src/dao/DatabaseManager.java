package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	private static String DB_URL = "jdbc:mysql://localhost:3306/db_esp";
	private static String USER = "root";
	private static String DB_PASSWORD = "";

	private static Connection connection;

	private DatabaseManager() {

	}

	public static Connection getConnection() throws DaoException {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL, USER, DB_PASSWORD);
				return connection;
			} catch (ClassNotFoundException c) {
				throw new DaoException("Missing driver : " + c.getMessage());
			} catch (Exception e) {
				throw new DaoException("Error while trying to connect to the database : " + e.getMessage());
			}
		}
		return connection;
	}
}
