package be.souk.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import be.souk.models.Booking;
import be.souk.models.Copy;
import be.souk.models.Loan;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlayerInterface extends JFrame {

	private static final long serialVersionUID = 7544019862560332915L;
	private ArrayList<Booking> bookings;
	private ArrayList<Loan> loans;
	private ArrayList<VideoGame> videoGames ;
	private ArrayList<Copy> copies ;
	private JPanel contentPane;
	private JTable tableVideoGames;
	private JScrollPane scrollPaneB;
	private JScrollPane scrollPaneV;
	private JTable tableBookings;
	private JTable tableLoans;
	private JMenuBar menuBar;
	private JButton logout;
	private JLabel lblCredit;
	private JTabbedPane tabbedPane;
	private JButton btnBorrow;
	private JTable tableCopies;

	public PlayerInterface(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 500);
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWelcome.setText("<html>&ensp Signed in as <b> "+ player.getPseudo() +" </b></html>");
		menuBar.add(lblWelcome);
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
				if(rowSelected>-1) {
					VideoGame vg = videoGames.get(rowSelected);
					if(player.loanAllowed(vg)) {
						btnBorrow.setEnabled(true);
					}else
						btnBorrow.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);

				
			}
		});
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				switch (tabbedPane.getSelectedIndex()) {
					case 0:
						displayTableVG();
						break;
					case 1:
						displayTableBookings(player);
						break;
					case 2:
						displayTableLoans(player);
						break;
					case 3 :
						displayTableCopies(player);
						break;
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
		
		JButton btnAddCopy = new JButton("Add a copy");
		btnAddCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableVideoGames.getSelectedRow();
				if(rowSelected>-1) {
					VideoGame vg = videoGames.get(rowSelected);
					Copy copy = new Copy(0, vg, player, true);
					if(copy.add())
						JOptionPane.showMessageDialog(null, "copy added successfully !", null, JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "copy not added! An error has occurred please try again", null, JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnAddCopy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddCopy.setBounds(91, 341, 241, 47);
		VGPane.add(btnAddCopy);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableVideoGames.getSelectedRow();
				
				String input;
				int nbweek=0;
				if(rowSelected>-1) {
					VideoGame vg = videoGames.get(rowSelected);
					boolean error = false;
					do {
						try 
						{
							input =JOptionPane.showInputDialog("How many weeks do you want to borrow ?");
							if(input !=null && input.trim().length()>0) {
								nbweek = Integer.valueOf(input);
								
								if(nbweek<1) {
									error = true;
									JOptionPane.showMessageDialog(null, "Please enter a number greater than 0", null, JOptionPane.ERROR_MESSAGE);
								}else if(!player.hasEnoughToBorrow(vg, nbweek)){
									JOptionPane.showMessageDialog(null, "The number of weeks is too high to borrow, you haven't enough credits ",
											null, JOptionPane.ERROR_MESSAGE);
									error = true;
								}
								else
									error=false;
							}
							else
								error = false;
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Please enter a number", null, JOptionPane.ERROR_MESSAGE);
							error = true;
						}
					}while(error);
					
					Copy copy = vg.copyAvailable(player);
					if(copy!=null && nbweek >0) {
						Loan loan = new Loan(0, LocalDate.now(), LocalDate.now().plusWeeks(nbweek), true, player, copy.getOwner(), copy);
						
						if(loan.borrowing()) {
							JOptionPane.showMessageDialog(null,"loan added successfully !", null, JOptionPane.INFORMATION_MESSAGE);
						}   
						else
							JOptionPane.showMessageDialog(null, "loan not added! An error has occurred please try again", null, JOptionPane.ERROR_MESSAGE);
					}
					else if(nbweek >0) {
						int response = JOptionPane.showConfirmDialog(null, "There is no available copy of this game do you want to book it?");
						
						if(response == 0) {
							Booking booking = new Booking(0, LocalDate.now(), player, vg, nbweek);
							
							if (booking.book()) {
								JOptionPane.showMessageDialog(null, "booking added successfully !", null, JOptionPane.INFORMATION_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(null, "booking not added! An error has occurred please try again", null, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
				
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
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = tableBookings.getSelectedRow();
				if(rowSelected>-1) {
					player.cancelBooking(rowSelected);
					displayTableBookings(player);
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
			
			}
		});
		btnCancelBooking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelBooking.setBounds(262, 355, 188, 35);
		bookingsPane.add(btnCancelBooking);
		
		JPanel loansPane = new JPanel();
		loansPane.setLayout(null);
		tabbedPane.addTab("My Loans", null, loansPane, null);
		
		JScrollPane scrollPaneL = new JScrollPane();
		scrollPaneL.setBounds(32, 46, 654, 299);
		loansPane.add(scrollPaneL);
		
		tableLoans = new JTable();
		scrollPaneL.setViewportView(tableLoans);
		
		JButton btnEndLoan = new JButton("End Loan");
		btnEndLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowSelected = tableLoans.getSelectedRow();
				if(rowSelected>-1) {
					Loan loan= loans.get(rowSelected);
					if(loan.endLoan()) {
						JOptionPane.showMessageDialog(null, "Loan ended!", null, JOptionPane.INFORMATION_MESSAGE);
						//just for dynamism
						lblCredit.setText("Credit(s): "+ Player.getPlayer(player.getIdUser()).getCredit()+ "  ");
					}
						
					else
						JOptionPane.showMessageDialog(null, "Error loan not ended correctly", null, JOptionPane.ERROR_MESSAGE);
					displayTableLoans(player);
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
					
				
			}
		});
		btnEndLoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEndLoan.setBounds(242, 355, 224, 39);
		loansPane.add(btnEndLoan);
		
		JPanel copiesPane = new JPanel();
		copiesPane.setLayout(null);
		tabbedPane.addTab("My Copies", null, copiesPane, null);
		
		JScrollPane scrollPaneC = new JScrollPane();
		scrollPaneC.setBounds(32, 46, 654, 299);
		copiesPane.add(scrollPaneC);
		
		tableCopies = new JTable();
		scrollPaneC.setViewportView(tableCopies);
		
		JButton btnDeletecopy = new JButton("Delete");
		btnDeletecopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowSelected = tableCopies.getSelectedRow();
				if(rowSelected>-1) {
					if(copies.get(rowSelected).delete())
						JOptionPane.showMessageDialog(null,"copy deleted successfully !", null, JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,"error copy not deleted, probably still on loan!", null, JOptionPane.ERROR_MESSAGE);
					displayTableCopies(player);
				}
				else
					JOptionPane.showMessageDialog(null, "Please select a row", null, JOptionPane.ERROR_MESSAGE);
				
			}
		});
		btnDeletecopy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeletecopy.setBounds(242, 355, 224, 39);
		copiesPane.add(btnDeletecopy);
	}
	
	
	public void displayTableVG() {
		videoGames = VideoGame.getAll();
		String[] colName = { "Name", "CreditCost", "Console"};
		DefaultTableModel model = new DefaultTableModel() {
			
			private static final long serialVersionUID = -8619738515202886303L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(colName);
		tableVideoGames.setModel(model);
		tableVideoGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumnModel cModel= tableVideoGames.getColumnModel();
		cModel.getColumn(0).setPreferredWidth(200);
		cModel.getColumn(1).setPreferredWidth(50);
		cModel.getColumn(2).setPreferredWidth(50);
		
		for (VideoGame vg : videoGames) {
			String name, creditCost, console;
			name = vg.getName();
			creditCost = String.valueOf(vg.getCrediCost());
			console = vg.getConsole();
			model.addRow(new String[] {name,creditCost,console}); 
		}
	}
	
	public void displayTableBookings(Player player) {
		bookings = player.getBookings();
		DefaultTableModel model = new DefaultTableModel() {
			
			private static final long serialVersionUID = -5773367108093712923L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] colName = { "Booking date", "VideoGame", "Number of weeks"};
		model.setColumnIdentifiers(colName);
		tableBookings.setModel(model);
		tableBookings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (Booking bk : bookings) {
			String date, videoGame, nbWeek;
			date = bk.getBookingDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			videoGame = bk.getVideoGame().getName();
			nbWeek = String.valueOf(bk.getNbWeek());
			model.addRow(new String[] {date,videoGame,nbWeek});
		}
	}
	
	public void displayTableLoans(Player player) {
		loans  = player.getLoans();
		DefaultTableModel modelLoan = new DefaultTableModel() {
			private static final long serialVersionUID = 1871578459627816924L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] colName = { "Start Date", "End Date", "Borrower", "VideoGame"};
		modelLoan.setColumnIdentifiers(colName);
		tableLoans.setModel(modelLoan);
		tableLoans.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (Loan loan : loans) {
			String startDate,endDate, borrower, videoGame;
			startDate = loan.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			endDate = loan.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			borrower = loan.getBorrower().getUserName();
			videoGame = loan.getCopy().getVideoGame().getName();
			modelLoan.addRow(new String[] {startDate,endDate,borrower,videoGame});
		}
	}
	public void displayTableCopies(Player player) {
		copies  = player.getCopies();
		DefaultTableModel modelLoan = new DefaultTableModel() {
			private static final long serialVersionUID = 1871578459627816924L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		String[] colName = {"VideoGame", "Console"};
		modelLoan.setColumnIdentifiers(colName);
		tableCopies.setModel(modelLoan);
		tableCopies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (Copy copy : copies) {
			String videoGame, console;
			videoGame = copy.getVideoGame().getName();
			console = copy.getVideoGame().getConsole();
			modelLoan.addRow(new String[] {videoGame, console});
		}
	}
}
