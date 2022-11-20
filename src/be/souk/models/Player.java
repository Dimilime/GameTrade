package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.dao.*;

public class Player extends User implements Serializable {
	
	private static final long serialVersionUID = -3258539560199471902L;
	
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate registrationDate;
	private int credit;
	private ArrayList<Booking> bookings;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Player> playerDAO = adf.getPlayerDAO();

	public Player() {}
	
	public Player(int idUser, String userName, String password, String pseudo, LocalDate dateOfBirth, 
			LocalDate registrationDate, int credit) {
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate date) {
		this.dateOfBirth = date;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
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
	
	public void addBirthdayBonus() {
		 
		if(checkDateForBonus()) {
			
		}
	}
	
	private boolean checkDateForBonus() {
		
		//get his birdthday og this year
		LocalDate birthday = LocalDate.of(LocalDate.now().getYear(), dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		return false;
	}
	
	private boolean bonusAlreadyAdded() {
		
		return ((PlayerDAO)playerDAO).bonusAdded(this);
	}
	
	public static Player getPlayer(int id) {
		return playerDAO.find(id);
	}
	
	public boolean update() {
		return playerDAO.update(this);
	}
	
	public boolean cancelBooking() {
		//s'il cancel on lui resititue les credits qu'il a utilisé pour la réservation en récupérant le coup du jeu video pourlequel il fait la reservation
		return false;
	}
	
	
	
	
	

}
