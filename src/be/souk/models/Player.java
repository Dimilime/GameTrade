package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import be.souk.dao.*;

public class Player extends User implements Serializable {
	
	private static final long serialVersionUID = -3258539560199471902L;
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Player> playerDAO = adf.getPlayerDAO();
	
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate registrationDate;
	private int credit;
	private LocalDate lastSeen;
	private ArrayList<Booking> bookings;
	private ArrayList<Loan> loans;
	private ArrayList<Copy> copies;
	

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
		return bookings = Booking.getAll().stream()
				.filter(booking -> booking.getBorrower().getIdUser() == idUser)
				.collect(Collectors.toCollection(ArrayList::new));
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
		return loans= Loan.getAll().stream()
				.filter(loan-> loan.getLender().getIdUser() == idUser && loan.isOngoing())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public ArrayList<Copy> getCopies() {
		return copies = Copy.getAll().stream()
				.filter(c-> c.getOwner().getIdUser() == idUser)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

	public LocalDate getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDate lastSeen) {
		this.lastSeen = lastSeen;
	}

	public boolean signUp() {
		return playerDAO.create(this);
	}
	
	public int getAge() {
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	
	public void addBirthdayBonus() {
		 
		while(!lastSeen.equals(LocalDate.now())) {
			
			if(dateOfBirth.getMonth().equals(lastSeen.getMonth()) && dateOfBirth.getDayOfMonth() == lastSeen.getDayOfMonth()) {
				credit+=2;
			}
			lastSeen = lastSeen.plusDays(1);
		}
		
		//if he signup the day of his birthday
		if(dateOfBirth.getMonth().equals(lastSeen.getMonth()) && dateOfBirth.getDayOfMonth() == lastSeen.getDayOfMonth()) {
			credit+=2;
		}
	}
	
	public boolean deleteCopy(int index) {
		return copies.get(index).delete();
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
	
	@Override
	public String toString() {
		return super.toString()+ " Player [pseudo=" + pseudo + ", dateOfBirth=" + dateOfBirth + ", registrationDate=" + registrationDate
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
