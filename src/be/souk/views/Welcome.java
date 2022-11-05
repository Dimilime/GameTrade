package be.souk.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(120, 266, 155, 33);
		contentPane.add(btnNewButton);
		
		JButton btnSignIn = new JButton("Sign up");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Signup signup = new Signup();
				signup.setVisible(true);
				dispose();
			}
		});
		btnSignIn.setBackground(new Color(192, 192, 192));
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignIn.setBounds(334, 166, 155, 33);
		contentPane.add(btnSignIn);
		
		lblBackGroundImg = new JLabel("");
		lblBackGroundImg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBackGroundImg.setBounds(0, 0, this.getWidth(), this.getHeight());
		img = new ImageIcon(Welcome.class.getResource("/ressources/gameTrade.jfif")).getImage().getScaledInstance(lblBackGroundImg.getWidth(), lblBackGroundImg.getHeight(), Image.SCALE_SMOOTH);
		lblBackGroundImg.setIcon(new ImageIcon(img));
		contentPane.add(lblBackGroundImg);
		
	}
}
