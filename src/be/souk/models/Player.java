package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import be.souk.dao.*;

public class Player extends User implements Serializable {
	
	private static final long serialVersionUID = -3258539560199471902L;
	
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate registrationDate;
	private int credit;
	private ArrayList<Booking> bookings;
	private ArrayList<Loan> loans;
	
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
		return bookings = getBorrowerBookings();
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
	
	public ArrayList<Loan> getLoans() {
		return getLenderLoans();
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public boolean signUp() {
		return playerDAO.create(this);
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
	
	public boolean cancelBooking(int index) {
		return bookings.get(index).delete();
	}
	
	public boolean loanAllowed(VideoGame vg) {
		return credit>= vg.getCrediCost();
	}
	
	public boolean hasEnoughToBorrow(VideoGame vg, int nbWeek) {
		return credit >= vg.getCrediCost()*nbWeek;
	}
	
	public void editCredit(int nb) {
		credit+=nb;
	}
	
	private ArrayList<Booking> getBorrowerBookings() {
		ArrayList<Booking> borrowerBookings = new ArrayList<>();
		for(Booking booking : Booking.getAll()) {
			if(booking.getBorrower().equals(this))
				borrowerBookings.add(booking);
		}
		return borrowerBookings;
	}
	private ArrayList<Loan> getLenderLoans() {
		ArrayList<Loan> lenderLoans = new ArrayList<>();
		for(Loan loan : Loan.getAll()) {
			if(loan.getLender().equals(this) && loan.isOngoing())
				lenderLoans.add(loan);
		}
		return lenderLoans;
	}

	@Override
	public String toString() {
		return "Player [pseudo=" + pseudo + ", dateOfBirth=" + dateOfBirth + ", registrationDate=" + registrationDate
				+ ", credit=" + credit + ", bookings=" + bookings + "]";
	}

	@Override
	public int hashCode() {
		
		return super.hashCode() + Objects.hash(bookings, credit, dateOfBirth, loans, pseudo, registrationDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		return Objects.equals(bookings, other.bookings) && credit == other.credit
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(loans, other.loans)
				&& Objects.equals(pseudo, other.pseudo) && Objects.equals(registrationDate, other.registrationDate);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
