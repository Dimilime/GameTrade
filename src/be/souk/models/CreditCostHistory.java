package be.souk.models;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCostHistory implements Serializable{

	private static final long serialVersionUID = 7569112340164365736L;
	
	private int idCreditCostHistory;
	private int creditCost;
	private LocalDate modificationDate;
	
	public CreditCostHistory(int idCreditCostHistory, int creditCost, LocalDate modificationDate) {
		this.idCreditCostHistory = idCreditCostHistory;
		this.creditCost = creditCost;
		this.modificationDate = modificationDate;
	}
	public int getIdCreditCostHistory() {
		return idCreditCostHistory;
	}
	public void setIdCreditCostHistory(int idCreditCostHistory) {
		this.idCreditCostHistory = idCreditCostHistory;
	}
	
	public int getCreditCost() {
		return creditCost;
	}
	public void setCreditCost(int creditCost) {
		this.creditCost = creditCost;
	}
	public LocalDate getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
	}
	
}
