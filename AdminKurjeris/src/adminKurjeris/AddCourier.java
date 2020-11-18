package adminKurjeris;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddCourier extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textLastname;
	private JTextField textEmail;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourier frame = new AddCourier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddCourier() {
		setTitle("Naujas vairuotojas");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNew = new JLabel("Vardas");
		lblNew.setBounds(65, 41, 303, 20);
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNew);

		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.BOLD, 18));
		textName.setBounds(65, 60, 396, 43);
		contentPane.add(textName);
		textName.setColumns(10);

		textLastname = new JTextField();
		textLastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		textLastname.setBounds(65, 146, 396, 43);
		contentPane.add(textLastname);
		textLastname.setColumns(10);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textEmail.setBounds(65, 237, 396, 43);
		textEmail.setToolTipText("example@exa.com");
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		JButton btnSave = new JButton("Issaugoti");
		btnSave.setBounds(65, 302, 180, 50);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Kurjeris kurjeris = new Kurjeris();
				SendEmail sendEmail = new SendEmail();
				CheckEmail checkEmail = new CheckEmail();

				String name = textName.getText();
				String lastName = textLastname.getText();
				String email = textEmail.getText();

				if (!checkEmail.emailIsValid(email) == false) {

					try {
						kurjeris.addCourier(name, lastName, email);
						sendEmail.sendEmail(email, name, lastName);
						dispose();
					} catch (SQLException | MessagingException | IOException e3) {
						e3.printStackTrace();
					}
				} else {
				}
			}
		});
		contentPane.add(btnSave);

		JButton btnBack = new JButton("Uzdaryti");
		btnBack.setBounds(282, 302, 180, 50);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnBack);

		JLabel lblPavarde = new JLabel("Pavarde");
		lblPavarde.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPavarde.setBounds(65, 127, 303, 20);
		contentPane.add(lblPavarde);

		JLabel lblNew_1_1 = new JLabel("Elektroninis pastas");
		lblNew_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNew_1_1.setBounds(65, 217, 303, 20);
		contentPane.add(lblNew_1_1);
		
		JLabel idWrongMessage = new JLabel("");
		idWrongMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idWrongMessage.setBounds(142, 192, 238, 34);
		contentPane.add(idWrongMessage);
	}
}
