package be.souk.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Welcome extends JFrame {

	private static final long serialVersionUID = -1808111470969229491L;
	private JPanel contentPane;
	private JLabel lblBackGroundImg;
	private Image img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setTitle("Game Trade");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setBackground(new Color(192, 192, 192));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Signin signin = new Signin();
				signin.setVisible(true);
				dispose();
			}
		});
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignIn.setBounds(120, 266, 155, 33);
		contentPane.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Signup signup = new Signup();
				signup.setVisible(true);
				dispose();
			}
		});
		btnSignUp.setBackground(new Color(192, 192, 192));
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignUp.setBounds(334, 166, 155, 33);
		contentPane.add(btnSignUp);
		
		lblBackGroundImg = new JLabel("");
		lblBackGroundImg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBackGroundImg.setBounds(0, 0, this.getWidth(), this.getHeight());
		img = new ImageIcon(Welcome.class.getResource("/ressources/gameTrade.jfif")).getImage().getScaledInstance(lblBackGroundImg.getWidth(), lblBackGroundImg.getHeight(), Image.SCALE_SMOOTH);
		lblBackGroundImg.setIcon(new ImageIcon(img));
		contentPane.add(lblBackGroundImg);
		
	}
}
