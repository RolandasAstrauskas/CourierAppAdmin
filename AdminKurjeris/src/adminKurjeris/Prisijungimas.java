package adminKurjeris;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Prisijungimas {
	private static Connection conn;

	public static Connection getConnection() {

		if (conn == null) {
			try {
				FileInputStream in = new FileInputStream("properties");
				Properties prop = new Properties();
				prop.load(in);

				String url = prop.getProperty("db.url");
				String username = prop.getProperty("db.username");
				String password = prop.getProperty("db.password");

				conn = DriverManager.getConnection(url, username, password);
				System.out.println("Pavyko");

			} catch (Exception e) {

				System.out.println("Neveikia " + e.getMessage());
			}
		}

		return conn;
	}

}
