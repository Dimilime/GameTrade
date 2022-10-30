package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Player extends User implements Serializable {
	
	private static final long serialVersionUID = -3258539560199471902L;
	
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate registrationDate;
	private int credit;
	private ArrayList<Booking> bookings;

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

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	
	
	

}
