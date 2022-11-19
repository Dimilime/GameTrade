package be.souk.models;

import java.io.Serializable;
import java.util.ArrayList;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.CopyDAO;
import be.souk.dao.DAO;

public class Copy implements Serializable {
	
	private static final long serialVersionUID = -6673633708090081919L;
	
	private int idCopy;
	private VideoGame videoGame;
	private Player owner;
	private Loan loan;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Copy> copyDAO = adf.getCopyDAO();
	
	public Copy(int idCopy, VideoGame videoGame, Player owner) {
		this.idCopy = idCopy;
		this.videoGame = videoGame;
		this.owner = owner;
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

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public boolean putOnLoan() {
		return copyDAO.create(this);
	}
	
	public static ArrayList<Copy> getVideoGameCopies(VideoGame vg) {
		return ((CopyDAO)copyDAO).getVideoGameCopies(vg);
	}
	
	public boolean isAvailable() {
		return false;
	}
	

}
