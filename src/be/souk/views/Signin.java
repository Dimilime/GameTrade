package be.souk.views;

import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import be.souk.models.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.time.LocalDate;
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
	
	
	public Signin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(52, 52, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome welcome = new Welcome();
				welcome.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(449, 10, 101, 39);
		contentPane.add(btnGoBack);
		
		lblErrorUserName = new JLabel("");
		lblErrorUserName.setForeground(new Color(255, 128, 128));
		lblErrorUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorUserName.setBounds(162, 199, 388, 25);
		contentPane.add(lblErrorUserName);
		
		lblErrorPassword = new JLabel("");
		lblErrorPassword.setForeground(new Color(255, 128, 128));
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
		txtfUserName.getDocument().addDocumentListener(signinListener);
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
				
				User user = User.getUser(username);
				
				
				if(user != null) {
					user.setPassword(password);
					if(user.login()) {
						if(user instanceof Admin a) {
							AdminInterface adminInterface = new AdminInterface(a);
							adminInterface.setVisible(true);
							dispose();
						}
						else if( user instanceof Player p) {
							p.addBirthdayBonus();
							p.setLastSeen(LocalDate.now());
							p.update();
							PlayerInterface playerInterface = new PlayerInterface(p);
							playerInterface.setVisible(true);
							dispose();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "The password is not correct","Error",JOptionPane.ERROR_MESSAGE);
						
				}
				else
					JOptionPane.showMessageDialog(null, "The username is not correct","Error",JOptionPane.ERROR_MESSAGE);
				
				
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
		passwordField.getDocument().addDocumentListener(signinListener);
		
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
	
	DocumentListener signinListener = new DocumentListener() {
		
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
			if(txtfUserName.getText().trim().length()>0)
				if(!(txtfUserName.getText().length() >=5 && txtfUserName.getText().length() <=30)) {
					lblErrorUserName.setText("Please enter a username of minimum 5 characters and maximum 30");
				}
				else
					lblErrorUserName.setText("");
			else
				lblErrorUserName.setText("");
			
			
			if(passwordField.getPassword().length>0)
				if(!(passwordField.getPassword().length >= 8 && passwordField.getPassword().length <=30)) {
					lblErrorPassword.setText("Please enter a password of minimum 8 characters and maximum 30");
				}
				else
					lblErrorPassword.setText("");
			else
				lblErrorPassword.setText("");
		}

		
	};
	
}
