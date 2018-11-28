package fr.ocr.sql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GarageConnection {

	private static String url = "jdbc:hsqldb:file:";
	private static String user = "SA";
	private static String passwd = "";
	private static Connection connect;

	private GarageConnection() {
	}

	public static Connection getInstance() {
		if (connect == null) {
			try {
				String path = null;
				path = retrievePath(path);
				loadDriver();
				connect = DriverManager.getConnection(url + path + "VEHICULE", user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

	private static void loadDriver() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
			e.printStackTrace();
			return;
		}
	}

	private static String retrievePath(String path) {
		try {
			path = new File(".").getCanonicalPath();
			path += "/hsqldb/database/";
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return path;
	}
}
