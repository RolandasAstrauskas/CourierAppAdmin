package adminKurjeris;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;

public class AdminKurjerisLangas extends JFrame {

	private JPanel contentPane;

	int xx, xy;

	Kurjeris kurjeris = new Kurjeris();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminKurjerisLangas frame = new AdminKurjerisLangas();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminKurjerisLangas() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();

			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				AdminKurjerisLangas.this.setLocation(x - xx, y - xy);

			}
		});
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(Color.BLACK, 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddOrder = new JButton("UZSAKYMO PRIDEJIMAS");
		btnAddOrder.setForeground(Color.WHITE);
		btnAddOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddOrder.setBounds(352, 116, 304, 40);
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddOrder addOrder = new AddOrder();
				addOrder.setVisible(true);
			}
		});
		btnAddOrder.setBackground(Color.BLACK);
		contentPane.add(btnAddOrder);

		JButton btnAddCourier = new JButton("VAIRUOTOJO REGISTRACIJA");
		btnAddCourier.setBackground(Color.BLACK);
		btnAddCourier.setForeground(Color.WHITE);
		btnAddCourier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddCourier.setBounds(352, 166, 304, 40);
		btnAddCourier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourier addCourier = new AddCourier();
				addCourier.setVisible(true);
			}
		});
		contentPane.add(btnAddCourier);

		JButton btnViewOrder = new JButton("UZSAKYMU PERZIURA");
		btnViewOrder.setBackground(Color.BLACK);
		btnViewOrder.setForeground(Color.WHITE);
		btnViewOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewOrder.setBounds(352, 216, 304, 40);
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectOrders selectOrders = new SelectOrders();
				selectOrders.setVisible(true);

			}
		});
		contentPane.add(btnViewOrder);

		JButton btnViewCourier = new JButton("VAIRUOTOJU PERZIURA");
		btnViewCourier.setBackground(Color.BLACK);
		btnViewCourier.setForeground(Color.WHITE);
		btnViewCourier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewCourier.setBounds(352, 266, 304, 40);
		btnViewCourier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListOfCourier listOfCourier = new ListOfCourier();

				try {
					listOfCourier.getTblCourier().setModel(DbUtils.resultSetToTableModel(kurjeris.selectAllCouriers()));

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				listOfCourier.setVisible(true);
			}
		});
		contentPane.add(btnViewCourier);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 336, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-245, 0, 581, 411);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(
				AdminKurjerisLangas.class.getResource("/Image/hand-man-using-laptop-computer-dark_141345-69.jpg")));
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.exit(JFrame.EXIT_ON_CLOSE);

			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(686, 0, 10, 22);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("_");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				setState(AdminKurjerisLangas.ICONIFIED);

			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(660, 0, 20, 22);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Administratorius");
		lblNewLabel.setBounds(423, 10, 182, 48);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
}
