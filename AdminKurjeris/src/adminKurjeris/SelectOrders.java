package adminKurjeris;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SelectOrders extends JFrame {

	private JPanel contentPane;
	private JTextField txtCourierID;

	SelectOrder selectOrder = new SelectOrder();
	Uzsakymas uzsakymas = new Uzsakymas();
	Kurjeris kurjeris = new Kurjeris();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectOrders frame = new SelectOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectOrders() {
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UZSAKYMU PERZIURA");
		lblNewLabel.setBounds(0, 0, 0, 0);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		JButton btnAllOrder = new JButton("Visi uzsakymai");
		btnAllOrder.setBounds(67, 38, 155, 21);
		btnAllOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					selectOrder.getTblOrder().setModel(DbUtils.resultSetToTableModel(uzsakymas.selectAllOrders()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				selectOrder.setVisible(true);
			}
		});
		contentPane.add(btnAllOrder);
		
		JButton btnDeliveredOrders = new JButton("Atlikti uzsakymai");
		btnDeliveredOrders.setBounds(67, 69, 155, 21);
		btnDeliveredOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					selectOrder.getTblOrder().setModel(DbUtils.resultSetToTableModel(uzsakymas.selectAllOrdersDone()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				selectOrder.setVisible(true);
			}
		});
		contentPane.add(btnDeliveredOrders);
		
		JButton btnNotDeliveredOrder = new JButton("Neatlikti uzsakymai");
		btnNotDeliveredOrder.setBounds(67, 100, 155, 21);
		btnNotDeliveredOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					selectOrder.getTblOrder().setModel(DbUtils.resultSetToTableModel(uzsakymas.selectAllOrdersNotDone()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				selectOrder.setVisible(true);
			}
		});
		contentPane.add(btnNotDeliveredOrder);
		
		JButton btnOrderByCourierId = new JButton("Uzsakymai pagl kurjerio ID");
		btnOrderByCourierId.setBounds(67, 169, 204, 21);
		btnOrderByCourierId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SelectOrder selectOrder = new SelectOrder();
				
				Uzsakymas uzsakymas1 = new Uzsakymas();
				
				int courierId = Integer.parseInt(txtCourierID.getText());
				ResultSet rs;
				try {
					rs = uzsakymas1.selectAllOrdersByCourier(courierId);
					selectOrder.getTblOrder().setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				selectOrder.setVisible(true);
			}
		});
		contentPane.add(btnOrderByCourierId);
		
		txtCourierID = new JTextField();
		txtCourierID.setBounds(281, 170, 64, 19);
		txtCourierID.setText("Ivesti ID");
		contentPane.add(txtCourierID);
		txtCourierID.setColumns(10);
		
		JButton btnBack = new JButton("Uzdaryti");
		btnBack.setBounds(233, 235, 83, 21);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnBack);
		
		
}
}