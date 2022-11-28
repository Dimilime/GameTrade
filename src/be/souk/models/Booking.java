package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class Booking implements Serializable{

	static final long serialVersionUID = -1082029551876283845L;
	
	private int idBooking;
	private LocalDate bookingDate;
	private Player borrower;
	private long nbWeek;
	private VideoGame videoGame;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Booking> bookingDAO = adf.getBookingDAO();
	
	public Booking(int idBooking, LocalDate bookingDate, Player borrower, VideoGame videoGame, long nbWeek) {
		this.idBooking = idBooking;
		this.bookingDate = bookingDate;
		this.borrower = borrower;
		this.nbWeek = nbWeek;
		this.videoGame = videoGame;
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

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	public long getNbWeek() {
		return nbWeek;
	}

	public void setNbWeek(long nbWeek) {
		this.nbWeek = nbWeek;
	}
	
	public boolean book() {
		return bookingDAO.create(this);
	}
	
	public static ArrayList<Booking> getAll(){
		return bookingDAO.findAll();
	}
	public boolean delete() {
		return bookingDAO.delete(this);
	}

	
	
	
	
	
	
	
	
	

}
