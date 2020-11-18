package adminKurjeris;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Uzsakymas {
	static Connection conn = Prisijungimas.getConnection();
	Scanner scan = new Scanner(System.in);

	private int idOrders; 
	private String description;
	private float amount;
	private boolean status;
	private String deliveryDate; 
	private String orderLocation;

	public Uzsakymas() {

	}

	public int getIdOrder() {
		return idOrders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	private static int getLastOrder(Connection conn) throws SQLException {

		int idOrders = 1;
		String sql = "SELECT max(idOrders) + 1 AS generatedId FROM orders;";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		if (rs.next()) {
			idOrders = rs.getInt("generatedId");
		}
		return idOrders;
	}

	public void addOrder(String description, Float amount, String orderLocation, int courierId) throws SQLException {

		this.idOrders = getLastOrder(conn);

		String sql = "INSERT INTO orders(idOrders, description,amount,status, orderLocation, courierId) VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			pstm.setInt(1, idOrders);
			pstm.setString(2, description);
			pstm.setFloat(3, amount);
			pstm.setInt(4, 0);
			pstm.setString(5, orderLocation);
			pstm.setInt(6, courierId);
			pstm.executeUpdate();
			System.out.println("Prideta sekmingai");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet selectAllOrders() throws SQLException {

		String sql = "SELECT idOrders, description, amount, orderLocation, status, courierId FROM orders;";

		
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
		return rs;
	}

	public ResultSet selectAllOrdersNotDone() throws SQLException {

		String sql = "SELECT idOrders, description, amount, orderLocation, status, courierId FROM orders WHERE status = 0";
				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			return rs;

	}
	
	public ResultSet selectAllOrdersDone() throws SQLException {

		String sql = "SELECT idOrders, description, amount, orderLocation, status, courierId FROM orders WHERE status = 1 ";
				

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

		return rs;
	}

	public ResultSet selectAllOrdersByCourier(int txtCourierID) throws SQLException {

		String sql = "SELECT idOrders, description, amount, orderLocation, status FROM orders WHERE courierId = "
				+ txtCourierID;
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			return rs;
	
		}
	
	}
	
	

