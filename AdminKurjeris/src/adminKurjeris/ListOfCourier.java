package adminKurjeris;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class ListOfCourier extends JFrame {

	private JPanel contentPane;
	private JTable tblCourier;
	private JTextField textCourierId;
	
	public JTable getTblCourier() {
		return tblCourier;
	}

	/**
	 * Launch the application.
	 */
	
	Kurjeris kurjeris = new Kurjeris();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListOfCourier frame = new ListOfCourier();
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
	public ListOfCourier() {
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 802, 357);
		contentPane.add(scrollPane);
		
		tblCourier = new JTable();
		scrollPane.setViewportView(tblCourier);
		
		JButton btnDeleteCourier = new JButton("Istrinti");
		btnDeleteCourier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(textCourierId.getText());
				
				kurjeris.deleteCourier(id);
				
				try {
					tblCourier.setModel(DbUtils.resultSetToTableModel(kurjeris.selectAllCouriers()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDeleteCourier.setBounds(21, 498, 85, 21);
		contentPane.add(btnDeleteCourier);
		
		JButton btnBack = new JButton("Uzdaryti");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(727, 498, 85, 21);
		contentPane.add(btnBack);
		
		textCourierId = new JTextField();
		textCourierId.setBounds(10, 455, 96, 19);
		contentPane.add(textCourierId);
		textCourierId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Irasyti kurjerio ID");
		lblNewLabel.setBounds(10, 428, 127, 13);
		contentPane.add(lblNewLabel);
	}
}
