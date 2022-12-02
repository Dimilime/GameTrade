package be.souk.views;

import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import be.souk.models.Player;
import be.souk.models.User;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

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
	private JButton btnSignup;
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
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
		lblPseudo.setLabelFor(txtfPseudo);
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
		lblUserName.setLabelFor(txtfUserName);
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
		
		btnSignup = new JButton("Sign up");
		btnSignup.addActionListener(new ActionListener() 
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
				p.setRegistrationDate(LocalDate.now());
				p.setCredit(10);
				p.setDateOfBirth( LocalDate.parse(dob, formatter) );
				p.setLastSeen(LocalDate.now());
				if(User.getUser(username) == null)
				{
					if (p.signUp()) {
						p.addBirthdayBonus();
						p.update();
						JOptionPane.showMessageDialog(Signup.this, "You have successfully registered!");
					}
						
					dateField.setText("");
					txtfPseudo.setText("");
					txtfUserName.setText("");
					passwordField.setText("");
					
					Signin signin = new Signin();
					signin.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(Signup.this, "The username already exists","Error", JOptionPane.ERROR_MESSAGE);
				}
					
				
			}

		});
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSignup.setBackground(new Color(192, 192, 192));
		btnSignup.setBounds(166, 377, 287, 39);
		contentPane.add(btnSignup);
		
		passwordField = new JPasswordField();
		lblPassword.setLabelFor(passwordField);
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
		
		dateField = new JFormattedTextField(format);
		lblDoB.setLabelFor(dateField);
		dateField.getDocument().addDocumentListener(signupListener);
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateField.setBounds(166, 187, 287, 25);
		contentPane.add(dateField);
		
		JButton btnGoBack = new JButton("GO BACK");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome welcome = new Welcome();
				welcome.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(449, 10, 105, 39);
		contentPane.add(btnGoBack);
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
			if(dateField.getText().trim().length()>0)
				if (!dateField.getText().matches("^(0[1-9]|[12][0-9]|3[01])[\\/.](0[1-9]|1[012])[\\/.](19|20)\\d\\d$")) {
					lblErrorDoB.setText("Error: Please enter a valid date as dd/MM/yyyy format"); 
				}else if( Period.between(LocalDate.parse(dateField.getText(),formatter),LocalDate.now()).getYears() < 16 )//if he is older than 16
					lblErrorDoB.setText("Error: You have to be 16 years old"); 
				else
					lblErrorDoB.setText("");
			else
				lblErrorDoB.setText(""); 
			
			if(txtfUserName.getText().trim().length()>0)
				if(!(txtfUserName.getText().length() >=5 && txtfUserName.getText().length() <=30)) {
					lblErrorUserName.setText("Please enter a username of minimum 5 characters and maximum 30");
				}
				else
					lblErrorUserName.setText("");
			else
				lblErrorUserName.setText("");
			
			if(txtfPseudo.getText().trim().length()>0)
				if(!(txtfPseudo.getText().length() >=5 && txtfPseudo.getText().length() <=30)) {
					lblErrorPseudo.setText("Please enter a pseudo of minimum 5 characters and maximum 30");
				}
				else
					lblErrorPseudo.setText("");
			else
				lblErrorPseudo.setText("");
			
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
