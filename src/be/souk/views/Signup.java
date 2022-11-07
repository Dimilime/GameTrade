package be.souk.views;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DateFormatter;

import be.souk.models.Player;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4353208898181143528L;
	private JPanel contentPane;
	private Image iconSignup;
	private JTextField txtfPseudo;
	private JLabel lblIconSignup;
	private JLabel lblDoB;
	private JLabel lblUserName;
	private JTextField txtfUserName;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JFormattedTextField dateField;
	private JLabel lblErrorDoB;
	private JLabel lblErrorPseudo;
	private JLabel lblErrorUserName;
	private JLabel lblErrorPassword;
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormatter df = new DateFormatter(format);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(52, 52, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPseudo.setForeground(new Color(255, 255, 255));
		lblPseudo.setBounds(35, 130, 121, 25);
		contentPane.add(lblPseudo);
		
		lblErrorPseudo = new JLabel("");
		lblErrorPseudo.setForeground(new Color(255, 128, 128));
		lblErrorPseudo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorPseudo.setBounds(166, 162, 388, 25);
		contentPane.add(lblErrorPseudo);
		
		lblErrorUserName = new JLabel("");
		lblErrorUserName.setForeground(new Color(255, 128, 128));
		lblErrorUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorUserName.setBounds(166, 281, 388, 30);
		contentPane.add(lblErrorUserName);
		
		lblErrorPassword = new JLabel("");
		lblErrorPassword.setForeground(new Color(255, 128, 128));
		lblErrorPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorPassword.setBounds(166, 348, 388, 25);
		contentPane.add(lblErrorPassword);
		
		lblErrorDoB = new JLabel("");
		lblErrorDoB.setForeground(new Color(255, 128, 128));
		lblErrorDoB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorDoB.setBounds(166, 218, 388, 25);
		contentPane.add(lblErrorDoB);
		
		txtfPseudo = new JTextField();
		txtfPseudo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtfPseudo.setBounds(166, 132, 287, 25);
		contentPane.add(txtfPseudo);
		txtfPseudo.setColumns(10);
		txtfPseudo.getDocument().addDocumentListener(signupListener);
		
		lblIconSignup = new JLabel("");
		lblIconSignup.setBounds(234, 29, 139, 85);
		iconSignup = new ImageIcon(Welcome.class.getResource("/ressources/add-user.png")).getImage().getScaledInstance(lblIconSignup.getWidth(), lblIconSignup.getHeight(), Image.SCALE_SMOOTH);
		lblIconSignup.setIcon(new ImageIcon(iconSignup));
		contentPane.add(lblIconSignup);
		
		lblDoB = new JLabel("Date of Birth");
		lblDoB.setForeground(Color.WHITE);
		lblDoB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoB.setBounds(35, 187, 121, 25);
		contentPane.add(lblDoB);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setBounds(35, 251, 121, 25);
		contentPane.add(lblUserName);
		
		txtfUserName = new JTextField();
		txtfUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtfUserName.setColumns(10);
		txtfUserName.setBounds(166, 253, 287, 25);
		txtfUserName.getDocument().addDocumentListener(signupListener);
		contentPane.add(txtfUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(35, 317, 121, 25);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {

				String pseudo, username, password, dob;

				pseudo = txtfPseudo.getText();
				username = txtfUserName.getText();
				dob = dateField.getText();
				password = String.valueOf(passwordField.getPassword());
				

				Player p = new Player();
				p.setPseudo(pseudo);
				p.setUserName(username);
				p.setPassword(password);
				p.setRegistrationDate(new Timestamp(new Date().getTime()) );
				p.setCredit(10);
				try {
					p.setDateOfBirth(format.parse(dob));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if(!p.exists())
				{
					p.signUp();
					JOptionPane.showMessageDialog(Signup.this, "You have successfully registered!");
					dateField.setValue(new Date());
					txtfPseudo.setText("");
					txtfUserName.setText("");
					passwordField.setText("");
					//dispose();
				}
				else {
					JOptionPane.showMessageDialog(Signup.this, "The username already exists","Error", JOptionPane.ERROR_MESSAGE);
				}
					
				
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(166, 377, 287, 39);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(166, 317, 287, 25);
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
		chckbxShowPwd.setBounds(453, 317, 105, 25);
		contentPane.add(chckbxShowPwd);
		
		dateField = new JFormattedTextField(df);
		dateField.getDocument().addDocumentListener(signupListener);
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateField.setBounds(166, 187, 287, 25);
		dateField.setValue(new Date());
		contentPane.add(dateField);

		
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
			if (!dateField.getText().matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
				lblErrorDoB.setText("Error: Please enter date as dd/mm/yyyy format"); 
			}else
				lblErrorDoB.setText(""); 
			
			
			if(!(txtfUserName.getText().length() >=5 && txtfUserName.getText().length() <=30)) {
				lblErrorUserName.setText("Please enter a username of minimum 5 characters and maximum 30");
			}
			else
				lblErrorUserName.setText("");
			
			if(!(txtfPseudo.getText().length() >=5 && txtfPseudo.getText().length() <=30)) {
				lblErrorPseudo.setText("Please enter a pseudo of minimum 5 characters and maximum 30");
			}
			else
				lblErrorPseudo.setText("");
			
			if(!(passwordField.getPassword().length >= 8 && passwordField.getPassword().length <=30)) {
				lblErrorPassword.setText("Please enter a password of minimum 8 characters and maximum 30");
			}
			else
				lblErrorPassword.setText("");
		}

		
	};
	
}
