package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.souk.dao.AbstractDAOFactory;
import be.souk.dao.DAO;

public class Loan implements Serializable {

	private static final long serialVersionUID = 4398522355874576768L;
	
	private static AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Loan> loanDAO = adf.getLoanDAO();
	
	private int idLoan;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean ongoing;
	private Player borrower;
	private Player lender;
	private Copy copy;
	
	public Loan(int idLoan, LocalDate startDate, LocalDate endDate, boolean ongoing, Player borrower, Player lender, Copy copy) {
		this.idLoan = idLoan;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ongoing = ongoing;
		this.borrower = borrower;
		this.lender = lender;
		this.copy = copy;
	}

	public int getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isOngoing() {
		return ongoing;
	}

	public void setOngoing(boolean ongoing) {
		this.ongoing = ongoing;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	public Player getLender() {
		return lender;
	}

	public void setLender(Player lender) {
		this.lender = lender;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}
	
	public boolean borrowing() {
		return loanDAO.create(this);
	}

	@Override
	public String toString() {
		return "Loan [idLoan=" + idLoan + ", startDate=" + startDate + ", endDate=" + endDate + ", ongoing=" + ongoing
				+ ", borrower=" + borrower + ", lender=" + lender + ", copy=" + copy + "]";
	}
	
	public static ArrayList<Loan> getAll() {
		return loanDAO.findAll();
	}
	
	public boolean endLoan() {
		return loanDAO.update(this);
	}
	
	public void calculateBalance() {
		
	}
	
	
	
	
	

}
