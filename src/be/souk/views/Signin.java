package be.souk.views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DateFormatter;

import be.souk.models.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4353208898181143528L;
	private JPanel contentPane;
	private Image iconSignup;
	private JLabel lblIconSignup;
	private JLabel lblUserName;
	private JTextField txtfUserName;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblErrorUserName;
	private JLabel lblErrorPassword;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signin frame = new Signin();
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
	public Signin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(52, 52, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblErrorUserName = new JLabel("");
		lblErrorUserName.setForeground(Color.RED);
		lblErrorUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorUserName.setBounds(162, 199, 388, 25);
		contentPane.add(lblErrorUserName);
		
		lblErrorPassword = new JLabel("");
		lblErrorPassword.setForeground(Color.RED);
		lblErrorPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorPassword.setBounds(162, 259, 388, 22);
		contentPane.add(lblErrorPassword);
		
		lblIconSignup = new JLabel("");
		lblIconSignup.setBounds(234, 29, 139, 114);
		iconSignup = new ImageIcon(Welcome.class.getResource("/ressources/user-profile.png")).getImage().getScaledInstance(lblIconSignup.getWidth(), lblIconSignup.getHeight(), Image.SCALE_SMOOTH);
		lblIconSignup.setIcon(new ImageIcon(iconSignup));
		contentPane.add(lblIconSignup);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setBounds(31, 162, 121, 25);
		contentPane.add(lblUserName);
		
		txtfUserName = new JTextField();
		txtfUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtfUserName.setColumns(10);
		txtfUserName.setBounds(162, 164, 287, 25);
		txtfUserName.getDocument().addDocumentListener(signupListener);
		contentPane.add(txtfUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(31, 228, 121, 25);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {

				String  username, password;
				
				username = txtfUserName.getText();
				password = String.valueOf(passwordField.getPassword());

				System.out.println(username + " " + password );

				User user = User.getUser(username);
				user.setPassword(password);
				
				if(user != null && user.checkUserPassword()) {
					
					if(user instanceof Admin a) {
						System.out.println("Admin connected");
					}
					else if( user instanceof Player p) {
						System.out.println("Player connected");
					}
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "The username or the password is not correct","Error",JOptionPane.ERROR_MESSAGE);
				
				
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(162, 288, 287, 39);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(162, 228, 287, 25);
		contentPane.add(passwordField);
		passwordField.getDocument().addDocumentListener(signupListener);
		
		char defaultt = passwordField.getEchoChar();
		JCheckBox chckbxShowPwd = new JCheckBox("Show password");
		chckbxShowPwd.setBackground(new Color(52, 52, 52));
		chckbxShowPwd.setForeground(new Color(255, 255, 255));
		chckbxShowPwd.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.DESELECTED) {
					 passwordField.setEchoChar(defaultt);
			        } else {
			        	passwordField.setEchoChar((char) 0);
			        }
			}
		});
		chckbxShowPwd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxShowPwd.setBounds(449, 228, 105, 25);
		contentPane.add(chckbxShowPwd);

		
	}
	
	DocumentListener signupListener = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			warn();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			warn();
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			warn();
			
		}

		public void warn() {
			if(!(txtfUserName.getText().length() >=5 && txtfUserName.getText().length() <=30)) {
				lblErrorUserName.setText("Please enter a username of minimum 5 characters and maximum 30");
			}
			else
				lblErrorUserName.setText("");
			
			
			if(!(passwordField.getPassword().length >= 8 && passwordField.getPassword().length <=30)) {
				lblErrorPassword.setText("Please enter a password of minimum 8 characters and maximum 30");
			}
			else
				lblErrorPassword.setText("");
		}

		
	};
	
}
