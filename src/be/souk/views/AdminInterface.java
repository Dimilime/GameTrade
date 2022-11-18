package be.souk.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import be.souk.models.Admin;
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

import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AdminInterface extends JFrame {

	private static final long serialVersionUID = 7544019862560332915L;
	private JPanel contentPane;
	private JTextField txtfName;
	private JTextField txtfCreditCost;
	private JTextField txtfConsole;
	private JLabel lblErrorName ;
	private JLabel lblErrorCreditCost;
	private JLabel lblErrorConsole;
	private DefaultTableModel model;
	private JTable table;
	private JMenuBar menuBar;
	private JButton logout;
	private boolean editable;
	private ArrayList<VideoGame> videoGames ;

	
	public AdminInterface(Admin admin) {
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
		logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		menuBar.add(logout);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(240, 240, 240));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(tabbedPane.getSelectedIndex() == 1)
				{
					displayTable();
				}
			}
		});
		tabbedPane.setBounds(0, 0, 731, 463);
		contentPane.add(tabbedPane);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setForeground(new Color(0, 0, 0));
		
		JPanel addVGPane = new JPanel();
		tabbedPane.addTab("Add VideoGame", null, addVGPane, null);
		addVGPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(123, 101, 114, 34);
		addVGPane.add(lblName);
		
		txtfName = new JTextField();
		lblName.setLabelFor(txtfName);
		txtfName.setBounds(258, 104, 308, 34);
		txtfName.getDocument().addDocumentListener(addVideoGameListener);
		addVGPane.add(txtfName);
		txtfName.setColumns(10);
		
		txtfCreditCost = new JTextField();
		txtfCreditCost.setColumns(10);
		txtfCreditCost.setBounds(258, 168, 308, 34);
		txtfCreditCost.getDocument().addDocumentListener(addVideoGameListener);
		addVGPane.add(txtfCreditCost);
		
		JLabel lblCreditCost = new JLabel("Credit Cost");
		lblCreditCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCreditCost.setBounds(123, 165, 114, 34);
		addVGPane.add(lblCreditCost);
		
		txtfConsole = new JTextField();
		txtfConsole.setColumns(10);
		txtfConsole.setBounds(258, 233, 308, 34);
		txtfConsole.getDocument().addDocumentListener(addVideoGameListener);
		addVGPane.add(txtfConsole);
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsole.setBounds(123, 230, 114, 34);
		addVGPane.add(lblConsole);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name, console;
				int credicost;
				
				try 
				{
					if(txtfName.getText().trim().length()>0 && txtfConsole.getText().trim().length()>0 && txtfCreditCost.getText().trim().length()>0) {
						name = txtfName.getText().toUpperCase();
						console = txtfConsole.getText().toUpperCase();
						credicost = Integer.valueOf(txtfCreditCost.getText());
						VideoGame vg = new VideoGame(0,name, console, credicost);
						
						if(admin.addVideoGame(vg))
							JOptionPane.showMessageDialog(null, "videoGame added successfully !", null, JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "videoGame not added! An error has occurred please try again", null, JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Empty ", null, JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Incorrect number format", null, JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(123, 310, 444, 44);
		addVGPane.add(btnNewButton);
		
		lblErrorName = new JLabel("");
		lblErrorName.setForeground(new Color(255, 128, 128));
		lblErrorName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorName.setBounds(258, 135, 444, 34);
		addVGPane.add(lblErrorName);
		
		lblErrorCreditCost = new JLabel("");
		lblErrorCreditCost.setForeground(new Color(255, 128, 128));
		lblErrorCreditCost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorCreditCost.setBounds(258, 199, 444, 34);
		addVGPane.add(lblErrorCreditCost);
		
		lblErrorConsole = new JLabel("");
		lblErrorConsole.setForeground(new Color(255, 128, 128));
		lblErrorConsole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblErrorConsole.setBounds(258, 266, 444, 34);
		addVGPane.add(lblErrorConsole);
		
		JPanel VGPane = new JPanel();
		tabbedPane.addTab("VideoGames", null, VGPane, null);
		VGPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 32, 654, 299);
		VGPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSetCredit = new JButton("SET CREDIT");
		btnSetCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = table.getSelectedRow();
				int newCredit = Integer.valueOf(JOptionPane.showInputDialog("Enter the new value")) ;
				VideoGame vg = videoGames.get(rowSelected);
				vg.setCrediCost(newCredit);
				admin.setCreditVideoGame(vg);
				displayTable();
				
			}
		});
		btnSetCredit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetCredit.setBounds(91, 341, 241, 47);
		VGPane.add(btnSetCredit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelected = table.getSelectedRow();
				
				VideoGame vg = videoGames.get(rowSelected);
				admin.deleteVideoGame(vg);
				displayTable();
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(399, 341, 241, 47);
		VGPane.add(btnDelete);
	}
	

	DocumentListener addVideoGameListener = new DocumentListener() 
	{
			
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
			if(txtfName.getText().trim().length()>0)
				if(!(txtfName.getText().length() >=2 && txtfName.getText().length() <=30)) {
					lblErrorName.setText("Please enter a name of minimum 2 characters and maximum 30");
				}
				else
					lblErrorName.setText("");
			else
				lblErrorName.setText("");
			
			
			if(txtfCreditCost.getText().trim().length()>0)
				try  {
					Integer.parseInt(txtfCreditCost.getText());
					lblErrorCreditCost.setText("");
				}
				catch(NumberFormatException e) {
					lblErrorCreditCost.setText("Please enter a number");
				}	
			else
				lblErrorCreditCost.setText("");
			
			if(txtfConsole.getText().trim().length()>0)
				if(!(txtfConsole.getText().length() >= 2 && txtfConsole.getText().length() <=30)) {
					lblErrorConsole.setText("Please enter a name of minimum 2 characters and maximum 30");
				}
				else
					lblErrorConsole.setText("");
			else
				lblErrorConsole.setText("");
		}

		
	};
	
	
	public void displayTable() {
		
		model = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] colName = { "Name", "CreditCost", "Console"};
		model.setColumnIdentifiers(colName);
		table.setModel(model);
		
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
