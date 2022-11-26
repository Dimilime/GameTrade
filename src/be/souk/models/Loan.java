package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		copy.copyOnLoan();
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
		int loanCost = calculateBalance();
		lender.editCredit(+loanCost);
		borrower.editCredit(-loanCost);
		lender.update();
		borrower.update();
		ongoing = false;
		if(copy.releaseCopy()) {
			return loanDAO.update(this);
		}
		
		return false;
	}
	
	private int calculateBalance() {
		int balance=0;
		LocalDate receivedDate = LocalDate.now();
		while(!startDate.equals(endDate))
		{
			
			ArrayList<CreditCostHistory> latestModifieds= copy.getVideoGame().getCreditCostHistories()
					.stream().filter(ccH -> ccH.getModificationDate().isBefore(startDate) ||ccH.getModificationDate().equals(startDate))
					.collect(Collectors.toCollection(ArrayList::new));
			CreditCostHistory lastModfied = latestModifieds.get(latestModifieds.size()-1);
			balance+= lastModfied.getCreditCost();
			
			startDate= startDate.plusWeeks(1);
		}
		if(receivedDate.isAfter(endDate)) {
			long daysBetween = ChronoUnit.DAYS.between(endDate,receivedDate);
			balance+= 5*daysBetween;
		}
			
		return balance;
		
	}
	
	
	
	
	
	

}
