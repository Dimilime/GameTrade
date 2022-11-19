package be.souk.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.souk.models.Copy;
import be.souk.models.Player;
import be.souk.models.VideoGame;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerInterface extends JFrame {

	private static final long serialVersionUID = 7544019862560332915L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable tableVideoGames;
	private JScrollPane scrollPaneB;
	private JScrollPane scrollPaneV;
	private JTable tableBookings;
	private JTable tableLoan;
	private JMenuBar menuBar;
	private JButton logout;
	private ArrayList<VideoGame> videoGames ;
	private JLabel lblCredit;
	private JPanel borrowedPane;
	private JScrollPane scrollPaneC;
	private JTable tableBorrowedCopie;
	private JButton btnEndBorrow;
	private JTabbedPane tabbedPane;
	private JButton btnBorrow;

	public PlayerInterface(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 500);
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(Box.createHorizontalGlue());
		setJMenuBar(menuBar);
		
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signin signin = new Signin();
				signin.setVisible(true);
				
				dispose();
			}
		});
		
		lblCredit = new JLabel("");
		lblCredit.setText("Credit(s): "+ player.getCredit()+ "  ");
		lblCredit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		menuBar.add(lblCredit);
		logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(logout);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(240, 240, 240));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableVideoGames = new JTable();
		tableVideoGames.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tableVideoGames.getSelectedRow();
				VideoGame vg = videoGames.get(rowSelected);
				if(player.getCredit()>= vg.getCrediCost()) {
					btnBorrow.setEnabled(true);
				}else
					btnBorrow.setEnabled(false);
			}
		});
		
		displayTable();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(tabbedPane.getSelectedIndex() == 0)
				{
					displayTable();
				}
			}
		});
		tabbedPane.setBounds(0, 0, 731, 463);
		contentPane.add(tabbedPane);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setForeground(new Color(0, 0, 0));
		
		JPanel VGPane = new JPanel();
		tabbedPane.addTab("VideoGames", null, VGPane, null);
		VGPane.setLayout(null);
		
		scrollPaneV = new JScrollPane();
		scrollPaneV.setBounds(37, 32, 654, 299);
		VGPane.add(scrollPaneV);
		
		
		scrollPaneV.setViewportView(tableVideoGames);
		
		JButton btnLend = new JButton("Put on loan");
		btnLend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableVideoGames.getSelectedRow();
				
				VideoGame vg = videoGames.get(rowSelected);
				Copy copy = new Copy(0, vg, player);
				if(copy.putOnLoan())
					JOptionPane.showMessageDialog(null, "copy added successfully !", null, JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "copy not added! An error has occurred please try again", null, JOptionPane.ERROR_MESSAGE);
			}
		});
		btnLend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLend.setBounds(91, 341, 241, 47);
		VGPane.add(btnLend);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableVideoGames.getSelectedRow();
				if(rowSelected>-1) {
					System.out.println(rowSelected);
					VideoGame vg = videoGames.get(rowSelected);
					//TODO: On prend le jeu video on prend ses copies, d'abord on verifie s'il a des copies si non on retourne false et isAvaible vaudra false
					//Si y a des copies on continue la verification et on regarde si c'est pas en pret (dans la table loan) si oui on return false si non on return la copie
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
				
				//displayTable();
				
			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrow.setBounds(399, 341, 241, 47);
		VGPane.add(btnBorrow);
		
		JPanel bookingsPane = new JPanel();
		tabbedPane.addTab("My Bookings", null, bookingsPane, null);
		bookingsPane.setLayout(null);
		
		scrollPaneB = new JScrollPane();
		scrollPaneB.setBounds(32, 46, 654, 299);
		bookingsPane.add(scrollPaneB);
		
		tableBookings = new JTable();
		scrollPaneB.setViewportView(tableBookings);
		
		JButton btnCancelBooking = new JButton("Cancel booking");
		btnCancelBooking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelBooking.setBounds(262, 355, 188, 35);
		bookingsPane.add(btnCancelBooking);
		
		JPanel loansPane = new JPanel();
		loansPane.setLayout(null);
		tabbedPane.addTab("My Loans", null, loansPane, null);
		
		JScrollPane scrollPaneL = new JScrollPane();
		scrollPaneL.setBounds(32, 46, 654, 299);
		loansPane.add(scrollPaneL);
		
		tableLoan = new JTable();
		scrollPaneL.setViewportView(tableLoan);
		
		JButton btnEndLoan = new JButton("End Loan");
		btnEndLoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEndLoan.setBounds(242, 355, 224, 39);
		loansPane.add(btnEndLoan);
		
		borrowedPane = new JPanel();
		borrowedPane.setLayout(null);
		tabbedPane.addTab("My borrowed copies", null, borrowedPane, null);
		
		scrollPaneC = new JScrollPane();
		scrollPaneC.setBounds(32, 46, 654, 299);
		borrowedPane.add(scrollPaneC);
		
		tableBorrowedCopie = new JTable();
		scrollPaneC.setViewportView(tableBorrowedCopie);
		
		btnEndBorrow = new JButton("End Borrow");
		btnEndBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEndBorrow.setBounds(242, 355, 224, 39);
		borrowedPane.add(btnEndBorrow);
	}
	
	
	public void displayTable() {
		
		model = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] colName = { "Name", "CreditCost", "Console"};
		model.setColumnIdentifiers(colName);
		tableVideoGames.setModel(model);
		
		videoGames  = VideoGame.getAll();
		
		for (VideoGame vg : videoGames) {
			String name, creditCost, console;
			name = vg.getName();
			creditCost = String.valueOf(vg.getCrediCost());
			console = vg.getConsole();
			model.addRow(new String[] {name,creditCost,console});
		}
	}
}
