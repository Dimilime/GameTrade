package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class Copy implements Serializable {
	
	private static final long serialVersionUID = -6673633708090081919L;
	
	private int idCopy;
	private VideoGame videoGame;
	private Player owner;
	private boolean isAvailable;
	private Loan loan;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Copy> copyDAO = adf.getCopyDAO();
	
	public Copy(int idCopy, VideoGame videoGame, Player owner, boolean isAvailable) {
		this.idCopy = idCopy;
		this.videoGame = videoGame;
		this.owner = owner;
		this.isAvailable = isAvailable;
		}

	
	public int getIdCopy() {
		return idCopy;
	}

	public void setIdCopy(int idCopy) {
		this.idCopy = idCopy;
	}

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}


	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public boolean add() {
		return copyDAO.create(this);
	}
	
	public boolean releaseCopy() {
		isAvailable = true;
		videoGame.selectBooking(this);
		return copyDAO.update(this);
	}
	
	public void copyOnLoan() {
		isAvailable= false;
		copyDAO.update(this);
	}
	
	public void borrow(Booking booking) {
		Long nbWeek = booking.getNbWeek();
		Player borrower = booking.getBorrower();
		loan = new Loan(0, LocalDate.now(), LocalDate.now().plusWeeks(nbWeek), true, borrower, owner, this);
		loan.borrowing();
	}
	
	public static ArrayList<Copy> getAll(){
		return copyDAO.findAll();
	}
	public static Copy getCopy(int id) {
		return copyDAO.find(id);
	}
	
	public boolean delete() {
		if(isAvailable)
			return copyDAO.delete(this);
		return false;
	}

	
}
