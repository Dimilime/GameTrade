package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable{

	static final long serialVersionUID = -1082029551876283845L;
	
	private int idBooking;
	private LocalDate bookingDate;
	private Player borrower;
	
	public Booking(int idBooking, LocalDate bookingDate, Player borrower) {
		this.idBooking = idBooking;
		this.bookingDate = bookingDate;
		this.borrower = borrower;
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}
	
	
	
	
	
	
	
	

}
