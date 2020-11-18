package adminKurjeris;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddOrder extends JFrame {

	private JPanel contentPane;
	private JTextField textDescription;
	private JTextField textAmount;
	private JTextField textCourierId;
	private JTextField textLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOrder frame = new AddOrder();
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
	public AddOrder() {
		setTitle("Naujas uzsakymas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textDescription = new JTextField();
		textDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textDescription.setBounds(10, 11, 495, 40);
		textDescription.setText("Uzsakymo pavadinimas");
		textDescription.setToolTipText("");
		contentPane.add(textDescription);
		textDescription.setColumns(10);
		
		textAmount = new JTextField();
		textAmount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAmount.setBounds(10, 113, 495, 40);
		textAmount.setText("Suma");
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		textCourierId = new JTextField();
		textCourierId.setBounds(413, 169, 100, 19);
		textCourierId.setText("Kurjerio ID");
		contentPane.add(textCourierId);
		textCourierId.setColumns(10);
		
		textLocation = new JTextField();
		textLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textLocation.setBounds(10, 62, 495, 40);
		textLocation.setText("Pristatymo vieta");
		contentPane.add(textLocation);
		textLocation.setColumns(10);
		
		JButton btnSave = new JButton("Issaugoti");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String description = textDescription.getText();
				Float amount = Float.parseFloat(textAmount.getText());
				String orderLocation = textLocation.getText();
				int courierId = Integer.parseInt(textCourierId.getText());
				
				Uzsakymas uzsakymas = new Uzsakymas();
				try {
					uzsakymas.addOrder(description, amount, orderLocation, courierId);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setBounds(10, 362, 180, 50);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("Uzdaryti");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(343, 362, 180, 50);
		contentPane.add(btnBack);
	}

}
