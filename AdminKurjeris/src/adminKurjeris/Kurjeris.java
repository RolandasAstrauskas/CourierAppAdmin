package adminKurjeris;

import java.sql.*;
import java.util.*;

public class Kurjeris {
	static Connection conn = Prisijungimas.getConnection();
	Scanner scan = new Scanner(System.in);

	private int idCourier;
	private String name;
	private String lastName;
	private String password = "";
	private String userName;
	public static String password2;

	public String getUserName() {
		return userName;
	}

	public static String getPassword2() {
		return password2;
	}

	public static void setPassword2(String password2) {
		Kurjeris.password2 = password2;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	float orderAmount;

	public int getIdCourier() {
		return idCourier;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	private static int getLastCourierId(Connection conn) throws SQLException {

		int idCourier = 1;
		String sql = "SELECT max(idCourier) + 1 AS generatedId FROM courier;";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			idCourier = rs.getInt("generatedId");
		}
		return idCourier;
	}

	private String passwordGeneration() {

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			char symbol = (char) ('a' + random.nextInt(26));
			int number = random.nextInt(9 - 0);
			password = password + symbol + number;
		}
		return password;
	}

	public  String addCourier(String name, String lastName, String email) throws SQLException {

		int idCourier = getLastCourierId(conn);
		userName = name.substring(0, 2).concat(lastName);
		password2 = passwordGeneration();

		String sql = "INSERT INTO courier(idCourier, name, lastName, email, password, userName) VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setInt(1, idCourier);
			pstm.setString(2, name);
			pstm.setString(3, lastName);
			pstm.setString(4, email);
			pstm.setString(5, password2);
			pstm.setString(6, userName);
			pstm.executeUpdate();
			System.out.println("Prideti pavyko");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password2;
	}

	public void deleteCourier(int idCourier) {

		String sql = "DELETE FROM courier WHERE idCourier = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idCourier);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet selectAllCouriers() throws SQLException {

		String sql = "SELECT idCourier, name, lastName, email, userName, password FROM courier";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		return rs;
	}

//neveikia
	public void checkCourierOrderStatus() {
		System.out.println("Iveskite kurjerio Id norint gauti jo  uzsakymus");
		int idCourier = scan.nextInt();
		System.out.println("Iveskite 1 - pristatytiems arba 0 nepristatytoms siuntoms pamatyti");
		int status = scan.nextInt();

		String sql = "SELECT name, lastName, description, amount, orderLocation FROM orders "
				+ "JOIN courier cr ON o.courierId = cr.idCourier WHERE idCourier = ? AND status = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idCourier);
			pstmt.setInt(2, status);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("o.description") + "\t" + rs.getString("o.orderLocation"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//TODO papildyti metoda datos intervalo pasirinkimu
	public void checkCourierDeliveredAllByDate() {
		System.out.println("Iveskite kurjerio Id norint gauti jo uzsakymu suma");
		int idCourier = scan.nextInt();

		// TODO:
		String sql = "SELECT name, lastName, amount FROM orders o "
				+ "JOIN courier cr ON o.courierId = cr.idCourier WHERE idCourier = " + idCourier + " AND status = 0";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, idCourier);

			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				float amountOrder = rs.getFloat("amount");
				orderAmount = amountOrder + rs.getFloat("amount");
			}
			System.out.println(orderAmount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}