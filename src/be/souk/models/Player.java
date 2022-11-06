package be.souk.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import be.souk.dao.*;

public class Player extends User implements Serializable {
	
	private static final long serialVersionUID = -3258539560199471902L;
	
	private String pseudo;
	private Date dateOfBirth;
	private Date registrationDate;
	private int credit;
	private ArrayList<Booking> bookings;
	
	private AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private DAO<Player> playerDAO = adf.getPlayerDAO();

	public Player() {}
	
	public Player(int idUser, String userName, String password, String pseudo, Date dateOfBirth, 
			Date registrationDate, int credit) {
		super(idUser, userName, password);
		this.pseudo = pseudo;
		this.dateOfBirth = dateOfBirth;
		this.registrationDate = registrationDate;
		this.credit = credit;
	}
	
	
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date date) {
		this.dateOfBirth = date;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public void signUp() {
		playerDAO.create(this);
	}

	public boolean exists() {
		
		return ((PlayerDAO)playerDAO).exists(this);
	}
	
	
	
	

}
